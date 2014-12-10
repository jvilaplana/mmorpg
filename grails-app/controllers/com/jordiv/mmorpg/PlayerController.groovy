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
}
