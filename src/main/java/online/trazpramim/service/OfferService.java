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
import online.trazpramim.domain.Interested;
import online.trazpramim.domain.Offer;
import online.trazpramim.domain.OfferDetails;
import online.trazpramim.domain.State;
import online.trazpramim.domain.User;
import online.trazpramim.model.InterestedModel;
import online.trazpramim.model.OfferModel;
import online.trazpramim.repository.AddressRepository;
import online.trazpramim.repository.OfferRepository;
import online.trazpramim.repository.UserRepository;

@Stateless
public class OfferService {

	@EJB
	UserRepository userRepository;
	
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
		offerModel.setCity(address.getCity());
		
		State state = addressRepository.findState(address.getState_id());
		
		offerModel.setState(state.getState());
		
		Country country = addressRepository.findCountry(state.getCountry_id()); 
		
		offerModel.setCountry(country.getName());
		
		OfferDetails offerDetails = offerRepository.findOfferDetails(offer.getOffer_details_id());
		
		offerModel.setPrice(offerDetails.getMax_price());
		offerModel.setWeight(offerDetails.getMax_weight());
		offerModel.setArrival(offerDetails.getArrival_day().toString());
		offerModel.setDeparture(offerDetails.getDeparture_day().toString());
	
		offerModel.setType(offerRepository.findOfferType(offer.getOffer_type_id()).getName());		
		
		if (offer.getUser_id() != null) { 
			User user = userRepository.find(offer.getUser_id());
			
			offerModel.setUser(user.getName());		
		}
		
	
			
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
			
			offerModel.setId(offer.getId());
			
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

		try {
			User user = userRepository.getNowUser(offerModel.getUser());
			
			offer.setUser_id(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		offerRepository.saveOffer(offer);
	}
	
	public boolean saveInterest(InterestedModel interestedModel) {
		
		User user = null;
		try {
			user = userRepository.getNowUser(interestedModel.getToken());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Offer offer = null;
		try {
			offer = offerRepository.findOffer(interestedModel.getOffer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (user == null || offer == null) {
			return false;
		}
		
		Interested interested = new Interested();
		
		interested.setOffer_id(interestedModel.getOffer());
		interested.setUser_id(user.getId());
		
		offerRepository.saveInterest(interested);
		
		return true;
	}
	
	public boolean alreadyIsInterested(InterestedModel interestedModel) {
		
		try {
			User user = userRepository.getNowUser(interestedModel.getToken());
			
			offerRepository.findInterest(user.getId(), interestedModel.getOffer());
			offerRepository.findOfferWithUser(user.getId(), interestedModel.getOffer());			
			
			return true;	
		} catch (Exception e) {
			return false;
		}
		
		
		
	}
	
}
