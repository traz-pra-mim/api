package online.trazpramim.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import online.trazpramim.domain.Address;
import online.trazpramim.domain.Country;
import online.trazpramim.domain.State;
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
	
	public void delUser(String token) throws ParseException{
		/*User user = null;
		
		String email = userDataModel.getEmail();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String password = userDataModel.getPassword();		
		md5.update(password.getBytes(),0,password.length());
		password = (new BigInteger(1,md5.digest()).toString(32));
		
		user = userRepository.getTokenByEmailAndPass(email, password);*/
		try {
		userRepository.deleteUser(token);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public UserDataModel login(UserDataModel userDataModel) throws NoSuchAlgorithmException {
		
		String email = userDataModel.getEmail();
		MessageDigest md5 = MessageDigest.getInstance("MD5");		
		
		String password = userDataModel.getPassword();		
		md5.update(password.getBytes(),0,password.length());		
		password = (new BigInteger(1,md5.digest()).toString(32));
		
		User user =  userRepository.getTokenByEmailAndPass(email, password);
		
		UserDataModel token = new UserDataModel();
		
		token.setToken(user.getToken());
		
		return token; 
	}
	
	public UserDataModel findUser(String token) {
		
		User user = null;
		try {
			 user = userRepository.getNowUser(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserDataModel userDataModel = new UserDataModel();
		
		userDataModel.setEmail(user.getEmail());
		userDataModel.setName(user.getName());
		userDataModel.setLast_name(user.getLast_name());
		userDataModel.setCpf(user.getCpf());
		userDataModel.setBirthday(user.getBirthday().toString());
		
		Address address = new Address();
		try {
			address = addressRepository.find(user.getAddress_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userDataModel.setFirst_part(address.getFirst_part());
		userDataModel.setSecond_part(address.getSecond_part());
		userDataModel.setPostal_code(address.getPostal_code());
		userDataModel.setNeighborhood(address.getNeighborhood());
		userDataModel.setCity(address.getCity());
		
		State state = new State();
		try {
			state = addressRepository.findState(address.getState_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userDataModel.setState(state.getState());
		
		Country country = new Country();
		try {
			country = addressRepository.findCountry(state.getCountry_id());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		userDataModel.setCountry(country.getName());
	
		
		return userDataModel;
	}
	
}
