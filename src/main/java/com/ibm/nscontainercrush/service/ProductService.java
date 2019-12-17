package com.containercrush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.containercrush.entity.ProductStyle;
import com.containercrush.repository.ProductStyleRespository;


@Service
public class ProductService {
	
	@Autowired
	ProductStyleRespository productStyleRespository;
	
	public List<ProductStyle> findProductStyleByItemNumber(int itemNumber) {
		return productStyleRespository.findProductStyleByItemNumber(itemNumber);
	}

}
