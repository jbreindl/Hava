package Model.Characters

class fast(var movementSpeed: Double = 1.25,var dfs: Int =1, var atk: Int =2, var HP: Int =75 )
  extends character(movementSpeed, dfs, atk, HP){
  override def basicAttack(enemy:character): Unit={
    enemy.takeDamage(20*this.atk)
  }

  //need to figure out how to induce range
  override def special(character:character): Unit = {
    // "sprint" ability
    this.movementSpeed += 1.25 // should only last for a few seconds, and have a cooldown
  }

  override def takeDamage(dmg: Int): Unit = {
    this.HP -= dmg/this.dfs
  }

}