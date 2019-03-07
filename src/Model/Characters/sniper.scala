package Model.Characters

class sniper(var movementSpeed: Double= 0.8, var dfs: Int = 2, var atk: Int = 2, var HP: Int = 100)
  extends character(movementSpeed: Double, dfs: Int, atk: Int, HP: Int){

  override def basicAttack(enemy: character): Unit={
    enemy.takeDamage(20*this.atk)
  }

  override def special(enemy: character): Unit ={
    enemy.takeDamage(10)
  }

  override def takeDamage(dmg: Int): Unit={
    this.HP-= dmg/this.dfs
  }
}