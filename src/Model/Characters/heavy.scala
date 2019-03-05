package Model.Characters

class heavy extends character(0.75, 3,1, 15){
  override def basicAttack(character: Character): Unit = {
    character.HP-=1
  }

  override def special(character: Character): Unit = {// defense buff for peeling
    this.dfs = 10
  }
}