package com.farris.productsandcategories.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.farris.productsandcategories.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

}
