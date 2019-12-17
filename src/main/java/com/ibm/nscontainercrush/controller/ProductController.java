package com.ibm.nscontainercrush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.nscontainercrush.entity.ProductStyle;
import com.ibm.nscontainercrush.repository.ProductStyleRespository;

@RestController
@RequestMapping(value = "/productStyle")
public class ProductController {
	
	@Autowired
	ProductStyleRespository repository;
		
	@GetMapping("/findProductStyleByItemNumber/{itemNumStr}")
	public List<ProductStyle> findProductStyleByItemNumber(@PathVariable String itemNumStr) {
		int itemNumber = Integer.parseInt(itemNumStr);
		return repository.findProductStyleByItemNumber(itemNumber);
	}
	
	@GetMapping("/findAll")
	public List<ProductStyle> findAll() {
		return repository.findAll();
	}
	
	@PostMapping("/add")
	public ProductStyle add(@RequestBody ProductStyle productStyle) {
		return repository.save(productStyle);
	}
	
    @PutMapping("/update/{itemNumber}")
    ProductStyle update(@RequestBody ProductStyle newProductStyle, @PathVariable Integer itemNumber) {
 
        return repository.findById(itemNumber).map(productStyle -> {
        	productStyle.setDescription(newProductStyle.getDescription());
            productStyle.setLongDescription(newProductStyle.getLongDescription());
            productStyle.setCatalogueCategory(newProductStyle.getCatalogueCategory());
            productStyle.setBrand(newProductStyle.getBrand());
            return repository.save(productStyle);
        }).orElseGet(() -> {
        	newProductStyle.setItemNumber(itemNumber);
            return repository.save(newProductStyle);
        });
    }
    
    @DeleteMapping("/delete/{itemNumber}")
    void delete(@PathVariable Integer itemNumber) {
        repository.deleteById(itemNumber);
    }

}
