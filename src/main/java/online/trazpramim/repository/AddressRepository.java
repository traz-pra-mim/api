package online.trazpramim.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import online.trazpramim.domain.Address;
import online.trazpramim.domain.Offer;

public class AddressRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public boolean save(Offer offer) {
		
		em.persist(offer);
		
		return true;
		
	}
	
	public List<Address> findAll() {
				
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Address> query = builder.createQuery(Address.class);
	    query.from(Address.class);
	    return em.createQuery(query).getResultList();				
	
	}
	
	public Address find(Integer id) throws Exception {
		
		if (id <= 0) {
			throw new Exception("Id inválido.");
		}
		
		return em.find(Address.class, id);
		
	}
	
}
