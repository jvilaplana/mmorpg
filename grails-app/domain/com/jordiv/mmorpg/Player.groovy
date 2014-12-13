package com.jordiv.mmorpg

class Player {
	Date dateCreated
	Date lastUpdated
	Zone position
	String name
	
	/* ATTRIBUTES */
	Integer damageRecieved=0
	Integer manaSpent=0
	Integer level=1
	
	Integer strength=100
	Integer stamina=100
	Integer dexterity=30
	Integer agility=20
	Integer intelligence=50
	Integer moveCooldown=10
	
	def getMaxHealth(){
		return strength*level;
	}
	def getCurrentHealt(){
		return this.maxHealth-DamageRecieved
	}
	def getMaxMana(){
		return intelligence*level
	}
	def getCurrentMana(){
		return this.maxMana-manaSpent
	}
	
	static hasMany = [skills: PlayerSkill]
	
	static belongsTo = [user: User]
	
    static constraints = {
    }
}
