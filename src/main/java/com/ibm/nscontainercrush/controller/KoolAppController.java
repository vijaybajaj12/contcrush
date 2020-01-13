package com.ibm.nscontainercrush.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ibm.nscontainercrush.dto.SkuItemResult;
import com.ibm.nscontainercrush.service.ProductCatalogueService;

@RestController
@RequestMapping(value = "/koolApp")
public class KoolAppController {
	
	private static final Logger logger = LoggerFactory.getLogger(KoolAppController.class);
	
	@Autowired
	private ProductCatalogueService service;
	
	@GetMapping("/findSegments")
	public List<ProductCatalogueDto> findSegments() {
		logger.info("findSegments() called");
		return service.findSegments();
	}
	
	@GetMapping("/findFamiliesBySegment/{segmentId}")
	public List<Family> findFamiliesBySegment(@PathVariable String segmentId) {
		return service.findFamiliesBySegment(segmentId);
	}
	
	@GetMapping("/findClassesByFamily/{familyId}")
	public List<ClassLevel> findClassesByFamily(@PathVariable String familyId) {
		return service.findClassesByFamily(familyId);
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
	
	@PostMapping("/findItemsByStringArray")
	public SkuItemResult findItemsByStringArray(@RequestBody List<String> text) {
		SkuItemResult result = null;
		List<SkuItem> skuItemList = service.findItemsByTextArray(text);
		if (skuItemList != null && !skuItemList.isEmpty()) {
			result = new SkuItemResult();
			result.setSkuItemList(skuItemList);
		}
		return result;
	}
	
	@GetMapping("/findItemsByBrand/{brand}")
	public List<SkuItem> findItemsByBrand(@PathVariable String brand) {
		return service.findItemsByBrand(brand);
	}
	
	@GetMapping("/findItemsByBrandAndDiscount/{brand}/{discount}")
	public List<SkuItem> findItemsByBrandAndDiscount(@PathVariable String brand, @PathVariable float discount) {
		return service.findItemsByBrandAndDiscount(brand, discount);
	}
	
}
