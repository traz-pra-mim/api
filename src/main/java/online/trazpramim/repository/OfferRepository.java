package online.trazpramim.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import online.trazpramim.domain.Interested;
import online.trazpramim.domain.Offer;
import online.trazpramim.domain.OfferDetails;
import online.trazpramim.domain.OfferType;

@Stateless
public class OfferRepository {

	@PersistenceContext
	EntityManager em;
	
	public boolean saveOffer(Offer offer) {
		
		em.persist(offer);
				
		return true;
		
	}
	
	public boolean saveOfferDetails(OfferDetails offerDetails) {
		
		em.persist(offerDetails);
				
		return true;
		
	}
	public List<Offer> findAllOffer() {
				
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Offer> query = builder.createQuery(Offer.class);
	    query.from(Offer.class);
	   
	    return (List<Offer>) em.createQuery(query).getResultList();				
	
	}
	

	public Offer findOffer(Integer id) throws Exception {
		
		if (id <= 0) {
			throw new Exception("Id inválido.");
		}
		
		return em.find(Offer.class, id);
		
	}
	
	public List<Offer> findOfferByType(Integer type) {
			
		return em.createQuery("SELECT o FROM Offer o WHERE o.offer_type_id = :type", Offer.class)
				.setParameter("type", type)
				.getResultList();
		
	}
	
	public OfferDetails findOfferDetails(Integer id) throws Exception {
		if (id <= 0) {
			throw new Exception("Id inválido.");
		}
		
		return (OfferDetails) em.createQuery("SELECT o FROM OfferDetails o WHERE o.id = :id")
				.setParameter("id", id)
				.getSingleResult();
		
		
	}
	
	public OfferType findOfferType(Integer id) throws Exception {
		if (id <= 0) {
			throw new Exception("Id inválido.");
		}
		
		return (OfferType) em.createQuery("SELECT o FROM OfferType o WHERE o.id = :id")
				.setParameter("id", id)
				.getSingleResult();
		
	}
	
	public boolean saveInterest(Interested interested) {
		
		em.persist(interested);
		
		return true;
	}
	
	public Interested findInterest(Integer user, Integer offer) throws Exception {
		try {
			Interested interested = (Interested) em.createQuery("SELECT o FROM Interested o WHERE o.user_id = :user AND o.offer_id = :offer")
					.setParameter("user", user)
					.setParameter("offer", offer)
					.getSingleResult();	
			return interested;
		} catch (Exception e) {
			throw new Exception("Deu errado");
		}
		
		
		
	}
	
	public Offer findOfferWithUser(Integer user, Integer offer) throws Exception {
		try {
			Offer _offer = (Offer) em.createQuery("SELECT o FROM Offer o WHERE o.user_id = :user AND o.id = :offer")
					.setParameter("user", user)
					.setParameter("offer", offer)
					.getSingleResult();	
			return _offer;
		} catch (Exception e) {
			throw new Exception("Deu errado");
		}
		
		
		
	}
}
