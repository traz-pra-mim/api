package online.trazpramim.resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import online.trazpramim.model.OfferModel;
import online.trazpramim.service.OfferService;

@Stateless
@Path("offer")
public class OfferResource {

	@EJB
	OfferService offerService;
	
	@GET
	public String find() {
		
		OfferModel data = offerService.find(1);
		
		return new Gson().toJson(data);
	}
	
}
