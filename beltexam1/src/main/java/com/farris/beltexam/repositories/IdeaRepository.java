package com.farris.beltexam.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.farris.beltexam.models.Idea;


public interface IdeaRepository extends CrudRepository<Idea, Long> {
	
	List<Idea> findAll(Sort sort);
	
	List<Idea> findAllByOrderByUsersAsc();

	
}
