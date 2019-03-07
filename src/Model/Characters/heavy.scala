package Model.Characters

class heavy(var movementSpeed: Double = 0.75, var dfs: Int = 3, var atk: Int= 1, var HP: Int =125 )
  extends character(movementSpeed: Double, dfs: Int, atk: Int, HP: Int){

  def basicAttack(enemy: character): Unit={
    enemy.takeDamage(20*this.atk)
  }

  override def special(character: character): Unit = {// defense buff for peeling
    this.dfs = 10
  }

  override def takeDamage(dmg:Int):Unit={
    this.HP-= dmg/this.dfs
  }
}