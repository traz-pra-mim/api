package online.trazpramim.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import online.trazpramim.domain.Address;
import online.trazpramim.domain.Country;
import online.trazpramim.domain.State;

@Stateless
public class AddressRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public boolean save(Address address) {
		
		em.persist(address);
		
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
	
	public State findState(Integer id) throws Exception {
		if (id <= 0) {
			throw new Exception("Id inválido.");
		}
		
		return (State) em.createQuery("SELECT s FROM State s WHERE s.id = :id")
				.setParameter("id", id)
				.getSingleResult();
		
		
	}
	
	public Country findCountry(Integer id) throws Exception {
		if (id <= 0) {
			throw new Exception("Id inválido.");
		}
		
		return (Country) em.createQuery("SELECT c FROM Country c WHERE c.id = :id")
				.setParameter("id", id)
				.getSingleResult();
		
		
	}
	
	
}
