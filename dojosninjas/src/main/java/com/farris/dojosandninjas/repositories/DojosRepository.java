package com.farris.dojosandninjas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farris.dojosandninjas.models.Dojo;

@Repository
public interface DojosRepository extends CrudRepository<Dojo,Long> {

}
