package Model.Characters

import Model.physics.{GameObject, PhysicalObject, PhysicsVector}

class Shark (var inputLocation: PhysicsVector, var inputVelocity: PhysicsVector) extends fish (inputLocation, inputVelocity) {

  override val tag = "shark"

  val speed: Double = 5
  val fishSize: Double = .6
}