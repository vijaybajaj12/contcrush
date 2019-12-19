package com.ibm.nscontainercrush.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.nscontainercrush.dto.ClassLevel;
import com.ibm.nscontainercrush.dto.Commodity;
import com.ibm.nscontainercrush.dto.Family;
import com.ibm.nscontainercrush.dto.ProductCatalogueDto;
import com.ibm.nscontainercrush.dto.SkuItem;
import com.ibm.nscontainercrush.entity.ProductCatalogue;
import com.ibm.nscontainercrush.repository.ProductCatalogueRepository;

@Service
public class ProductCatalogueService {
	
	@Autowired 
	ProductCatalogueRepository repository;
	
	public List<ProductCatalogueDto> findSegments() {
		List<ProductCatalogueDto> productCatalogueDtoList = null ;
		List<Object[]> objList = repository.findSegments();
		
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
	
	public List<Family> findFamiliesBySegment(String segment) {
		List<Family> familyList = null ;
		List<Object[]> objList = repository.findFamiliesBySegment(segment);
		
		if (objList !=null && !objList.isEmpty()) {
			familyList = new ArrayList<>();
			Family family;
			for (Object[] obj:objList) {
				String familyId = (String) obj[0];
				String familyName = (String) obj[1];
				family = new Family(familyId, familyName);
				familyList.add(family);
				family = null; // will be sent for garbage collection
			}
		}
		
		return familyList;
	}
	
	public List<ClassLevel> findClassByFamily(String family) {
		List<ClassLevel> classList = null ;
		List<Object[]> objList = repository.findClassByFamily(family);
		
		if (objList !=null && !objList.isEmpty()) {
			classList = new ArrayList<>();
			ClassLevel classLvl;
			for (Object[] obj:objList) {
				String classId = (String) obj[0];
				String className = (String) obj[1];
				classLvl = new ClassLevel(classId, className);
				classList.add(classLvl);
				classLvl = null; // will be sent for garbage collection
			}
		}
		
		return classList;
	}
	
	public List<SkuItem> findCommoditiesByClass(String classId) {
		List<Object[]> objList = repository.findCommoditiesByClass(classId);
		
		return processResults(objList);
	}
	
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
					skuItem.setSkuItemNumber((String)obj[5]);
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
	
	// TODO To be removed
	public List<ProductCatalogueDto> findProductCatalogues() {
		
		return processProductCatalogueResults(repository.findAll());
		
		//return repository.findAll();
	}
	
	
	
//	public List<ProductCatalogueDto> findProductCatalogues() {
//		
//		return buildTree(repository.findAll());
//
//	}
	
	// TODO To be removed
	private List<ProductCatalogueDto> processProductCatalogueResults(List<ProductCatalogue> productCatalogueList) {
	
		List<ProductCatalogueDto> productCatalogueDtoList = new ArrayList<>();
		Map<String, List<Family>> catalogueMap = new HashMap<>();
		if (productCatalogueList != null && !productCatalogueList.isEmpty()) {
			for (ProductCatalogue prodCat:productCatalogueList) {
				List<Family> familyList = null;
				if (catalogueMap.containsKey(prodCat.getId().getSegment())) {
					familyList = catalogueMap.get(prodCat.getId().getSegment());
				} else {
					familyList = new ArrayList<>();
				}
				familyList.add(new Family(prodCat.getId().getFamily(), prodCat.getFamilyName()));
				List<Family> familyList1 = processFamilyElements(familyList, prodCat);
				catalogueMap.put(prodCat.getId().getSegment(), familyList1);
				//productCatalogueDtoList.add(new ProductCatalogueDto(prodCat.getId().getSegment(), prodCat.getFamilyName(), familyList1));
			}
		}
		
		return productCatalogueDtoList;
	}
	
	// TODO To be removed
	private List<Family> processFamilyElements(List<Family> familyList, ProductCatalogue prodCat) {
		
		Map<String, List<ClassLevel>> familyMap = new HashMap<>();
		List<Family> familyList1 = new ArrayList<>();
		if (familyList != null && !familyList.isEmpty()) {
			for (Family family:familyList) {
				List<ClassLevel> classList = null;
				if (familyMap.containsKey(family.getFamilyId())) {
					classList = familyMap.get(family.getFamilyId());
				} else {
					classList = new ArrayList<>();
				}
				classList.add(new ClassLevel(prodCat.getId().getClass1(), prodCat.getClassName()));
				processClassElements(classList, prodCat);
				familyMap.put(family.getFamilyId(), classList);
				Family family1 = new Family(family.getFamilyId(), family.getFamilyName());
				family1.setClassList(classList);
				familyList1.add(family1);
			}
		}
		
		return familyList1;
	}
	
	// TODO To be removed
	private List<ClassLevel> processClassElements(List<ClassLevel> classList, ProductCatalogue prodCat) {
		
		Map<String, List<Commodity>> classMap = new HashMap<>();
		List<ClassLevel> classList1 = new ArrayList<>();
		if (classList != null && !classList.isEmpty()) {
			for (ClassLevel classLevel:classList) {
				List<Commodity> commodityList = null;
				if (classMap.containsKey(classLevel.getClassId())) {
					commodityList = classMap.get(classLevel.getClassId());
				} else {
					commodityList = new ArrayList<>();
				}
				commodityList.add(new Commodity(prodCat.getId().getCommodity(), prodCat.getCommodityName()));
				classMap.put(classLevel.getClassId(), commodityList);
				ClassLevel class1 = new ClassLevel(prodCat.getId().getCommodity(), prodCat.getCommodityName());
				class1.setCommodityList(commodityList);
				classList1.add(class1);
				
			}
		}
		
		return classList1;
	}

}
