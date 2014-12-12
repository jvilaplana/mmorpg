package com.jordiv.mmorpg.auth

class AuthenticationToken {
	Date dateCreated
	Date lastUpdated
	Integer accessCount = 0
	
	String tokenValue
	String username
	
	def afterLoad() {
		accessCount++
	}
	
	static mapping = {
		version false
	}
	
    static constraints = {
    }
}
