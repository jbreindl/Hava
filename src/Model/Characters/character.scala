package Model.Characters

abstract class character(var movementSpeed: Double, var dfs: Int, var atk: Int,var  HP: Int){

  def basicAttack(character: Character): Unit={
  character.HP -= atk
  }

  def special(character: Character): Unit

}