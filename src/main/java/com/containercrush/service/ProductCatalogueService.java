package com.containercrush.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.containercrush.dto.ClassLevel;
import com.containercrush.dto.Commodity;
import com.containercrush.dto.Family;
import com.containercrush.dto.ProductCatalogueDto;
import com.containercrush.entity.ProductCatalogue;
import com.containercrush.repository.ProductCatalogueRepository;

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
			}
		}
		
		return productCatalogueDtoList;
	}
	
	public List<Family> findFamiliesBySegment(String segmentId) {
		List<Family> familyList = null ;
		List<Object[]> objList = repository.findFamiliesBySegment(segmentId);
		
		if (objList !=null && !objList.isEmpty()) {
			familyList = new ArrayList<>();
			Family family;
			for (Object[] obj:objList) {
				String familyId = (String) obj[0];
				String familyName = (String) obj[1];
				family = new Family(familyId, familyName);
				familyList.add(family);
			}
		}
		
		return familyList;
	}
	
	public List<ProductCatalogue> findProductCatalogues() {
		
		//return processProductCatalogueResults(repository.findAll());
		
		return repository.findAll();
	}
	
	
	
//	public List<ProductCatalogueDto> findProductCatalogues() {
//		
//		return buildTree(repository.findAll());
//
//	}
	
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
