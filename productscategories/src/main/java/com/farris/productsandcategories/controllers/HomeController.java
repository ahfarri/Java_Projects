package com.farris.productsandcategories.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farris.productsandcategories.models.Category;
import com.farris.productsandcategories.models.Product;
import com.farris.productsandcategories.services.AppService;

@Controller
public class HomeController {
	
	private final AppService appService;
	
	
	public HomeController(AppService appService) {
		this.appService = appService;
	}

	@GetMapping("/categories/new")
    public String categoriespage(@ModelAttribute ("category") Category category) {
    	return "newcategory.jsp";
    }
    
    @PostMapping("/category/create")
    public String createC(@Valid @ModelAttribute ("category") Category category, BindingResult result) {
    	 if (result.hasErrors()) {
             return "newcategory.jsp";
         } else {
        	 appService.createCategory(category);
             return "redirect:/categories/new";}
    }
    
    @GetMapping("/products/new")
    public String productspage(@ModelAttribute ("product") Product product, Model model) {
    	model.addAttribute("product", product);
    	return "newproduct.jsp";
    }
    
    @PostMapping("/product/create")
    public String createP(@Valid @ModelAttribute ("product") Product product, BindingResult result) {
    	if (result.hasErrors()) {
            return "newproduct.jsp";
        } else {
        	appService.createProduct(product);;
            return "redirect:/products/new";}
    }
    
    @RequestMapping("/products/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model, HttpSession session) {
        Product product = appService.findProductbyId(id);
        List<Category> available = appService.findByProductsNotContains(id);
        model.addAttribute("product", product);
        model.addAttribute("freecategories", available);
        session.setAttribute("pid", id);
        return "showproducts.jsp";
    }
    
    @PostMapping("/product/add")
    public String addProduct(@RequestParam("categories") Category category, HttpSession session, Model model) {
    	Long p = (Long) session.getAttribute("pid");
    	appService.createRelationship(category.getId(), p);
            return "redirect:/products/" + p;
    }
    
    @RequestMapping("/categories/{id}")
    public String showCategory(@PathVariable("id") Long id, Model model, HttpSession session) {
        Category category = appService.findCategorybyId(id);
        List<Product> available = appService.findByCategoriesNotContains(id);
        model.addAttribute("category", category);
        model.addAttribute("freecategories", available);
        session.setAttribute("cid", id);
        return "showcategories.jsp";
    }
    
    @PostMapping("/category/add")
    public String addCategory(@RequestParam("products") Product product, HttpSession session, Model model) {
    	Long p = (Long) session.getAttribute("cid");
    	appService.createRelationship(p , product.getId());
            return "redirect:/categories/" + p;
    }
}
