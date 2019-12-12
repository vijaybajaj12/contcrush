package com.containercrush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.containercrush.entity.ProductCatalogue;
import com.containercrush.entity.ProductCataloguePK;

@Repository
public interface ProductCatalogueRepository extends JpaRepository<ProductCatalogue, ProductCataloguePK > {
	
	public static final String FIND_SEGMENTS = "SELECT distinct segment, segment_Name FROM XXIBM_Product_Catalogue";
	public static final String FIND_FAMILIES_BY_SEGMENT = "select distinct family, family_name FROM XXIBM_Product_Catalogue where segment = :segment";
	public static final String FIND_COMMODITIES_BY_CLASS = "select distinct commodity, commodity_name FROM XXIBM_Product_Catalogue where class = :classId";

	@Query(value = FIND_SEGMENTS, nativeQuery = true)
	public List<Object[]> findSegments();
	
	@Query(value = FIND_FAMILIES_BY_SEGMENT, nativeQuery = true)
	public List<Object[]> findFamiliesBySegment(@Param("segment") String segment);
	
	@Query(value = FIND_COMMODITIES_BY_CLASS, nativeQuery = true)
	public List<Object[]> findCommoditiesByClass(@Param("classId") String classId);

}
