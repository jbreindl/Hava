package Model

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import physics.{PhysicsVector, Playground}

class GameActor extends Actor{
  var players: Map[String, ActorRef] = Map()
  var towers: List[ActorRef] = List()
  var playerNumber = 0
  var sharkNum = 0
  var minnowNum = 0

  val game: Game = new Game()
  var levelNumber = 0
  loadLevel(levelNumber)

  def loadLevel(levelNumber: Int): Unit ={
    game.loadLevel(new Playground)
  }

  override def receive: Receive = {

    case message: AddPlayer =>
      if (playerNumber == 0 & sharkNum == 0){
        game.addShark(message.username)
        playerNumber += 1
        sharkNum += 1
        print(playerNumber)
        print(sharkNum)
      }
      else {
        game.addMinnow(message.username)
        playerNumber += 1
        minnowNum += 1
        print(playerNumber)
        print(sharkNum)
      }
    case message: RemovePlayer =>
      if (game.playerMap(message.username).tag == "shark") {
        game.removePlayer(message.username)
        playerNumber -= 1
        sharkNum -= 1
        print(playerNumber)
        print(sharkNum)

      }
      else {
        game.removePlayer(message.username)
        playerNumber -= 1
        minnowNum -= 1
        print(playerNumber)
        print(sharkNum)
      }
    case message: MovePlayer =>
      game.playerMap(message.username).move(new PhysicsVector(message.x, message.y))

    case message: StopPlayer =>
      game.playerMap(message.username).stop()

    case message: changeClass =>
      game.checkForTags()

    case UpdateGame =>
      game.update()
      if (playerNumber >= 2 & minnowNum == 0){
        loadLevel(levelNumber)
      }

    case SendGameState =>
      sender() ! GameState(game.gameState())

  }

}
