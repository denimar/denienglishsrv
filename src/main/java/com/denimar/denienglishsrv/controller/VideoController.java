package com.denimar.denienglishsrv.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.dao.VideoDao;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T90IMG;
import com.denimar.denienglishsrv.dto.CreateVideoRequestDTO;
import com.denimar.denienglishsrv.helper.ImageHelper;
import com.denimar.denienglishsrv.service.T05ITMService;
import com.denimar.denienglishsrv.service.T08VDOService;
import com.denimar.denienglishsrv.service.T90IMGService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/video")
public class VideoController {

	@Autowired	 
	private T05ITMService t05itmService;
	@Autowired	 
	private T08VDOService t08vdoService;
	@Autowired	 
	private T90IMGService t90imgService;
	@Autowired
	private VideoDao videoDao;
	@Autowired
	private ImageHelper imageHelper;
	
	@RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T08VDO> getDadosVideo(@RequestParam("cd_item") final long cd_item)  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T08VDO>(false, "Item not found!");
		} else {
			return new RestDefaultReturn<T08VDO>(true, t08vdoService.findByT05itm(t05itm));
		}	
	}	

	@RequestMapping(value = "/add", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
	public RestDefaultReturn<T08VDO> addVideo(@RequestBody CreateVideoRequestDTO createVideoRequest)  {
		try {
			return new RestDefaultReturn<T08VDO>(true, videoDao.addVideo(createVideoRequest));
		} catch (Exception e) {
			return new RestDefaultReturn<T08VDO>(false, e.getMessage());			
		}
	}
	
	@RequestMapping(value = "/comentarios/set", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T08VDO> setComentariosVideo(@RequestParam("cd_video") final int cd_video, @RequestBody final String tx_comentario)  {
		T08VDO t08vdo = t08vdoService.findOne(cd_video);
		if (t08vdo == null) {
			return new RestDefaultReturn<T08VDO>(false, "Video not found!");
		} else {
			t08vdo.setTx_comentarios(tx_comentario);
			t08vdoService.save(t08vdo);
			return new RestDefaultReturn<T08VDO>(true, t08vdo);
		}
	}

	@RequestMapping(value = "/poster/get")
	public void getPosterVideo(@RequestParam("cd_video") final int cd_video, HttpServletResponse response) throws Exception {
		T08VDO t08vdo = t08vdoService.findOne(cd_video);
		if (t08vdo == null) {
			throw new Exception("Video not found!");
		} else {
			T90IMG t90img = t90imgService.findByT05itm(t08vdo.getT05itm());
			imageHelper.getImagemBancoDados(response, t90img.getBt_imagem());
		}
	}
	
}
