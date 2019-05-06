package Model.Characters

import Model.physics.Physics.{equalDoubles, slope, yIntercept}
import Model.physics.{Boundary, PhysicsVector}

class Minnow(var inputLocation: PhysicsVector, var inputVelocity: PhysicsVector) {
  val speed: Double = 3
  val size: Double = 0.3
  var id: String = _

  def tag(): Unit={
    val ret = new Shark(this.inputLocation, new PhysicsVector(0,0))
    ret.id = this.id
    this= ret
  }

  var location: PhysicsVector = inputLocation

  def computePotentialLocation(dt: Double): PhysicsVector = {
    val newX = this.inputLocation.x + dt * this.inputVelocity.x
    val newY = this.inputLocation.y + dt * this.inputVelocity.y
    new PhysicsVector(newX,newY)
  }

  def isAllowed(potentialLocation: PhysicsVector, boundary: Boundary): Boolean={
    val EPSILON: Double = 0.000000001
    if(this.location.x == potentialLocation.x && this.location.y == potentialLocation.y){
      return false
    }
    val mObj = slope(this.location, potentialLocation)
    val bObj = yIntercept(this.location, mObj)

    val mBound = slope(boundary.start, boundary.end)
    val bBound = yIntercept(boundary.start, mBound)
    if (equalDoubles(mObj, mBound)) {
      return false
    }

    //    m1x + b1 = m2x + b2
    //    m1x - m2x = b2 - b1
    //    x(m1x - m2) = b2 - b1
    //    x = (b2 - b1) / (m1x - m2)

    val ix: Double = (bBound - bObj) / (mObj - mBound)
    val iy: Double = ix * mObj + bObj
    val iy_redundant: Double = ix * mBound + bBound

    val objLeft = this.location.x.min(potentialLocation.x)
    val objRight = this.location.x.max(potentialLocation.x)

    val objUp = this.location.y.min(potentialLocation.y)
    val objDown = this.location.y.max(potentialLocation.y)

    val bLeft = boundary.start.x.min(boundary.end.x)
    val bRight = boundary.start.x.max(boundary.end.x)

    val bUp = boundary.start.y.min(boundary.end.y)
    val bDown = boundary.start.y.max(boundary.end.y)

    ((ix >= objLeft-EPSILON && ix <= objRight+EPSILON) && (iy >= objUp-EPSILON && iy <= objDown+EPSILON)) && ((ix >= bLeft -EPSILON&& ix <= bRight+EPSILON) && (iy >= bUp-EPSILON && iy <= bDown+EPSILON))
  }


  def move(direction: PhysicsVector){
    val normalDirection = direction.normal2d()
    this.inputVelocity = new PhysicsVector(normalDirection.x * speed, normalDirection.y * speed)
  }
  def stop() :Unit= this.inputVelocity = new PhysicsVector(0,0)
}