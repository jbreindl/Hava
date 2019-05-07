package Model.physics

class PhysicalObject(var location: PhysicsVector, var velocity: PhysicsVector) extends GameObject {

  def onGround(): Unit = {}
  def collide(): Unit = {}
  val tag: String = "po"

  override def toString = s"PhysicalObject($location, $velocity)"
}
