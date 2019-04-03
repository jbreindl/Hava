package Model.Characters
import Model.Characters.states.playerStates._

class Shark extends fish {
  override var fishType: playerState = new Alive

  def tag(minnow: Minnow): Unit={
    minnow.die()
  }
}
