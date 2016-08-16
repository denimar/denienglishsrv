package com.denimar.denienglishsrv.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T90IMG;
import com.denimar.denienglishsrv.domain.enums.VIDEO_TYPE_ENUM;
import com.denimar.denienglishsrv.dto.ImageRequestDTO;
import com.denimar.denienglishsrv.helper.ImageHelper;
import com.denimar.denienglishsrv.helper.ItemHelper;
import com.denimar.denienglishsrv.helper.VideoHelper;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.service.T05ITMService;
import com.denimar.denienglishsrv.service.T08VDOService;
import com.denimar.denienglishsrv.service.T90IMGService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {
	
	@Autowired	 
	private T02CTGService t02ctgService;
	@Autowired	 
	private T05ITMService t05itmService;
	@Autowired	 
	private T08VDOService t08vdoService;
	@Autowired	 
	private T90IMGService t90imgService;
	@Autowired
	private ItemHelper itemHelper;
	@Autowired
	private ImageHelper imageHelper;
	@Autowired
	private VideoHelper videoHelper;
	
	@RequestMapping("/list")
	public RestDefaultReturn<T05ITM> getItemsByCategory(@RequestParam("cd_categoria") final int cd_categoria) {
		T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
		if (t02ctg == null) {
			return new RestDefaultReturn<T05ITM>(false, "Category not found!");			
		} else {
			return new RestDefaultReturn<T05ITM>(true, itemHelper.getItemsByCategory(t02ctg));
		}	
	}

	@RequestMapping(value = "/favorite/get")
	public RestDefaultReturn<T05ITM> getFavoritesItemsByCategory(@RequestParam("cd_categoria") final int cd_categoria) {
		T02CTG t02ctg = t02ctgService.findOne(cd_categoria);
		if (t02ctg == null) {
			return new RestDefaultReturn<T05ITM>(false, "Category not found!");			
		} else {
			return new RestDefaultReturn<T05ITM>(true, itemHelper.getItemsByCategory(t02ctg, true, true));
		}	
	}

	@RequestMapping(value = "/favorite/set")
	public RestDefaultReturn<T05ITM> setItemFavorite(@RequestParam("cd_item") final long cd_item, @RequestParam("bl_favorite") boolean bl_favorite) {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Item not found!");			
		} else {
			t05itm.setBlFavorite(bl_favorite);
			t05itmService.save(t05itm);			
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		}	
	}

	@RequestMapping(value = "/get")
	public RestDefaultReturn<T05ITM> getItem(@RequestParam("cd_item") final long cd_item)  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Item not found!");			
		} else {
			return new RestDefaultReturn<T05ITM>(true, t05itmService.findOne(cd_item));
		}	
	}
	
	/**
	 * 
	 * @param topCategoryNode This param serve to identify which kind of content there is in this item (text or video)
	 * @param cd_categoria
	 * @param ds_item
	 * @param imageRequestDTO
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public RestDefaultReturn<T05ITM> addItem(@RequestParam("topCategoryNode") final int topCategoryNode, @RequestParam("cd_categoria") final int cd_categoria, @RequestParam("ds_item") final String ds_item, final @RequestBody ImageRequestDTO imageRequestDTO) throws IOException  {
		try {
			T05ITM t05itm = itemHelper.createItem(topCategoryNode, cd_categoria, ds_item, imageRequestDTO.getBt_imagem());
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		} catch (Exception e) {
			return new RestDefaultReturn<T05ITM>(false, e.getMessage());			
		}	
	}
	
	@RequestMapping(value = "/del")
	public RestDefaultReturn<T05ITM> delItem(@RequestParam("cd_item") final long cd_item)  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Record not found!");
		} else {
			t05itmService.delete(t05itm);
			return new RestDefaultReturn<T05ITM>(true, t05itm);			
		}
	}

	@RequestMapping(value = "/upd", method = RequestMethod.POST)	
	public RestDefaultReturn<T05ITM> updItem(@RequestParam("cd_item") final long cd_item, @RequestParam("cd_categoria") final int cd_categoria, final String ds_item, final @RequestBody ImageRequestDTO imageRequestDTO) throws IOException  {
		try {
			T05ITM t05itm = itemHelper.updateItem(cd_item, cd_categoria, ds_item, imageRequestDTO.getBt_imagem());
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		} catch (Exception e) {
			return new RestDefaultReturn<T05ITM>(false, e.getMessage());			
		}	
	}	
	
	@RequestMapping(value = "/rename", method = RequestMethod.POST)	
	public RestDefaultReturn<T05ITM> renameItem(@RequestParam("cd_item") final long cd_item, final String ds_item) throws IOException  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Record not found!");
		} else {
			t05itm.setDsItem(ds_item);
			t05itm.setDtAlteracao(new Date());		
			t05itmService.save(t05itm);
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		}	
	}	

	@RequestMapping(value = "/image/upd", method = RequestMethod.POST)	
	public RestDefaultReturn<T05ITM> updImageItem(@RequestParam("cd_item") final long cd_item, final @RequestBody String bt_imagem_item) throws IOException  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T05ITM>(false, "Record not found!");
		} else {
			t05itm.setDtAlteracao(new Date());		
			t05itmService.save(t05itm);
			
			//Edita a imagem da subcategoria
			T90IMG t90img = t90imgService.findByT05itm(t05itm);
			byte[] imagem = ImageHelper.getBytesFromUriImagem(bt_imagem_item);
			t90img.setBtImagem(imagem);
			t90imgService.save(t90img);
			
			return new RestDefaultReturn<T05ITM>(true, t05itm);
		}	
	}	

	@RequestMapping(value = "/image/getlink")
	public void getImageLink(@RequestParam("tp_video") final int tp_video, @RequestParam("id_video") final String id_video, HttpServletResponse response) throws Exception {
		if (id_video != null) {
			VIDEO_TYPE_ENUM videoType = VIDEO_TYPE_ENUM.values()[tp_video];
			String urlPoster = videoHelper.getUtlPoster(videoType, id_video);
			byte[] videoImage = videoHelper.getBytesArrayFromURL(urlPoster);
			
			if (videoImage != null) {
				imageHelper.getImagemBancoDados(response, videoImage);				
			}
		}
	}	
	
	@RequestMapping(value = "/image/get")
	public void getImage(@RequestParam("cd_item") final long cd_item, @RequestParam("time") final String time, HttpServletResponse response) throws Exception {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			throw new Exception("Item not found!");
		} else {
			T90IMG t90img = t90imgService.findByT05itm(t05itm);
			imageHelper.getImagemBancoDados(response, t90img.getBtImagem());
			
			/*
			//Text
			if (topCategoryNode == CATEGORY_TYPE_ENUM.TEXT.getCategoryType()){
				T90IMG t90img = t90imgService.findByT05itm(t05itm);
				imageHelper.getImagemBancoDados(response, t90img.getBtImagem());

			//Video
			} else if (topCategoryNode == CATEGORY_TYPE_ENUM.VIDEO.getCategoryType()){
				T08VDO t08vdo = t08vdoService.findByT05itm(t05itm);
				
				String urlImage = null;
				if (t08vdo.getTpVideo() == VIDEO_TYPE_ENUM.YOUTUBE) {
					urlImage = "http://img.youtube.com/vi/" + t08vdo.getIdVideo() + "/default.jpg";
				} else if (t08vdo.getTpVideo() == VIDEO_TYPE_ENUM.GOOGLE_DRIVE) {
					urlImage = "https://docs.google.com/vt?id=" + t08vdo.getIdVideo();
				}
				response.sendRedirect(urlImage);
				
			} else {
				throw new Exception("Invalid Item Type!");
			}
			*/
		}
	}
	
}

