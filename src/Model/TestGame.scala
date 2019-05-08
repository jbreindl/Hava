package Model

import Model.Characters.Minnow
import Model.physics.PhysicsVector
import org.scalatest.FunSuite

class TestGame extends FunSuite{
  test("General Test"){
    val game = new Game

    game.placeWall(5,6)
    assert(game.walls(0).x == 5)
    assert(game.walls(0).y == 6)

    game.placeSharkWall(1,2)
    assert(game.sharkWalls(0).x == 1)
    assert(game.sharkWalls(0).y == 2)

    game.blockShark(1, 2, 3, 4)
    assert(game.world.boundaries.length == 12)

    game.addMinnow("test")
    assert(game.minnowList("test") != null)

    game.addShark("test2")
    assert(game.sharkList("test2") != null)

    game.tag(game.minnowList("test"))
    assert(game.sharkList("test") != null)

    game.reverseTag(game.sharkList("test"))
    assert(game.minnowList("test") != null)

    game.removePlayer("test")
    assert(game.minnowList.isEmpty)

//    game.resetplayers("test2")
//    assert(game.sharkList("test") != null)

    assert(game.sharkSpawn().x == game.sharkPoint.x)
    assert(game.sharkSpawn().y == game.sharkPoint.y)

    assert(game.MinnowSpawn().x == game.minnowPoint.x + 0.5)
    assert(game.MinnowSpawn().y == game.minnowPoint.y + 0.5)

    game.blockTile(5, 4, 3, 2)
    assert(game.world.boundaries.length == 16)
  }

}
