package com.jordiv.mmorpg

class PlayerMap {
	Integer type
	String observations
	
	static belongsTo = [player: Player, zone: Zone]
	
    static constraints = {
    }
}
