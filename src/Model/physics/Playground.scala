package Model.physics

class Playground {
  var walls: List[Wall] = List()
  var gridWidth: Int = 30
  var gridHeight: Int = 10
}

object Playground {
  var walls = List (
    new Wall(0, 29),
    new Wall(1, 29),
    new Wall(2, 29),
    new Wall(3, 29),
    new Wall(4,29),
    new Wall(5,29),
    new Wall(6, 29),
    new Wall(7,29),
    new Wall(8,29),
    new Wall(9, 29),
    new Wall(10,29),

    new Wall(0, 1),
    new Wall(1, 1),
    new Wall(2, 1),
    new Wall(3, 1),
    new Wall(4, 1),
    new Wall(5, 1),
    new Wall(6, 1),
    new Wall(7, 1),
    new Wall(8, 1),
    new Wall(9, 1),
    new Wall(10, 1)
  )
}