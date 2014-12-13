package com.jordiv.mmorpg



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), [status: OK]
    }
	
    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            render status: NOT_FOUND
            return
        }

        userInstance.validate()
        if (userInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        userInstance.save flush:true
        respond userInstance, [status: CREATED]
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            render status: NOT_FOUND
            return
        }

        userInstance.validate()
        if (userInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        userInstance.save flush:true
        respond userInstance, [status: OK]
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            render status: NOT_FOUND
            return
        }

        userInstance.delete flush:true
        render status: NO_CONTENT
    }
	def createPlayer(User userInstance,String playerName){
		if (userInstance == null) {
			render status: NOT_FOUND
			return
		}
		Player newp=new Player()
		newp.setName(playerName)
		userInstance.addToPlayers(newp)
		userInstance.save flush:true
		
	}
	
	def listPlayers(User userInstance){
		if(userInstance==null){
			render status: NOT_FOUND
			return
		}
		def playerList=[:]
		for(Player p:players){
			playerList[p.getName]=p.getLevel
		}
		return playerList
		
	}
}
