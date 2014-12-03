package com.jordiv.mmorpg

class Player {
	Date dateCreated
	Date lastUpdated
	
	String name
	
	/* ATTRIBUTES */
	Integer maxHealth
	Integer currentHealth
	Integer maxMana
	Integer currentMana
	
	Integer moveCooldown 
	
	Integer strength
	Integer stamina
	Integer dexterity
	Integer agility
	Integer intelligence
	
	static hasMany = [skills: PlayerSkill]
	
	static belongsTo = [user: User]
	
    static constraints = {
    }
}
