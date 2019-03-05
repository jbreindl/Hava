package Model.Characters

import Model.Characters.character

class fast extends character(1.25, 1, 3, 6){
  override def basicAttack(character: Character): Unit = {
    character.getHit(atk)
    //need to figure out how to induce range
  }
  override def special(character: Character): Unit ={// "sprint" ability
    this.movementSpeed += 1.25// should only last for a few seconds, and have a cooldown
  }
}