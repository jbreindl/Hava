package Model

import Model.Characters.{Minnow, Shark, fish}
import Model.physics.{Boundary, GridLocation, Physics, PhysicsVector, Playground, Wall, World, sharkBoundary, sharkWall}
import play.api.libs.json.{JsValue, Json}


class Game {

  var world: World = new World(10)
  var sharkList: Map[String, Shark] = Map()
  var minnowList: Map[String, Minnow] = Map()
  val sharkPoint: GridLocation = new GridLocation(15, 5)
  val minnowPoint: GridLocation = new GridLocation(0, 5)
  var playground = new Playground
  var walls: List[Wall] = List()
  var lastUpdateTime: Long = System.nanoTime()
  var playerMap: Map[String, fish] = Map()
  var sharkWalls: List[sharkWall] = List()

  def loadLevel(newLevel: Playground): Unit = {
    world.boundaries = List()
    playground = newLevel
    walls = List()

    blockTile(0, 0, playground.gridWidth, playground.gridHeight)

    playground.sharkWalls.foreach(sharkYeet=> placeSharkWall(sharkYeet.x, sharkYeet.y))
  }

  def placeWall(x: Int, y: Int): Unit = {
    blockTile(x, y)
    walls = new Wall(x, y) :: walls
  }

  def placeSharkWall(x: Int, y:Int): Unit ={
    blockShark(x, y)
   sharkWalls :+= new sharkWall(x, y)
  }

  def blockShark(x: Int, y: Int, width: Int = 1, height: Int = 1): Unit ={
    val ul = new PhysicsVector(x, y)
    val ur = new PhysicsVector(x + width, y)
    val lr = new PhysicsVector(x + width, y + height)
    val ll = new PhysicsVector(x, y + height)

    world.sharkBoundaries :+= new sharkBoundary(ul, ur)
    world.sharkBoundaries :+= new sharkBoundary(ur, lr)
    world.sharkBoundaries :+= new sharkBoundary(lr, ll)
    world.sharkBoundaries:+= new sharkBoundary(ll, ul)
  }

  def tag(minnow:Minnow): Unit={
    val minnowID = minnow.id
    minnowList -= minnowID

    val newshark = new Shark(minnow.inputLocation, new PhysicsVector(0, 0))
    sharkList += (minnowID -> newshark)
  }

  def addShark(id: String): Unit ={
    val shark = new Shark(sharkSpawn(), new PhysicsVector(0,0))
    sharkList += (id-> shark)
    playerMap += (id -> shark)
    world.objects :+= shark
  }

  def addMinnow(id: String):Unit={
    val minnow = new Minnow(MinnowSpawn(), new PhysicsVector(0,0))
    minnowList += (id-> minnow)
    playerMap += (id -> minnow)
    world.objects :+= minnow
  }

  def removePlayer(id: String): Unit ={
    playerMap -= id
    if (sharkList.contains(id)){
      sharkList -= id
    }
    else{
      minnowList -= id
    }
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
    for(shark <- sharkList.values){
      for (minnow <- minnowList.values){
        if(shark.location.distance2d(minnow.location) <= shark.fishSize) {
          tag(minnow)
          minnowList -= minnow.id
          addShark(minnow.id)
        }
      }
    }
  }

  //respawn mechanism for minnows
  def checkForFinish(): Unit ={
    for(minnow <- minnowList.values){
      if (minnow.location.x > 29) {
        minnow.location.x = minnowPoint.x
        minnow.location.y = minnowPoint.y
      }
    }
  }



  def gameState(): String = {
    val gameState: Map[String, JsValue] = Map(
      "gridSize" -> Json.toJson(Map("x" -> playground.gridWidth, "y" -> playground.gridHeight)),
      "minnowStart" -> Json.toJson(Map("x" -> minnowPoint.x, "y" -> minnowPoint.y)),
      "sharkStart" -> Json.toJson(Map("x" -> sharkPoint.x, "y" -> minnowPoint.y)),
      "sharkWalls" -> Json.toJson(this.sharkWalls.map({ w => Json.toJson(Map("x" -> w.x, "y" -> w.y)) })),
      "players" -> Json.toJson(this.playerMap.map({ case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.location.x),
        "y" -> Json.toJson(v.location.y),
        "v_x" -> Json.toJson(v.velocity.x),
        "v_y" -> Json.toJson(v.velocity.y),
        "id" -> Json.toJson(k))) })),

      "minnows" -> Json.toJson(this.minnowList.map({case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.location.x),
        "y" -> Json.toJson(v.location.y),
        "v_x" -> Json.toJson(v.velocity.x),
        "v_y" -> Json.toJson(v.velocity.y),
        "id" -> Json.toJson(k))) })),

      "shark"-> Json.toJson(this.sharkList.map({case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.location.x),
        "y" -> Json.toJson(v.location.y),
        "v_x" -> Json.toJson(v.velocity.x),
        "v_y" -> Json.toJson(v.velocity.y),
        "id" -> Json.toJson(k))) }))
    )
    Json.stringify(Json.toJson(gameState))
  }

}