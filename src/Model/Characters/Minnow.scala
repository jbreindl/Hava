package Model.Characters

import Model.physics.PhysicsVector

class Minnow(inputLocation: PhysicsVector, inputVelocity: PhysicsVector) extends fish(inputLocation, inputVelocity) {
  override def fishType = "minnow"

  override val speed: Double = 3
  override val fishSize: Double = 0.3

  def tag(): Unit = this = new Shark(this.location, new PhysicsVector(0,0))

}