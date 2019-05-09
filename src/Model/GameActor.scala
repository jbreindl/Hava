package Model

import Characters._
import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import physics.{PhysicsVector, Playground}

class GameActor extends Actor{
  var players: Map[String, ActorRef] = Map()
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
        println("playerNumber")
        println(playerNumber)
        println("sharkNumber")
        println(sharkNum)
      }

      else {
        game.addMinnow(message.username)
        println("playerNumber")
        println(playerNumber)
        println("minnowNumber")
        println(minnowNum)
      }
    case message: RemovePlayer =>
      if (game.playerMap(message.username).tag == "shark") {
        game.removePlayer(message.username)
        println("playerNumber")
        println(playerNumber)
        println(sharkNum)

      }
      else {
        game.removePlayer(message.username)
        println("playerNumber")
        println(playerNumber)
        println("minnowNumber")
        println(minnowNum)
      }
    case message: MovePlayer =>
      game.playerMap(message.username).move(new PhysicsVector(message.x, message.y))

    case message: StopPlayer =>
      game.playerMap(message.username).stop()

    case UpdateGame =>
      game.update()
      sharkNum = game.sharkList.size
      minnowNum = game.minnowList.size
      playerNumber = game.playerMap.size

    case SendGameState =>
      sender() ! GameState(game.gameState())

    case resetGame =>
      if (playerNumber >= 2 & minnowNum == 0){
        println("game is being reset")
        var holder: List[Shark] = List()
        for (players <- game.sharkList.values){
          holder :+= players
        }
        val random = scala.util.Random.nextInt(holder.size - 1)
        for (i <- 0 until holder.size){
          if (i != random){
            game.reverseTag(holder(i))
          }
        }
      }
      else if(playerNumber == 1 & minnowNum == 1){
        println("game is being reset")
        var holder: List[Minnow] = List()
        for (players <- game.minnowList.values) {
          holder :+= players
        }
        game.tag(holder(0))
      }
  }

}
