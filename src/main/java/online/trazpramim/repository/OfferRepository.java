package online.trazpramim.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import online.trazpramim.domain.Address;
import online.trazpramim.domain.Offer;

@Stateless
public class OfferRepository {

	@PersistenceContext
	EntityManager em;
	
	public boolean save(Offer offer) {
		
		em.persist(offer);
		
		return true;
		
	}
	
	public List<Offer> findAll() {
				
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Offer> query = builder.createQuery(Offer.class);
	    query.from(Offer.class);
	    return em.createQuery(query).getResultList();				
	
	}
	

	public Offer find(Integer id) throws Exception {
		
		if (id <= 0) {
			throw new Exception("Id inválido.");
		}
		
		return em.find(Offer.class, id);
		
	}
	
	
}
