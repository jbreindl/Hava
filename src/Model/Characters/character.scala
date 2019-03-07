package Model.Characters

abstract class character(movementSpeed: Double, dfs: Int, atk: Int, HP: Int){

  def basicAttack(enemy: character): Unit

  def special(character: character): Unit

  def takeDamage(dmg: Int): Unit
}