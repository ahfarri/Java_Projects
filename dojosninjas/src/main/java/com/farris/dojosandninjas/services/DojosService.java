package com.farris.dojosandninjas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.farris.dojosandninjas.models.Dojo;
import com.farris.dojosandninjas.models.Ninja;
import com.farris.dojosandninjas.repositories.DojosRepository;
import com.farris.dojosandninjas.repositories.NinjasRepository;


@Service
public class DojosService {

	// adding the book repository as a dependency
    private final DojosRepository dojosRepository;
    private final NinjasRepository ninjasRepository;
    
    public DojosService(DojosRepository dojosRepository, NinjasRepository ninjasRepository) {
        this.dojosRepository = dojosRepository;
        this.ninjasRepository = ninjasRepository;
    }
//    // returns all the dojos
    public List<Dojo> allDojos() {
        return (List<Dojo>) dojosRepository.findAll();
    }
    
    // creates a Dojo
    public Dojo createDojo(Dojo d) {
        return dojosRepository.save(d);
    }
    // creates a Ninja
    public Ninja createNinja(Ninja n) {
        return ninjasRepository.save(n);
    }
    // retrieves a dojo
    public Dojo findDojo(Long id) {
        return this.dojosRepository.findById(id).orElse(null);
    }
}

