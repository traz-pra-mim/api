package online.trazpramim.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import online.trazpramim.domain.User;

@Stateless
public class UserService {
	
	@PersistenceContext
	EntityManager em;
	

	
	public User findAll() {
		User la = new User();
		la.setName("Igor");
		
		em.persist(la);
		
		
		User lu = em.find(User.class, 2);
		return lu;		
	}
	
	
}

