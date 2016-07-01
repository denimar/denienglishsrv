package com.denimar.denienglishsrv.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.dto.CategoryTreeListResponseDTO;
import com.denimar.denienglishsrv.service.T02CTGService;

@Component
public class CategoryHelper {
	
	@Autowired
	T02CTGService t02ctgService;
	
	public CategoryTreeListResponseDTO getCategoryTreeList() {
		return getCategoryTreeList(null);
	}
	
	public CategoryTreeListResponseDTO getCategoryTreeList(T02CTG parentCategory) {
		CategoryTreeListResponseDTO root = new CategoryTreeListResponseDTO();
		root.setId(-99);
		root.setText("Root Denimar");
		root.setChildren(getCategoryChildrenTreeList(parentCategory));
		return root;
	}

	private CategoryTreeListResponseDTO[] getCategoryChildrenTreeList(T02CTG parentCategory) {
		List<T02CTG> list = t02ctgService.findByT02ctg(parentCategory);		
		
		CategoryTreeListResponseDTO[] children = new CategoryTreeListResponseDTO[list.size()];
		for (int index = 0 ; index < list.size() ; index++) {
			T02CTG t02ctg = list.get(index);	
			CategoryTreeListResponseDTO item = new CategoryTreeListResponseDTO();
			item.setId(t02ctg.getCdCategoria());
			item.setText(t02ctg.getDsCategoria());
			item.setChildren(getCategoryChildrenTreeList(t02ctg));
			children[index] = item;
		}
		
		return children;		
	}
	
}
