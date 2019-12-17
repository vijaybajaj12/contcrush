package com.ibm.nscontainercrush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.nscontainercrush.entity.ProductStyle;

	@Repository
	public interface ProductStyleRespository extends JpaRepository<ProductStyle, Integer> {

	    // custom query to search to blog post by title or content
	    List<ProductStyle> findProductStyleByItemNumber(int itemNumber);
	    

}
