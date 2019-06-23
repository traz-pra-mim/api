package online.trazpramim.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import online.trazpramim.domain.Address;
import online.trazpramim.domain.Country;
import online.trazpramim.domain.Offer;
import online.trazpramim.domain.OfferDetails;
import online.trazpramim.domain.State;
import online.trazpramim.model.OfferModel;
import online.trazpramim.repository.AddressRepository;
import online.trazpramim.repository.OfferRepository;

@Stateless
public class OfferService {

	@EJB
	OfferRepository offerRepository;
	
	@EJB
	AddressRepository addressRepository;
	
	public OfferModel find(Integer id) throws Exception {
		if (id <= 0 || id == null) {
			throw new Exception("Id Inválido.");
		}
		
		Offer offer = null;
		
		try {
			 offer = offerRepository.findOffer(id);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		OfferModel offerModel = new OfferModel(offer);
		
		Address address = addressRepository.find(offer.getAddress_id());
		
		offerModel.setNeighborhood(address.getNeighborhood());
		
		State state = addressRepository.findState(address.getState_id());
		
		offerModel.setState(state.getState());
		
		Country country = addressRepository.findCountry(state.getCountry_id()); 
		
		offerModel.setCountry(country.getName());
		
		OfferDetails offerDetails = offerRepository.findOfferDetails(offer.getOffer_details_id());
		
		offerModel.setPrice(offerDetails.getMax_price());
		offerModel.setWeight(offerDetails.getMax_weight());
	
		offerModel.setType(offerRepository.findOfferType(offer.getOffer_type_id()).getName());		

			
		return offerModel;		
	}
	
	public List<OfferModel> findALot(Integer type) throws Exception {
		List<Offer> offers = null;
		if (type == null) {
	
			offers = offerRepository.findAllOffer();				
		}else {
			offers  = offerRepository.findOfferByType(type);
		}
		
		
		List<OfferModel> offersModel = new ArrayList<OfferModel>(); 
		
		for (Offer offer:  offers) {
			OfferModel offerModel = new OfferModel(offer);
					
			Address address = addressRepository.find(offer.getAddress_id());
			
			offerModel.setNeighborhood(address.getNeighborhood());
			offerModel.setCity(address.getCity());
			
			State state = addressRepository.findState(address.getState_id());
			
			offerModel.setState(state.getState());
			
			Country country = addressRepository.findCountry(state.getCountry_id()); 
			
			offerModel.setCountry(country.getName());
			
			OfferDetails offerDetails = offerRepository.findOfferDetails(offer.getOffer_details_id());
			
			offerModel.setPrice(offerDetails.getMax_price());
			offerModel.setWeight(offerDetails.getMax_weight());
		
			offerModel.setType(offerRepository.findOfferType(offer.getOffer_type_id()).getName());
			
			offersModel.add(offerModel);
		}
		
		return offersModel;
	}
	
	
	public void saveOffer(OfferModel offerModel) throws ParseException {
	
		Offer offer = new Offer();		
		
		offer.setTitle(offerModel.getTitle());
		offer.setDescription(offerModel.getDescription());
		offer.setOffer_type_id(Integer.parseInt(offerModel.getType()));
		
		Address address = new Address();
		
		address.setNeighborhood(offerModel.getNeighborhood());
		address.setCity(offerModel.getCity());
		address.setState_id(Integer.parseInt(offerModel.getState()));
				
		addressRepository.save(address);
		
		offer.setAddress_id(address.getId());
		
		OfferDetails offerDetails = new OfferDetails();
		
		offerDetails.setMax_price(offerModel.getPrice());
		offerDetails.setMax_weight(offerModel.getWeight());
		
		offerDetails.setArrival_day(new SimpleDateFormat("dd/MM/yyyy").parse(offerModel.getArrival()));
		offerDetails.setDeparture_day(new SimpleDateFormat("dd/MM/yyyy").parse(offerModel.getDeparture()));
		
		offerRepository.saveOfferDetails(offerDetails);
		
		offer.setOffer_details_id(offerDetails.getId());			
		
		offer.setCreated_at(new Date());
				
		offerRepository.saveOffer(offer);
	}
	
}
