package online.trazpramim.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import online.trazpramim.domain.User;
import online.trazpramim.repository.UserRepository;

@Stateless
public class UserService {

	@EJB
	UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
}
