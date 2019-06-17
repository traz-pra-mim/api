package online.trazpramim.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import online.trazpramim.domain.Offer;
import online.trazpramim.model.OfferModel;
import online.trazpramim.repository.OfferRepository;

@Stateless
public class OfferService {

	@EJB
	OfferRepository offerRepository;
	
	public OfferModel find(Integer id) {
			
		Offer offer = null;
		
		try {
			 offer = offerRepository.find(id);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		OfferModel offerModel = new OfferModel(offer);
		
		
		
		return offerModel;		
	}
	
	
}
