package Model.Characters
import states.playerStates._

class Minnow extends fish {
  override var fishType: playerState = new Alive

  def die(): Unit = {
    this.fishType = new Dead
  }

  def safe(): Unit = {
    this.fishType = new outOfPlay
  }
}