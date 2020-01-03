package com.ibm.nscontainercrush.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ibm.nscontainercrush.constant.ContainerCrushConstant;

@Repository
public class ProductItemRepositoryImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public static final String FIND_ITEMS_BY_TEXT_ARRAY = "select distinct pst.item_number style_item_number, pst.description, pst.long_description, pst.catalogue_category, pst.brand,\r\n" + 
			"	   psk.ITEM_NUMBER sku_item_number, psk.SKU_UNIT_OF_MEASURE, psk.SKU_ATTRIBUTE1, psk.SKU_ATTRIBUTE2," + 
			"       psk.SKU_ATTRIBUTE_VALUE1, psk.SKU_ATTRIBUTE_VALUE2," + 
			"       pp.PRICE_ID, pp.LIST_PRICE, pp.DISCOUNT, pp.IN_STOCK, pp.PRICE_EFFECTIVE_DATE" + 
			"       FROM XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_STYLE pst, XXIBM_PRODUCT_SKU psk, XXIBM_PRODUCT_PRICING pp" + 
			"       WHERE pst.ITEM_NUMBER = psk.STYLE_ITEM" + 
			"       AND psk.ITEM_NUMBER = pp.ITEM_NUMBER" + 
			"       AND pst.CATALOGUE_CATEGORY = pc.COMMODITY " ;
	
	/**
	 * This method is used to to wild card search on long description field to get most
	 * possible result sets based on strList
	 * @param strList
	 * @return
	 */
	public List<Object[]> getProductItemsByTextArray(List<String> strList) {
		StringBuilder query = new StringBuilder(FIND_ITEMS_BY_TEXT_ARRAY);
		final StringBuilder addlnQuery = new StringBuilder(" UPPER(pst.LONG_DESCRIPTION) like ");

		if (strList != null && !strList.isEmpty()) {
			int k = 1;
			query.append(ContainerCrushConstant.AND_OPERATOR).append(ContainerCrushConstant.LEFT_BRACE);
			for (String str : strList) {
				query.append(addlnQuery).append(ContainerCrushConstant.SINGLE_QUOTE)
						.append(ContainerCrushConstant.PERCENT).append(str.toUpperCase()).append(ContainerCrushConstant.PERCENT)
						.append(ContainerCrushConstant.SINGLE_QUOTE);
				if (k < strList.size()) {
					query.append(ContainerCrushConstant.OR_OPERATOR);
				}
				k++;
			}
			query.append(ContainerCrushConstant.RIGHT_BRACE);
		}

		String queryStr = query.toString();
		Query nativeQuery = entityManager.createNativeQuery(queryStr);

		return nativeQuery.getResultList();
	}
}
