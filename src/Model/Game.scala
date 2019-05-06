package Model

import Model.Characters.{Minnow, Shark}
import Model.physics.{Boundary, GridLocation, Physics, PhysicsVector, Playground, Wall, World}

import scala.collection.mutable.ListBuffer

class Game {

  var world: World = new World(10)
  var sharkList: ListBuffer[Shark] = ListBuffer()
  var minnowList: ListBuffer[Minnow] = ListBuffer()
  val sharkPoint: GridLocation = new GridLocation(15, 5)
  val minnowPoint: GridLocation = new GridLocation(0, 5)
  var playground = new Playground
  var walls: List[Wall] = List()
  var lastUpdateTime: Long = System.nanoTime()

  def loadLevel(newLevel: Playground): Unit = {
    world.boundaries = List()
    playground = newLevel
    walls = List()
    blockTile(0, 0, playground.gridWidth, playground.gridHeight)

    playground.walls.foreach(wall => placeWall(wall.x, wall.y))
  }

  def placeWall(x: Int, y: Int): Unit = {
    blockTile(x, y)
    walls = new Wall(x, y) :: walls
  }


  def update(): Unit = {
    val time: Long = System.nanoTime()
    val dt = (time - this.lastUpdateTime) / 1000000000.0
    Physics.updateWorld(this.world, dt)
    checkForFinish()
    checkForTags()
    this.lastUpdateTime = time
  }

  def sharkSpawn(): PhysicsVector ={
    new PhysicsVector(sharkPoint.x, sharkPoint.y)
  }
  def MinnowSpawn(): PhysicsVector ={
    new PhysicsVector(minnowPoint.x, minnowPoint.y)
  }

  //takes a wall and makes a physics boundary
  def blockTile(x: Int, y: Int, width: Int = 1, height: Int = 1): Unit = {
    val ul = new PhysicsVector(x, y)
    val ur = new PhysicsVector(x + width, y)
    val lr = new PhysicsVector(x + width, y + height)
    val ll = new PhysicsVector(x, y + height)

    world.boundaries ::= new Boundary(ul, ur)
    world.boundaries ::= new Boundary(ur, lr)
    world.boundaries ::= new Boundary(lr, ll)
    world.boundaries ::= new Boundary(ll, ul)
  }

  //if a minnow is close enough to be eaten it becomes a shark
  def checkForTags(): Unit ={
    for(shark <- sharkList){
      for (minnow <- minnowList){
        if(shark.location.distance2d(minnow.location) <= shark.fishSize) {
          minnow.tag()
          minnowList -= minnow
          sharkList :+= minnow
        }
      }
    }
  }
  //respawn mechanism for minnows
  def checkForFinish(): Unit ={
    for(minnow <- minnowList){
      if (minnow.location.x > 29) {
        minnow.location.x = minnowPoint.x
        minnow.location.y = minnowPoint.y
      }
    }
  }
}