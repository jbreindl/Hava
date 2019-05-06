package Model.Characters

import Model.physics.PhysicsVector

class Minnow(inputLocation: PhysicsVector, inputVelocity: PhysicsVector) {
  val speed: Double = 3
  val size: Double = 0.3
  def tag(): Unit = this = new Shark(this.inputLocation, new PhysicsVector(0,0))

  var location: PhysicsVector = inputLocation
}