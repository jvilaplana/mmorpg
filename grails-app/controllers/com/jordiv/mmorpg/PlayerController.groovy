package com.jordiv.mmorpg



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlayerController {
	def utilsService
    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Transactional
    def save(Player playerInstance) {
        if (playerInstance == null) {
            render status: NOT_FOUND
            return
        }
		User u=User.findByUsername(principal.username)
		Zone z=Zone.findByPosxAndPosy(0,0)
		print(z)
		playerInstance.position=z;
		playerInstance.user=u
		playerInstance.validate()
		if (playerInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }
		
		playerInstance.save flush: true
		
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
	def move(){
		def jsonObject = request.JSON
		Player player=Player.findById(jsonObject.player)
		if(player.user.username!=principal.username){
			render status: NOT_ACCEPTABLE
			return
		}
		int movementControl=Math.abs(
							Math.abs(player.position.posx-Integer.parseInt(jsonObject.posx))+
							Math.abs(player.position.posy-Integer.parseInt(jsonObject.posy))
							)
		if(movementControl<=1){
			Zone currentZone = Zone.findByPosxAndPosy(player.position.posx,player.position.posy)
			Zone newZone=Zone.findByPosxAndPosy(jsonObject.posx,jsonObject.posy)
			if(newZone==null){
				def namez=utilsService.getAllZonesType()
				Random r=new Random();
				newZone=Class.forName(namez[r.nextInt(namez.size())]).newInstance()
				newZone.posx=Integer.parseInt(jsonObject.posx)
				newZone.posy=Integer.parseInt(jsonObject.posy)
				newZone.layer=player.position.layer
				newZone.save()
			}
			player.position=newZone
			player.save()
			render status: OK
			return
		}else{
			render status: NOT_ACCEPTABLE
			return	
		}
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
	
	
	@Transactional
	def list(){
		User userInstance= User.findByUsername(principal.username)
		respond Player.findAllByUser(userInstance)		
	}

}
