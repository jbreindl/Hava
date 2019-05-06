package Model.Characters

import Model.physics.PhysicsVector

class Shark (inputLocation: PhysicsVector, inputVelocity: PhysicsVector) extends fish(inputLocation, inputVelocity) {
  override def fishType = "shark"

  override val speed: Double = 5
  override val fishSize: Double = 0.7
}