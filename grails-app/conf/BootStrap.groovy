import com.jordiv.mmorpg.Role
import com.jordiv.mmorpg.User
import com.jordiv.mmorpg.UserRole
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
		
		boolean createBootStrapData = true
		boolean createBootStrapDataProd = true
		
		switch(Environment.current){
			case Environment.DEVELOPMENT:
			  println "Development Mode (Start Up)"
			  if(createBootStrapData) bootStrapData()
			  break
			case Environment.TEST:
			  println "Test Mode (Start Up)"
			  break
			case Environment.PRODUCTION:
				if(createBootStrapData && createBootStrapDataProd) bootStrapData()
			  println "Production Mode (Start Up)"
			  break
		  }
    }
    def destroy = {
    }
	
	def bootStrapData() {
		log.debug "Generating bootstrap data..."
		
		// Default users and roles
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		User adminUser = new User(username: 'admin', password: 'admin1', accountExpired: false, accountLocked: false, passwordExpired: false).save(flush: true)
		UserRole.create adminUser, adminRole
		log.debug "\tAdmin role and user... [OK]"
		
		log.debug "Generating bootstrap data... [OK]"
	}
}
