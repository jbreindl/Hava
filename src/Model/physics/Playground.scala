package Model.physics

class Playground {
  var yeetWalls: List[sharkWall] = List()
  var gridWidth: Int = 30
  var gridHeight: Int = 10
}

object Playground {
    new Playground{
      yeetWalls = List (
    new sharkWall(0, 29),
    new sharkWall(1, 29),
    new sharkWall(2, 29),
    new sharkWall(3, 29),
    new sharkWall(4,29),
    new sharkWall(5,29),
    new sharkWall(6, 29),
    new sharkWall(7,29),
    new sharkWall(8,29),
    new sharkWall(9, 29),
    new sharkWall(10,29),

    new sharkWall(0, 1),
    new sharkWall(1, 1),
    new sharkWall(2, 1),
    new sharkWall(3, 1),
    new sharkWall(4, 1),
    new sharkWall(5, 1),
    new sharkWall(6, 1),
    new sharkWall(7, 1),
    new sharkWall(8, 1),
    new sharkWall(9, 1),
    new sharkWall(10, 1)
  )
}}
