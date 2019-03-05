package Model.Characters

class sniper extends character(0.9, 2, 2, 5){

  def basicAttack: Unit

  override def special(character: Character): Unit ={
    character.HP -= 6
  }
}