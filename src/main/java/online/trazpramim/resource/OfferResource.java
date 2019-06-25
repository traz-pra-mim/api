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

import online.trazpramim.model.InterestedModel;
import online.trazpramim.model.OfferModel;
import online.trazpramim.model.UserDataModel;
import online.trazpramim.service.OfferService;
import online.trazpramim.service.UserService;
import online.trazpramim.service.util.EmailService;

@Stateless
@Path("offer")
public class OfferResource {

	@EJB
	OfferService offerService;
	
	@EJB
	EmailService emailService;
	
	@EJB
	UserService userService;
	
	@Path("/type/{type}")
	@GET
	public String findByType(@PathParam("type") Integer type) {
		
		List<OfferModel> data = null;
		try {
			data = offerService.findALot(type);
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
		
		emailService.send("igornogueir@gmail.com", "Traz pra mim", "Recebido email.");
		
		List<OfferModel> data = null;
		try {
			//data = offerService.findALot(null);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return new Gson().toJson(data);
	}
	
	@POST
	public Response saveOffer(OfferModel offer) {
		
		try {
			offerService.saveOffer(offer);
			return Response.created(null).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.noContent().build();
		}	
		
	}
	
	@Path("/interest")
	@POST
	public Response saveInterest(InterestedModel interestedModel) {
		
		try {
			offerService.saveInterest(interestedModel);
			
			UserDataModel user = userService.findUser(interestedModel.getToken());
			
			OfferModel offer = offerService.find(interestedModel.getOffer());
			
			String subject = "Traz pra mim - Interesse cadastrado.";
			
			String body = "Olá, " + user.getName() + "!\n";
			body += "Seu interesse na ofertar '" + offer.getTitle() + "' foi cadastrado com sucesso.";
			body += "\n\nAtt,\nEquipe Traz Pra Mim";
			
			String destiny = user.getEmail();
			
			emailService.send(destiny, subject, body);
			
			return Response.created(null).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.noContent().build();
		}	
	}
}
