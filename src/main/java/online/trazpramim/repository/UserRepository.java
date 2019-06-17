package online.trazpramim.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import online.trazpramim.domain.User;

@Stateless
public class UserRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public List<User> findAll() {
				
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<User> query = builder.createQuery(User.class);
	    query.from(User.class);
	    return em.createQuery(query).getResultList();
	    
	}
	
	/*public User getNowUser(String token) {

		Query query = em.createQuery("from User where token = :token ");
		query.setParameter("token", token);
		
		return (User) query.getSingleResult();
		
	}*/
	
	
}

