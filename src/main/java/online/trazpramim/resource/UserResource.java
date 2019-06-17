package online.trazpramim.resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import online.trazpramim.domain.User;
import online.trazpramim.service.UserService;

@Stateless
@Path("auth")
public class UserResource {
	
	@EJB
	UserService userService;
	
	@GET
    public String findAll() {
        
		User la = new User();
		la.setName("Igor");
			
		
		User data = userService.findAll();
		
		//data.add(la);
		
        return new Gson().toJson(data);                          
    }

}
