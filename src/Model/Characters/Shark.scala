package Model.Characters

import Model.physics.{GameObject, PhysicalObject, PhysicsVector, sharkBoundary}

class Shark (inputLocation: PhysicsVector, inputVelocity: PhysicsVector) extends PhysicalObject(inputLocation, inputVelocity) {

  val speed: Double = 5
  val fishSize: Double = 0.5
}