package com.jordiv.mmorpg

class LayerMap {
	static belongsto=[layer:Layer,player:Player]
	static hasmany=[zones:PlayerZone]
	static constraints = {
	}
}
