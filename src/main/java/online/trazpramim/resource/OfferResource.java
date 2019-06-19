package online.trazpramim.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import online.trazpramim.model.OfferModel;
import online.trazpramim.service.OfferService;

@Stateless
@Path("offer")
public class OfferResource {

	@EJB
	OfferService offerService;
	
	@Path("/type/{type}")
	@GET
	public String findByType(@PathParam("type") Integer type) {
		
		List<OfferModel> data = null;
		try {
			data = offerService.findByType(type);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return new Gson().toJson(data);
	}
	
	
	@Path("/{id}")
	@GET
	public String find(@PathParam("id") Integer id) {
		
		OfferModel data = null;
		try {
			data = offerService.find(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Gson().toJson(data);
	}
	
	@GET
	public String findAll() {
		
		List<OfferModel> data = null;
		try {
			data = offerService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return new Gson().toJson(data);
	}
	
	@POST
	public Response saveOffer(OfferModel offer) {
		
		try {
			offerService.saveOffer(offer);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return Response.created(null).build();
	}
	
}
