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
	@Transactional
	def createPlayer(User userInstance){
		if (userInstance == null) {
			render status: NOT_FOUND
			return
		}
		Player newp=new Player()
		newp.setName("Pagafantes")
		userInstance.addToPlayers(newp)
		print(userInstance.players)
		respond [:]
	}
	@Transactional
	def listPlayers(User userInstance){
		if(userInstance==null){
			render status: NOT_FOUND
			return
		}
		def playerList=[:]
		if(userInstance.players!=null){
			for(Player p:userInstance.players){
				print(p.name)
				playerList[p.getName]=p.getLevel
			}	
		}
		print("returning list")
		respond playerList
		
	}
}
