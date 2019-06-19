package online.trazpramim.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import online.trazpramim.domain.User;
import online.trazpramim.model.UserDataModel;
import online.trazpramim.service.UserService;

@Stateless
@Path("user")
public class UserResource {
	
	@EJB
	UserService userService;
	
	@GET
    public String findAll() {
        
		User la = new User();
		la.setName("Igor");
			
		
		List<User> data = userService.findAll();
		
		//data.add(la);
		
        return new Gson().toJson(data);                          
    }
	
	@POST
	public Response createUser(UserDataModel userDataModel) { 
		
		try {
			userService.createUser(userDataModel);			
			return Response.created(null).build();
		} catch (Exception e) {
			return Response.noContent().build();
		} 
		
		
	}

}
