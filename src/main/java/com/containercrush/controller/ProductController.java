package com.containercrush.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.containercrush.entity.ProductStyle;
import com.containercrush.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
		
	@GetMapping("/findProductStyleByItemNumber/{itemNumStr}")
	public List<ProductStyle> findProductStyleByItemNumber(@PathVariable String itemNumStr) {
		System.out.println("inside findProductStyleByItemNumber");
		int itemNumber = Integer.parseInt(itemNumStr);
		return productService.findProductStyleByItemNumber(itemNumber);
	}

}
