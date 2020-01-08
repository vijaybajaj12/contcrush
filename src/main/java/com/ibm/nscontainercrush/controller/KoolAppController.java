package com.ibm.nscontainercrush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.nscontainercrush.dto.ClassLevel;
import com.ibm.nscontainercrush.dto.Commodity;
import com.ibm.nscontainercrush.dto.Family;
import com.ibm.nscontainercrush.dto.ProductCatalogueDto;
import com.ibm.nscontainercrush.dto.SkuItem;
import com.ibm.nscontainercrush.service.ProductCatalogueService;

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
	
	@GetMapping("/findClassByFamily/{familyId}")
	public List<ClassLevel> findClassByFamily(@PathVariable String familyId) {
		return service.findClassByFamily(familyId);
	}
	
	
	@GetMapping("/findCommoditiesByClass/{classId}")
	public List<Commodity> findCommoditiesByClass(@PathVariable String classId) {
		return service.findCommoditiesByClass(classId);
	}
	
	@GetMapping("/findItemsByCommodity/{commodityId}")
	public List<SkuItem> findItemsByCommodity(@PathVariable String commodityId) {
		return service.findItemsByCommodity(commodityId);
	}
	
	@GetMapping("/findItemsByText/{text}")
	public List<SkuItem> findItemsByText(@PathVariable String text) {
		return service.findItemsByText(text);
	}
	
	@PostMapping("/findItemsByTextArray")
	public List<SkuItem> findItemsByTextArray(@RequestBody List<String> text) {
		return service.findItemsByTextArray(text);
	}
	
	@GetMapping("/findItemsByBrandAndDiscount/{brand}/{discount}")
	public List<SkuItem> findItemsByBrandAndDiscount(@PathVariable String brand, @PathVariable float discount) {
		return service.findItemsByBrandAndDiscount(brand, discount);
	}
	
}
