package com.jordiv.mmorpg

class PlayerSkill {
	
	Integer usage
	
	static belongsTo = [player: Player, skill: Skill]

    static constraints = {
    }
}
