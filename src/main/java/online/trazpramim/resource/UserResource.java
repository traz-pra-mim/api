package online.trazpramim.resource;

import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import online.trazpramim.model.InterestedModel;
import online.trazpramim.model.UserDataModel;
import online.trazpramim.service.OfferService;
import online.trazpramim.service.UserService;

@Stateless
@Path("user")
public class UserResource {
	
	@EJB
	UserService userService;
	
	@EJB
	OfferService offerService;
	
	@Path("/register")
	@POST
	public Response createUser(UserDataModel userDataModel) { 
		
		try {
			userService.createUser(userDataModel);			
			return Response.created(null).build();
		} catch (Exception e) {
			return Response.noContent().build();
		} 
		
		
	}
	
	@POST
	public String getUserData(UserDataModel userDataModel) { 
		
		UserDataModel data = null;
		
		data = userService.findUser(userDataModel.getToken());
		
		return new Gson().toJson(data);
		
		
	}
	
	@Path("/authorized")
	@POST
	public boolean authorized(InterestedModel interestedModel) {
		
		boolean check = offerService.alreadyIsInterested(interestedModel);
		
		return check;
	}
	
	@Path("/login")
	@POST
	public String login(UserDataModel userDataModel) {
		
		UserDataModel token = null;
		try {
			token = userService.login(userDataModel);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return new Gson().toJson(token);
	}
	
	@Path("/delete/{token}")
	@POST
	public Response delete(@PathParam("token") String token) {
		
		try {
			userService.delUser(token);
			return Response.ok().build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.notModified().build();
		}
	}
	

}
