package online.trazpramim.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import online.trazpramim.domain.User;

@Stateless
public class UserService {
	
	@PersistenceContext
	EntityManager em;
	

	
	public List<User> findAll() {
		User la = new User();
		la.setName("Igor");
		
		em.persist(la);
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<User> query = builder.createQuery(User.class);
	    query.from(User.class);
	    return em.createQuery(query).getResultList();				
	}
	
	
}

