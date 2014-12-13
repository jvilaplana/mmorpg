package com.jordiv.mmorpg

abstract class Zone {
	
	static belongsTo = [layer: Layer]
	String type;
	Zone north
	Zone south
	Zone east
	Zone west
	
    static constraints = { north nullable:true
					 	   south nullable:true
						   east nullable:true
						   west nullable:true
    }
}
