package Model.Characters

import Model.physics.Physics.{equalDoubles, slope, yIntercept}
import Model.physics.{Boundary, PhysicsVector}

class Minnow(var inputLocation: PhysicsVector, var inputVelocity: PhysicsVector) extends fish (inputLocation, inputVelocity){
  val speed: Double = 3
  val size: Double = 0.3
  override val tag = "minnow"
}