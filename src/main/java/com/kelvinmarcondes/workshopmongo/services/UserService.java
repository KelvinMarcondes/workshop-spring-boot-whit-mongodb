package com.kelvinmarcondes.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelvinmarcondes.workshopmongo.domain.User;
import com.kelvinmarcondes.workshopmongo.dto.UserDTO;
import com.kelvinmarcondes.workshopmongo.repository.UserRepository;
import com.kelvinmarcondes.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById (String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado"));
	}
	
	public User insert(User user) {
		return repo.insert(user);
		
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	
	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return repo.save(newUser);
		}
	
	
	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		
	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
	

}
