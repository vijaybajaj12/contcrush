package com.ibm.nscontainercrush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.nscontainercrush.entity.ProductCatalogue;
import com.ibm.nscontainercrush.entity.ProductCataloguePK;

@Repository
public interface ProductCatalogueRepository extends JpaRepository<ProductCatalogue, ProductCataloguePK > {
	
	public static final String FIND_SEGMENTS = "SELECT distinct segment, segment_Name FROM XXIBM_PRODUCT_CATALOGUE";
	public static final String FIND_FAMILIES_BY_SEGMENT = "select distinct family, family_name FROM XXIBM_PRODUCT_CATALOGUE where segment = :segment";
	public static final String FIND_CLASSES_BY_FAMILY = "select distinct CLASS, CLASS_NAME FROM XXIBM_PRODUCT_CATALOGUE where FAMILY = :family";
	public static final String FIND_COMMODITIES_BY_CLASS = "select distinct COMMODITY, COMMODITY_NAME FROM XXIBM_PRODUCT_CATALOGUE where CLASS = :classId";
	public static final String FIND_ITEMS_BY_COMMODITY = "select pst.item_number style_item_number, pst.description, pst.long_description, pst.catalogue_category, pst.brand,\r\n" + 
			"	   psk.ITEM_NUMBER sku_item_number, psk.SKU_UNIT_OF_MEASURE, psk.SKU_ATTRIBUTE1, psk.SKU_ATTRIBUTE2," + 
			"       psk.SKU_ATTRIBUTE_VALUE1, psk.SKU_ATTRIBUTE_VALUE2," + 
			"       pp.PRICE_ID, pp.LIST_PRICE, pp.DISCOUNT, pp.IN_STOCK, pp.PRICE_EFFECTIVE_DATE" + 
			"       FROM XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_STYLE pst, XXIBM_PRODUCT_SKU psk, XXIBM_PRODUCT_PRICING pp" + 
			"       WHERE pst.ITEM_NUMBER = psk.STYLE_ITEM" + 
			"       AND psk.ITEM_NUMBER = pp.ITEM_NUMBER" + 
			"       AND pst.CATALOGUE_CATEGORY = pc.COMMODITY " +
			"       AND pc.COMMODITY = :commodityId";

	public static final String FIND_ITEMS_BY_TEXT = "select pst.item_number style_item_number, pst.description, pst.long_description, pst.catalogue_category, pst.brand,\r\n" + 
			"	   psk.ITEM_NUMBER sku_item_number, psk.SKU_UNIT_OF_MEASURE, psk.SKU_ATTRIBUTE1, psk.SKU_ATTRIBUTE2," + 
			"       psk.SKU_ATTRIBUTE_VALUE1, psk.SKU_ATTRIBUTE_VALUE2," + 
			"       pp.PRICE_ID, pp.LIST_PRICE, pp.DISCOUNT, pp.IN_STOCK, pp.PRICE_EFFECTIVE_DATE" + 
			"       FROM XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_STYLE pst, XXIBM_PRODUCT_SKU psk, XXIBM_PRODUCT_PRICING pp" + 
			"       WHERE pst.ITEM_NUMBER = psk.STYLE_ITEM" + 
			"       AND psk.ITEM_NUMBER = pp.ITEM_NUMBER" + 
			"       AND pst.CATALOGUE_CATEGORY = pc.COMMODITY " +
			"       AND UPPER(pst.LONG_DESCRIPTION) like %:text%  " ;
	
	public static final String FIND_ITEMS_BY_BRAND_AND_DISCOUNT = "select pst.item_number style_item_number, pst.description, pst.long_description, pst.catalogue_category, pst.brand,\r\n" + 
			"	   psk.ITEM_NUMBER sku_item_number, psk.SKU_UNIT_OF_MEASURE, psk.SKU_ATTRIBUTE1, psk.SKU_ATTRIBUTE2," + 
			"       psk.SKU_ATTRIBUTE_VALUE1, psk.SKU_ATTRIBUTE_VALUE2," + 
			"       pp.PRICE_ID, pp.LIST_PRICE, pp.DISCOUNT, pp.IN_STOCK, pp.PRICE_EFFECTIVE_DATE" + 
			"       FROM XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_STYLE pst, XXIBM_PRODUCT_SKU psk, XXIBM_PRODUCT_PRICING pp" + 
			"       WHERE pst.ITEM_NUMBER = psk.STYLE_ITEM" + 
			"       AND psk.ITEM_NUMBER = pp.ITEM_NUMBER" + 
			"       AND pst.CATALOGUE_CATEGORY = pc.COMMODITY " +
			"       AND UPPER(pst.BRAND) = :brand " + 
			"       AND pp.discount <= :discount";
	
	@Query(value = FIND_SEGMENTS, nativeQuery = true)
	public List<Object[]> findSegments();
	
	@Query(value = FIND_FAMILIES_BY_SEGMENT, nativeQuery = true)
	public List<Object[]> findFamiliesBySegment(@Param("segment") String segment);
	
	@Query(value = FIND_CLASSES_BY_FAMILY, nativeQuery = true)
	public List<Object[]> findClassesByFamily(@Param("family") String family);
	
	@Query(value = FIND_COMMODITIES_BY_CLASS, nativeQuery = true)
	public List<Object[]> findCommoditiesByClass(@Param("classId") String classId);
	
	@Query(value = FIND_ITEMS_BY_COMMODITY, nativeQuery = true)
	public List<Object[]> findItemByCommodity(@Param("commodityId") String commodityId);
	
	@Query(value = FIND_ITEMS_BY_TEXT, nativeQuery = true)
	public List<Object[]> findItemsByText(@Param("text") String text);
	
	@Query(value = FIND_ITEMS_BY_BRAND_AND_DISCOUNT, nativeQuery = true)
	public List<Object[]> findItemsByBrandAndDiscount(@Param("brand") String text, @Param("discount") float discount);

}
