package Model

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import physics.{PhysicsVector, Playground}

class GameActor extends Actor{
  var players: Map[String, ActorRef] = Map()
  var towers: List[ActorRef] = List()

  val game: Game = new Game()
  var levelNumber = 0
  loadLevel(levelNumber)

  def loadLevel(levelNumber: Int): Unit ={
    game.loadLevel(new Playground)
  }

  override def receive: Receive = {
    case message: AddPlayer =>
      // if shark number zero, then shark
      // else minnow
    case message: RemovePlayer => game.removePlayer(message.username)
    case message: MovePlayer => game.players(message.username).move(new PhysicsVector(message.x, message.y))
    case message: StopPlayer => game.players(message.username).stop()
    case UpdateGame => game.update()
    case SendGameState => sender() ! GameState(game.gameState())
  }

}
