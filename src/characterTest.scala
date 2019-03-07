import org.scalatest._

class characterTest extends FunSuite {
  test("attack functions") {

    val heavy1 = new Model.Characters.heavy
    val heavy2 = new Model.Characters.heavy // heavies for testing

    val fast1= new Model.Characters.fast
    val fast2= new Model.Characters.fast

    val sniper1 = new Model.Characters.sniper()
    val sniper2 = new Model.Characters.sniper()

    heavy1.basicAttack(heavy2)
    heavy1.basicAttack(fast2)
    heavy1.basicAttack(sniper2)// run heavy basic attacks

    assert(heavy2.HP==119)
    assert(fast2.HP==55)
    assert(sniper2.HP==90)//test heavy basics

    fast1.basicAttack(heavy2)
    fast1.basicAttack(fast2)
    fast1.basicAttack(sniper2)// run fast basics

    assert(heavy2.HP==106)
    assert(fast2.HP == 15)
    assert(sniper2.HP== 70)// test fast basics

    sniper1.basicAttack(heavy2)
    sniper1.basicAttack(fast1)
    sniper1.basicAttack(sniper2)// run sniper basics

    assert(heavy2.HP==93)
    assert(fast1.HP==35)
    assert(sniper2.HP==50)// test sniper basics

    fast1.special(fast1)
    assert(fast1.movementSpeed==2.5)// "sprint" test

    heavy1.special(heavy1)
    assert(heavy1.dfs==10)

    sniper1.special(heavy1)
    assert(heavy1.HP==124)
    }
}