package com.farris.artistsproject.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farris.artistsproject.models.Work;

@Repository
public interface WorkRepository extends CrudRepository<Work, Long> {
	
	List<Work> findAll(Sort sort);
	
	List<Work> findAllByOrderByUsersAsc();

	
}
