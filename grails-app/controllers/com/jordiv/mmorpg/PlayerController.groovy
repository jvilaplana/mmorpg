package com.jordiv.mmorpg



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlayerController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Player.list(params), [status: OK]
    }

    @Transactional
    def save(Player playerInstance) {
        if (playerInstance == null) {
            render status: NOT_FOUND
            return
        }

        playerInstance.validate()
        if (playerInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        playerInstance.save flush:true
		playerInstance.setDateCreated(new Date())
        respond playerInstance, [status: CREATED]
    }

    @Transactional
    def update(Player playerInstance) {
        if (playerInstance == null) {
            render status: NOT_FOUND
            return
        }

        playerInstance.validate()
        if (playerInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        playerInstance.save flush:true
		playerInstance.getLastUpdate(new Date())
        respond playerInstance, [status: OK]
    }

    @Transactional
    def delete(Player playerInstance) {

        if (playerInstance == null) {
            render status: NOT_FOUND
            return
        }

        playerInstance.delete flush:true
        render status: NO_CONTENT
    }
	
	def moveNorth(Player playerInstance){
		if (playerInstance==null){
			render status: NOT_FOUND
			return
		}
		Zone pos=playerInstance.getPosition()
		if(pos.getNorh()!=null){
			playerInstance.setPosition=pos.getNorth()
		}else{
		//TODO Crear una posicio nova
		}
		playerInstance.save flush:true
	}
	
	def moveSouth(Player playerInstance){
		if (playerInstance==null){
			render status: NOT_FOUND
			return
		}
		Zone pos=playerInstance.getPosition()
		if(pos.getSouth()!=null){
			playerInstance.setPosition=pos.getSouth()
		}else{
		//TODO Crear una posicio nova
		}
		playerInstance.save flush:true
	}
	
	def moveEast(Player playerInstance){
		if (playerInstance==null){
			render status: NOT_FOUND
			return
		}
		Zone pos=playerInstance.getPosition()
		if(pos.getEast()!=null){
			playerInstance.setPosition=pos.getEast()
		}else{
		//TODO Crear una posicio nova
		}
		playerInstance.save flush:true
	}
	
	def moveWest(Player playerInstance){
		if (playerInstance==null){
			render status: NOT_FOUND
			return
		}
		Zone pos=playerInstance.getPosition()
		if(pos.getWest()!=null){
			playerInstance.setPosition=pos.getWest()
		}else{
		//TODO Crear una posicio nova
		}
		playerInstance.save flush:true
	}
}
