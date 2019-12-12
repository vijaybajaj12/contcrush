package com.containercrush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.containercrush.dto.Commodity;
import com.containercrush.dto.Family;
import com.containercrush.dto.ProductCatalogueDto;
import com.containercrush.service.ProductCatalogueService;

@RestController
@RequestMapping(value = "/koolApp")
public class KoolAppController {
	
	@Autowired
	ProductCatalogueService service;

//	@GetMapping("/findProductCatalogues")
//	public List<ProductCatalogueDto> findProductCatalogues() {
//		return service.findProductCatalogues();
//	}
	
//	@GetMapping("/findProductCatalogues")
//	public List<ProductCatalogue> findProductCatalogues() {
//		return service.findProductCatalogues();
//	}
	
	@GetMapping("/findSegments")
	public List<ProductCatalogueDto> findSegments() {
		return service.findSegments();
	}
	
	@GetMapping("/findFamiliesBySegment/{segmentId}")
	public List<Family> findFamiliesBySegment(@PathVariable String segmentId) {
		return service.findFamiliesBySegment(segmentId);
	}
	
	@GetMapping("/findCommoditiesByClass/{classId}")
	public List<Commodity> findCommoditiesByClass(@PathVariable String classId) {
		return service.findCommoditiesByClass(classId);
	}
}
