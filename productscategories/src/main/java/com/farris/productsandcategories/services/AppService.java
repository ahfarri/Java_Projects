package com.farris.productsandcategories.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.farris.productsandcategories.models.Category;
import com.farris.productsandcategories.models.Product;
import com.farris.productsandcategories.repositories.CategoryRepository;
import com.farris.productsandcategories.repositories.ProductRepository;

@Service
public class AppService {

	private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    
	public AppService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public List<Category> allCategories() {
        return (List<Category>) categoryRepository.findAll();
    }
	
	public List<Product> allProducts() {
        return (List<Product>) productRepository.findAll();
    }
    
    public Product createProduct(Product p) {
        return productRepository.save(p);
    }
    
    public Category createCategory(Category c) {
        return categoryRepository.save(c);
    }
    
    public Category addProduct(Long id,Product p) {
    	Category thisCategory = findCategorybyId(id);
    	thisCategory.getProducts().add(p);
        return categoryRepository.save(thisCategory);
    }
    
    public Product addCategory(Long id, Category c) {
    	Product thisProduct = findProductbyId(id);
    	thisProduct.getCategories().add(c);
        return productRepository.save(thisProduct);
    }
    
    
    public Product findProductbyId(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }
    
    public Category findCategorybyId(Long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }
    
    public void createRelationship(Long categoryId, Long productId) {
    	Category thisCategory = findCategorybyId(categoryId);
    	Product thisProduct = findProductbyId(productId);
    	thisCategory.getProducts().add(thisProduct);
    	categoryRepository.save(thisCategory);
    }
    
    
    public List<Category> findAllByProducts(Long id) {
    	Product thisProduct = findProductbyId(id);
    	return thisProduct.getCategories();
    	
    }
    
    // Retrieves a list of any categories a particular product
    // does not belong to.
    public List<Category> findByProductsNotContains(Long id) {
    	Product thisProduct = findProductbyId(id);
    	List<Category> selected = thisProduct.getCategories();
    	List<Category> all = allCategories();
    	List<Category> free = new ArrayList<Category>();
    	for (int i = 0; i < all.size(); i++) {
    		if (selected.contains(all.get(i))) {
    			}
    		else {
    			free.add(all.get(i));
    		}
		}
    	return free;			
    }
    
    public List<Product> findByCategoriesNotContains(Long id) {
    	Category thisCategory = findCategorybyId(id);
    	List<Product> selected = thisCategory.getProducts();
    	List<Product> all = allProducts();
    	List<Product> free = new ArrayList<Product>();
    	for (int i = 0; i < all.size(); i++) {
    		if (selected.contains(all.get(i))) {
    			}
    		else {
    			free.add(all.get(i));
    		}
		}
    	return free;
    }
}
