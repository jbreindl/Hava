package Model.Characters

import Model.physics.{PhysicalObject, PhysicsVector}

abstract class fish(inputLocation: PhysicsVector, inputVelocity: PhysicsVector) extends PhysicalObject(inputLocation, inputVelocity){
  var id: String = _
  val speed: Double
  override val tag: String= "fish"

  def move(direction: PhysicsVector){
    val normalDirection = direction.normal2d()
    this.velocity = new PhysicsVector(normalDirection.x * speed, normalDirection.y * speed)
  }

  def stop() :Unit= this.velocity= new PhysicsVector(0,0)
}
