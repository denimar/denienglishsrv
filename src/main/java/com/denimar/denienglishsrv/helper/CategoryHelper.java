package com.denimar.denienglishsrv.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T90IMG;
import com.denimar.denienglishsrv.dto.CategoryTreeListResponseDTO;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.service.T90IMGService;

@Component
public class CategoryHelper {
	
	@Autowired
	T02CTGService t02ctgService;
	@Autowired	 
	private T90IMGService t90imgService;
	
	private String iconItemTreeview = null;
	
	public CategoryTreeListResponseDTO getCategoryTreeList() {
		return getCategoryTreeList(null);
	}
	
	public CategoryTreeListResponseDTO getCategoryTreeList(T02CTG parentCategory) {
		CategoryTreeListResponseDTO root = new CategoryTreeListResponseDTO();
		root.setId(-99);
		root.setText("root");
		root.setChildren(getCategoryChildrenTreeList(parentCategory));
		return root;
	}

	public byte[] getImage(T02CTG category) {
		T02CTG t02ctg = category;		
		T90IMG t90img = t90imgService.findByT02ctg(t02ctg);
				
		//if there is no image for this category, should look for image in a parent category
		while (t90img == null) {
			t02ctg = t02ctg.getT02ctg();
			if (t02ctg == null) {
				break;
			} else {
				t90img = t90imgService.findByT02ctg(t02ctg);
			}
		}
		
		if (t90img == null) {
			return null;
		} else {
			return t90img.getBtImagem();
		}	
	}
	
	private CategoryTreeListResponseDTO[] getCategoryChildrenTreeList(T02CTG parentCategory) {
		List<T02CTG> list = t02ctgService.findByT02ctg(parentCategory);		
		
		CategoryTreeListResponseDTO[] children = new CategoryTreeListResponseDTO[list.size()];
		for (int index = 0 ; index < list.size() ; index++) {
			T02CTG t02ctg = list.get(index);	
			CategoryTreeListResponseDTO item = new CategoryTreeListResponseDTO();
			item.setId(t02ctg.getCdCategoria());
			item.setText(t02ctg.getDsCategoria());
			
			if (parentCategory == null) {
				if (t02ctg.getDsCategoria().equalsIgnoreCase("Videos")) {
					iconItemTreeview = "assets/images/24x24/folder_movie.png";
				} else  if (t02ctg.getDsCategoria().equalsIgnoreCase("Texts")) {
					iconItemTreeview = "assets/images/24x24/folder_text.png";					
				}
			}
			
			item.setIcon(iconItemTreeview);
			item.setChildren(getCategoryChildrenTreeList(t02ctg));
			children[index] = item;
		}
		
		return children;		
	}
	
	
}
