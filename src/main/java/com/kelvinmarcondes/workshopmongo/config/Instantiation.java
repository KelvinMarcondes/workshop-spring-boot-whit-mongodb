package com.kelvinmarcondes.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kelvinmarcondes.workshopmongo.domain.Post;
import com.kelvinmarcondes.workshopmongo.domain.User;
import com.kelvinmarcondes.workshopmongo.dto.authorDTO;
import com.kelvinmarcondes.workshopmongo.repository.PostRepository;
import com.kelvinmarcondes.workshopmongo.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2028"), "Partiu viagem!", "Vou para São Paulo", new authorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2028"), "Bom dia", "Acordei feliz hoje", new authorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		
		
	}

	
}
