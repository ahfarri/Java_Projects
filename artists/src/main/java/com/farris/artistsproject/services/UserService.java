package com.farris.artistsproject.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.farris.artistsproject.models.LoginUser;
import com.farris.artistsproject.models.User;
import com.farris.artistsproject.models.Work;
import com.farris.artistsproject.repositories.UserRepository;
import com.farris.artistsproject.repositories.WorkRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WorkRepository workRepository;
    
    
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
    
    
//-----Works Services--------
    public List<Work> allLikes() {
        return (List<Work>) workRepository.findAll();
    }

    public List<Work> allLikesDesc() {
        return (List<Work>) workRepository.findAll(Sort.by("users").descending());
    }
	
	public List<Work> allLikesAsc() {
        return (List<Work>) workRepository.findAllByOrderByUsersAsc();
    }
    
    public Work createWork(Work i) {
        return workRepository.save(i);
    }
    
    public Work updateWork(Work i) {
        return workRepository.save(i);
    }
    
    public void deleteWork(Long id) {
        workRepository.deleteById(id);
    }
    
    public Work findWorkbyId(Long id) {
        return this.workRepository.findById(id).orElse(null);
    }
    
    public Work createRelationship(Long workId, Long userId) {
    	Work thisWork = findWorkbyId(workId);
    	User thisUser = findUserById(userId);
    	thisWork.getUsers().add(thisUser);
    	return workRepository.save(thisWork);
    }
    
    
    public Work removeRelationship(Long workId, Long userId) {
    	Work thisWork = findWorkbyId(workId);
    	User thisUser = findUserById(userId);
    	thisWork.getUsers().remove(thisUser);
    	return workRepository.save(thisWork);
    }
}


