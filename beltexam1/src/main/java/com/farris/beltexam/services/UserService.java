package com.farris.beltexam.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.farris.beltexam.models.Idea;
import com.farris.beltexam.models.LoginUser;
import com.farris.beltexam.models.User;
import com.farris.beltexam.repositories.IdeaRepository;
import com.farris.beltexam.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IdeaRepository ideaRepository;
    
    
// -----Login & Registration------
    public User register(User newUser, BindingResult result) {
        if(userRepository.findByEmail(newUser.getEmail()).isPresent()) {
      	  result.rejectValue("email", "Unique", "This email is already in use!");
        }
        if(!newUser.getPassword().equals(newUser.getPasswordConfirmation())) {
            result.rejectValue("passwordConfirmation", "Matches", "The Confirm Password must match Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepository.save(newUser);
        }
    }
    

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    

    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    
    public User login(LoginUser newLogin, BindingResult result) {
        if(result.hasErrors()) {
            return null;
        }
        Optional<User> potentialUser = userRepository.findByEmail(newLogin.getEmail());
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        User user = potentialUser.get();
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            return user;
        }
        
    }
    
    public List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }
    
    
//-----Ideas Services--------
    public List<Idea> allLikes() {
        return (List<Idea>) ideaRepository.findAll();
    }

    public List<Idea> allLikesDesc() {
        return (List<Idea>) ideaRepository.findAll(Sort.by("users").descending());
    }
	
	public List<Idea> allLikesAsc() {
        return (List<Idea>) ideaRepository.findAllByOrderByUsersAsc();
    }
    
    public Idea createIdea(Idea i) {
        return ideaRepository.save(i);
    }
    
    public Idea updateIdea(Idea i) {
        return ideaRepository.save(i);
    }
    
    public void deleteIdea(Long id) {
        ideaRepository.deleteById(id);
    }
    
    public Idea findIdeabyId(Long id) {
        return this.ideaRepository.findById(id).orElse(null);
    }
    
    public Idea createRelationship(Long ideaId, Long userId) {
    	Idea thisIdea = findIdeabyId(ideaId);
    	User thisUser = findUserById(userId);
    	thisIdea.getUsers().add(thisUser);
    	return ideaRepository.save(thisIdea);
    }
    
    
    public Idea removeRelationship(Long ideaId, Long userId) {
    	Idea thisIdea = findIdeabyId(ideaId);
    	User thisUser = findUserById(userId);
    	thisIdea.getUsers().remove(thisUser);
    	return ideaRepository.save(thisIdea);
    }
}


