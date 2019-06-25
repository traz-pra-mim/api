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
	
	public User find(Integer id) {
		return em.find(User.class, id);
	}
	
	public User getNowUser(String token) throws Exception {

		if (token.length() <= 0 || token == null) {
			throw new Exception("Token inválido.");
		}
		
		return (User) em.createQuery("SELECT u FROM User u WHERE u.token = :token")
				.setParameter("token", token)
				.getSingleResult();
		
	}
	
	public boolean saveUser(User user) {
		
		em.persist(user);
				
		return true;
		
	}

	public User getTokenByEmailAndPass(String email, String password) {
		return (User) em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
				.setParameter("email", email)
				.setParameter("password", password)
				.getSingleResult();
	}
	
	
}

