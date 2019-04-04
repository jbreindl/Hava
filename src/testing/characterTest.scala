package testing

import org.scalatest._
import Model.Characters._
import Model.Characters.states.playerStates._

class characterTest extends FunSuite {
test("do the fish die"){
  val minnow1: Minnow = new Minnow
  val minnow2: Minnow= new Minnow
  val shark: Shark = new Shark

  //comparator fish
  val deadFish: Minnow = new Minnow
  val safeFish: Minnow = new Minnow


  safeFish.safe()
  deadFish.die()//have to make this fish because scala won't let me assert that a tagged fish is dead
  shark.tag(minnow1)
  minnow2.safe()

  assert(minnow1.fishType == deadFish.fishType)//they both say dead but they give different ram locations so they don't work
  assert(minnow2.fishType == safeFish.fishType)//this test is pretty pointless but both return an out of play in the little ram error thing
}
}