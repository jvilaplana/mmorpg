package com.jordiv.mmorpg

class Layer {
	static hasMany = [zone: Zone]
	
	static belongsTo = [world: World]
	
	static constraints = {
	}
}
 