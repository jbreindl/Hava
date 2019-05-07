package Model.physics

class Playground {
  var yeetWalls: List[GridLocation] = List()
  var gridWidth: Int = 30
  var gridHeight: Int = 10
}

object Playground {
    new Playground{
      yeetWalls = List (
    new GridLocation(0, 29),
    new GridLocation(1, 29),
    new GridLocation(2, 29),
    new GridLocation(3, 29),
    new GridLocation(4,29),
    new GridLocation(5,29),
    new GridLocation(6, 29),
    new GridLocation(7,29),
    new GridLocation(8,29),
    new GridLocation(9, 29),
    new GridLocation(10,29),

    new GridLocation(0, 1),
    new GridLocation(1, 1),
    new GridLocation(2, 1),
    new GridLocation(3, 1),
    new GridLocation(4, 1),
    new GridLocation(5, 1),
    new GridLocation(6, 1),
    new GridLocation(7, 1),
    new GridLocation(8, 1),
    new GridLocation(9, 1),
    new GridLocation(10, 1)
  )
}}
