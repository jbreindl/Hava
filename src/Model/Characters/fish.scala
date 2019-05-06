package Model.Characters

import Model.physics.{PhysicalObject, PhysicsVector}

abstract class fish(inputLocation: PhysicsVector, inputVelocity: PhysicsVector) extends PhysicalObject(inputLocation,inputVelocity){
  var fishType: String
  val fishSize: Double
  val speed: Double

  def move(direction: PhysicsVector){
    val normalDirection = direction.normal2d()
    this.velocity= new PhysicsVector(normalDirection.x * speed, normalDirection.y * speed)
  }

  def stop(): Unit ={
    this.velocity = new PhysicsVector(0, 0)
  }
}
