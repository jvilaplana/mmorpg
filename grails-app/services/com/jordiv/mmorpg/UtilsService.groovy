package com.jordiv.mmorpg

import grails.transaction.Transactional

@Transactional
class UtilsService {
	def grailsApplication
	
	def getAllZonesType() {
		def zones = []
		grailsApplication.domainClasses.each { 
			if (it.clazz.superclass.simpleName == 'Zone') {
				zones << it.clazz.toString().split(" ")[1]
			}
		}
		return zones
	}
}
