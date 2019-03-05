package Model.Characters

abstract class character(var movementSpeed: Double, var dfs: Int, var atk: Int,var  HP: Int){
  var hp = this.HP

  def basicAttack(enemy: Character): Unit={
  enemy.HP -= this.atk
  }

  def special(character: Character): Unit

  def getHit(dmg: Int): Unit ={
    this.HP -= dmg
  }

}