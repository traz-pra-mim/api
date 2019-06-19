package online.trazpramim.service;

import java.util.ArrayList;
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
			 offer = offerRepository.find(id);
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
	
	public List<OfferModel> findAll() throws Exception {
		List<Offer> offers = offerRepository.findAll();
		
		List<OfferModel> offersModel = new ArrayList<OfferModel>(); 
		
		for (Offer offer:  offers) {
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
			
			offersModel.add(offerModel);
		}
		
		return offersModel;
	}
	
	public List<OfferModel> findByType(Integer type) throws Exception {
		if (type <= 0 || type == null || type > 2) {
			throw new Exception("Id Inválido.");
		}
		
		
		List<Offer> offers = null;
		try {
			 offers  = offerRepository.findByType(type);
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		List<OfferModel> offersModel = new ArrayList<OfferModel>(); 
		
		for (Offer offer:  offers) {
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
			
			offersModel.add(offerModel);
		}		
			
		return offersModel;		
	}
	
	public void saveOffer(OfferModel offerModel) {
	
		Offer offer = new Offer();
		
		offer.setTitle(offerModel.getTitle());
		offer.setDescription(offerModel.getDescription());
		
		
		
	}
	
}
