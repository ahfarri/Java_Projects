package com.farris.dojosandninjas.models;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farris.dojosandninjas.services.DojosService;


@RestController
public class DojosninjasAPI {
	private final DojosService dojosService;
	
    public DojosninjasAPI(DojosService dojosService){
        this.dojosService = dojosService;
    }
    
    @PostMapping("/api/dojos")
    public Dojo createD(@RequestParam(value="name") String name) {
        Dojo d = new Dojo(name);
        return dojosService.createDojo(d);
    }
    
    @PostMapping("/api/ninjas")
    public Ninja createN(@RequestParam(value="firstname") String firstname, @RequestParam(value="lastname") String lastname, @RequestParam(value="age") Integer age, @RequestParam(value="dojo") Dojo dojo) {
        Ninja n = new Ninja(firstname, lastname, age, dojo);
        return dojosService.createNinja(n);
    }
    
    @RequestMapping("/api/dojos/{id}")
    public List show(@PathVariable("id") Long id) {
        Dojo dojo = dojosService.findDojo(id);
        List ninjas = dojo.getNinjas();
        return ninjas;
    }
  
}
