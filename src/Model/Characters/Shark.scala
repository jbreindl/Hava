package Model.Characters

import Model.physics.{GameObject, PhysicalObject, PhysicsVector}

class Shark (var inputLocation: PhysicsVector, var inputVelocity: PhysicsVector) extends PhysicalObject(inputLocation, inputVelocity) {

  var id: String = _

  def move(direction: PhysicsVector){
    val normalDirection = direction.normal2d()
    this.velocity = new PhysicsVector(normalDirection.x * speed, normalDirection.y * speed)
  }
def stop(): Unit= this.velocity = new PhysicsVector(0,0)


  val speed: Double = 5
  val fishSize: Double = 0.5
}