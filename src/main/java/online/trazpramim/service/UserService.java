package online.trazpramim.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import online.trazpramim.domain.Address;
import online.trazpramim.domain.User;
import online.trazpramim.model.UserDataModel;
import online.trazpramim.repository.AddressRepository;
import online.trazpramim.repository.UserRepository;

@Stateless
public class UserService {

	@EJB
	UserRepository userRepository;
	
	@EJB
	AddressRepository addressRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public void createUser(UserDataModel userDataModel) throws ParseException, NoSuchAlgorithmException {
		
		User user = new User();
		
		user.setName(userDataModel.getName());
		user.setLast_name(userDataModel.getLast_name());
		user.setCpf(userDataModel.getCpf());
		user.setPhone(userDataModel.getPhone());
		user.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(userDataModel.getBirthday()));
		user.setEmail(userDataModel.getEmail());
		
		Address address = new Address();
		
		address.setCity(userDataModel.getCity());
		address.setFirst_part(userDataModel.getFirst_part());
		address.setSecond_part(userDataModel.getSecond_part());
		address.setPostal_code(userDataModel.getPostal_code());
		address.setState_id(Integer.parseInt(userDataModel.getState()));
		address.setNeighborhood(userDataModel.getNeighborhood());
		
		addressRepository.save(address);
		
		user.setAddress_id(address.getId());		
		
		MessageDigest md5 = MessageDigest.getInstance("MD5");		
		
		String password = userDataModel.getPassword();		
		md5.update(password.getBytes(),0,password.length());		
		user.setPassword(new BigInteger(1,md5.digest()).toString(32));
		
		String token = userDataModel.getEmail() + password + (new Date()).toString();	
		md5.update(token.getBytes(),0,token.length());	    
		user.setToken(new BigInteger(1,md5.digest()).toString(32));
		
		userRepository.saveUser(user);
		
	}
	
}
