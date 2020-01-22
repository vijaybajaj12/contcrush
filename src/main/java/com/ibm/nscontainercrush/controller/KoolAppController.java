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
public class KoolAppController extends BaseController{
	
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
	public SkuItemResult findItemsByCommodity(@PathVariable String commodityId) {
		SkuItemResult result = new SkuItemResult();
		boolean success=false;
		try {
			List<SkuItem> skuItemList = service.findItemsByCommodity(commodityId);
			result.setSkuItemList(skuItemList);
			success = true;
		} catch (Exception e) {
			logger.error("Exception occurred while finding items by text" + e);
			result.setErrorDesc(e.getMessage());
			setExceptionResponse(result, e);
		} finally {
			result.setSuccess(success);
		}
		return result;
	}
	
	@GetMapping("/findItemsByText/{text}")
	public SkuItemResult findItemsByText(@PathVariable String text) {
		SkuItemResult result = new SkuItemResult();
		boolean success=false;
		try {
			List<SkuItem> skuItemList = service.findItemsByText(text);
			result.setSkuItemList(skuItemList);
			success = true;
		} catch (Exception e) {
			logger.error("Exception occurred while finding items by text" + e);
			result.setErrorDesc(e.getMessage());
			setExceptionResponse(result, e);
		} finally {
			result.setSuccess(success);
		}
		return result;
	}
	
//	@PostMapping("/findItemsByTextArray")
//	public List<SkuItem> findItemsByTextArray(@RequestBody List<String> text) {
//		return service.findItemsByTextArray(text);
//	}
//	
//	@PostMapping("/findItemsByStringArray")
//	public SkuItemResult findItemsByStringArray(@RequestBody List<String> text) {
//		SkuItemResult result = null;
//		List<SkuItem> skuItemList = service.findItemsByTextArray(text);
//		if (skuItemList != null && !skuItemList.isEmpty()) {
//			result = new SkuItemResult();
//			result.setSkuItemList(skuItemList);
//		}
//		return result;
//	}
	
	@GetMapping("/findItemsByBrand/{brand}")
	public SkuItemResult findItemsByBrand(@PathVariable String brand) {
		SkuItemResult result = new SkuItemResult();
		boolean success=false;
		try {
			List<SkuItem> skuItemList = service.findItemsByBrand(brand);
			result.setSkuItemList(skuItemList);
			success = true;
		} catch (Exception e) {
			logger.error("Exception occurred while finding items by brand" + e);
			result.setErrorDesc(e.getMessage());
			setExceptionResponse(result, e);
		} finally {
			result.setSuccess(success);
		}
		return result;
	}
	
	@GetMapping("/findItemsByBrandAndDiscount/{brand}/{discount}")
	public SkuItemResult findItemsByBrandAndDiscount(@PathVariable String brand, @PathVariable float discount) {
		SkuItemResult result = new SkuItemResult();
		boolean success=false;
		try {
			List<SkuItem> skuItemList = service.findItemsByBrandAndDiscount(brand, discount);
			result.setSkuItemList(skuItemList);
			success = true;
		} catch (Exception e) {
			logger.error("Exception occurred while finding items by brand and discount" + e);
			result.setErrorDesc(e.getMessage());
			setExceptionResponse(result, e);
		} finally {
			result.setSuccess(success);
		}
		return result;
	}
	
	@GetMapping("/findItemsByGender/{gender}")
	public SkuItemResult findItemsByGender(@PathVariable String gender) {
		SkuItemResult result = new SkuItemResult();
		boolean success=false;
		try {
			List<SkuItem> skuItemList = service.findItemsByGender(gender);
			result.setSkuItemList(skuItemList);
			success = true;
		} catch (Exception e) {
			logger.error("Exception occurred while finding items by gender" + e);
			result.setErrorDesc(e.getMessage());
			setExceptionResponse(result, e);
		} finally {
			result.setSuccess(success);
		}
		return result;
	}
	
}
