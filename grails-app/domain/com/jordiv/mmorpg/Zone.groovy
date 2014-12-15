package com.jordiv.mmorpg

class Zone {
	
	static belongsTo = [layer: Layer]
	Integer posx
	Integer posy
	
    static constraints = {
    }
}
