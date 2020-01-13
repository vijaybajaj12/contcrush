package com.ibm.nscontainercrush.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.ibm.nscontainercrush.constant.ContainerCrushConstant;
import com.ibm.nscontainercrush.dto.ClassLevel;
import com.ibm.nscontainercrush.dto.Commodity;
import com.ibm.nscontainercrush.dto.Family;
import com.ibm.nscontainercrush.dto.ProductCatalogueDto;
import com.ibm.nscontainercrush.dto.SkuItem;
import com.ibm.nscontainercrush.entity.ProductCatalogue;
import com.ibm.nscontainercrush.repository.ProductCatalogueRepository;
import com.ibm.nscontainercrush.repository.ProductItemRepositoryImpl;

@Service
public class ProductCatalogueService {
	
	@Autowired 
	private ProductCatalogueRepository productCatalogueRepository;
	
	@Autowired
	private ProductItemRepositoryImpl productItemRepository;
	
	@Autowired
	private ImageRetrievalService imageRetrievalService;
	
	/**
	 * This method is used to retrieve all the segments from Product Catalogue
	 * @return List<ProductCatalogueDto>
	 */
	public List<ProductCatalogueDto> findSegments() {
		List<ProductCatalogueDto> productCatalogueDtoList = null ;
		List<Object[]> objList = productCatalogueRepository.findSegments();
		
		if (objList !=null && !objList.isEmpty()) {
			productCatalogueDtoList = new ArrayList<>();
			ProductCatalogueDto prodCatDto;
			for (Object[] obj:objList) {
				String segmentId = (String) obj[0];
				String segmentName = (String) obj[1];
				prodCatDto = new ProductCatalogueDto(segmentId, segmentName);
				productCatalogueDtoList.add(prodCatDto);
				prodCatDto = null;
			}
		}
		
		return productCatalogueDtoList;
	}
	
	/**
	 * This method is used to retrieve all family values based on a given segment id
	 * @param segment
	 * @return List<Family>
	 */
	public List<Family> findFamiliesBySegment(String segment) {
		List<Family> familyList = null ;
		List<Object[]> objList = productCatalogueRepository.findFamiliesBySegment(segment);
		
		if (objList !=null && !objList.isEmpty()) {
			familyList = new ArrayList<>();
			Family family;
			for (Object[] obj:objList) {
				String familyId = (String) obj[0];
				String familyName = (String) obj[1];
				family = new Family(familyId, familyName);
				familyList.add(family);
				family = null; 
			}
		}
		
		return familyList;
	}
	
	/**
	 * This method is used to retrieve all classes from Product Catalogue using family id
	 * @param family
	 * @return List<ClassLevel>
	 */
	public List<ClassLevel> findClassesByFamily(String family) {
		List<ClassLevel> classList = null ;
		List<Object[]> objList = productCatalogueRepository.findClassesByFamily(family);
		
		if (objList !=null && !objList.isEmpty()) {
			classList = new ArrayList<>();
			ClassLevel classLvl;
			for (Object[] obj:objList) {
				String classId = (String) obj[0];
				String className = (String) obj[1];
				classLvl = new ClassLevel(classId, className);
				classList.add(classLvl);
				classLvl = null; 
			}
		}
		
		return classList;
	}
	
	/**
	 * This method is used to return all commodities from Product Catalogue based on class id
	 * @param classId
	 * @return List<Commodity>
	 */
	public List<Commodity> findCommoditiesByClass(String classId) {
		List<Commodity> commodityList = null ;
		List<Object[]> objList = productCatalogueRepository.findCommoditiesByClass(classId);
		
		if (objList !=null && !objList.isEmpty()) {
			commodityList = new ArrayList<>();
			Commodity commodity;
			for (Object[] obj:objList) {
				String commodityId = (String) obj[0];
				String commodityName = (String) obj[1];
				commodity = new Commodity(commodityId, commodityName);
				commodityList.add(commodity);
				commodity = null; 
			}
		}
		
		return commodityList;
	}
	
	/**
	 * This method is used to find the Sku Items with all details using commodity id
	 * @param commodityId
	 * @return List<SkuItem>
	 */
	public List<SkuItem> findItemsByCommodity(String commodityId) {
		List<Object[]> objList = productCatalogueRepository.findItemByCommodity(commodityId);
		
		return processResults(objList);
	}
	
	/**
	 * This method will be used to find the sku item list based on strList with possible
	 * matching values
	 * @param strList
	 * @return List<SkuItem>
	 */
	public List<SkuItem> findItemsByTextArray(List<String> strList) {
		
		List<Object[]> objList = null;
		if (strList != null) {
			objList = productItemRepository.getProductItemsByTextArray(strList);
		}
		
		return processResults(objList);
	}
	
	/**
	 * This method is used to retrieve the list of SkuItem based on a given text which will used for searching
	 * @param textStr
	 * @return List<SkuItem
	 */
	public List<SkuItem> findItemsByText(String textStr) {
		
		List<Object[]> objList = null;
		if (!StringUtils.isEmpty(textStr)) {
			objList = productCatalogueRepository.findItemsByText(textStr.toUpperCase());
		}
		
		return processResults(objList);
	}
	
	/**
	 * This method will be used to find the sku item list based on brand and discount
	 * @param brand
	 * @param discount
	 * @return List<SkuItem>
	 */
	public List<SkuItem> findItemsByBrandAndDiscount(String brand, float discount) {
		
		if (!StringUtils.isEmpty(brand)) {
			String brandStr = brand.toUpperCase();
			List<Object[]> objList = productCatalogueRepository.findItemsByBrandAndDiscount(brandStr, discount);
			return processResults(objList);
		} 
		
		return null;
		
	}
	
	/**
	 * This method will be used to process the result sets from DB and convert into List of SkuItem
	 * @param resultList
	 * @return List<SkuItem>
	 */
	private List<SkuItem> processResults (List<Object[]> resultList) {
		
		List<SkuItem> processedList = null;
		if (resultList !=null && !resultList.isEmpty()) {
			processedList = new ArrayList<>();
			SkuItem skuItem;
			for (Object[] obj:resultList) {
				skuItem = new SkuItem();
				if (obj[0] != null) {
					skuItem.setStyleItemNumber((String)obj[0]);
				}
				if (obj[1] != null) {
					skuItem.setStyleDescription((String)obj[1]);
				}
				if (obj[2] != null) {
					skuItem.setStyleLongDescription((String)obj[2]);
				}
				if (obj[3] != null) {
					skuItem.setStyleCatalogueCategory((String)obj[3]);
				}
				if (obj[4] != null) {
					skuItem.setStyleBrand((String)obj[4]);
				}
				if (obj[5] != null) {
					skuItem.setSkuItemNumber((String) obj[5]);
					skuItem.setImageRetrievalUrl(imageRetrievalService.contructImageRetrievalUrl(skuItem.getSkuItemNumber()
									+ ContainerCrushConstant.JPG_EXTENSION)); // set image retrieval url
				}
				if (obj[6] != null) {
					skuItem.setSkuUnitOfMeasure((String)obj[6]);
				}
				if (obj[7] != null) {
					skuItem.setSkuAttribute1((String)obj[7]);
				}
				if (obj[8] != null) {
					skuItem.setSkuAttribute2((String)obj[8]);
				}
				if (obj[9] != null) {
					skuItem.setSkuAttributeValue1((String)obj[9]);
				}
				if (obj[10] != null) {
					skuItem.setSkuAttributeValue2((String)obj[10]);
				}
				if (obj[11] != null) {
					skuItem.setPriceId((String)obj[11]);
				}
				if (obj[12] != null) {
					skuItem.setListPrice((String)obj[12]);
				}
				if (obj[13] != null) {
					skuItem.setDiscount((String)obj[13]);
				}
				if (obj[14] != null) {
					skuItem.setInStock((String)obj[14]);
				}
				if (obj[15] != null) {
					java.sql.Date sqlDate = (java.sql.Date) obj[15];
					if (sqlDate != null) {
						skuItem.setPriceEffectiveDate(new java.util.Date(sqlDate.getTime()));
					}
				}
				processedList.add(skuItem);
				skuItem = null;
			}
		}
		return processedList;
	}

}
