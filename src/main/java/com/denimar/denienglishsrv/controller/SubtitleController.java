package com.denimar.denienglishsrv.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T08VIS;
import com.denimar.denienglishsrv.dto.SubtitleRequestDTO;
import com.denimar.denienglishsrv.service.T05ITMService;
import com.denimar.denienglishsrv.service.T08VDOService;
import com.denimar.denienglishsrv.service.T08VISService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/subtitle")
@CrossOrigin
public class SubtitleController {

	@Autowired	 
	private T05ITMService t05itmService;
	@Autowired	 
	private T08VDOService t08vdoService;
	@Autowired
	private T08VISService t08visService;
	
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestDefaultReturn<T08VIS> getSubtitles(@RequestParam("cd_item") final long cd_item)  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		return new RestDefaultReturn<T08VIS>(true, t08visService.findByT08vdo_t05itm(t05itm));
	}
	
	@RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T08VIS> addSubtitle(@RequestBody final SubtitleRequestDTO subtitleRequest)  {
		T08VDO t08vdo = t08vdoService.findOne(subtitleRequest.getCd_video());
		if (t08vdo == null) {
			return new RestDefaultReturn<T08VIS>(false, "Video not found!");
		} else {
			T08VIS t08vis = new T08VIS();
			t08vis.setT08vdo(t08vdo);
			t08vis.setNrStart(subtitleRequest.getNr_start());
			t08vis.setNrEnd(subtitleRequest.getNr_end());
			t08vis.setDsTexto(subtitleRequest.getDs_texto());
			t08visService.save(t08vis);
			return new RestDefaultReturn<T08VIS>(true, t08vis);
		}	
	}

	@RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T08VIS> delSubtitle(@RequestParam("cd_item_subtitle") final int cd_item_subtitle)  {
		T08VIS t08vis = t08visService.findOne(cd_item_subtitle);
		if (t08vis == null) {
			return new RestDefaultReturn<T08VIS>(false, "Record not found!");
		} else {
			t08visService.delete(t08vis);
			return new RestDefaultReturn<T08VIS>(true, t08vis);
		}	
	}

	@RequestMapping(value = "/upd", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T08VIS> updSubtitle(@RequestBody final SubtitleRequestDTO subtitleRequest)  {
		T08VIS t08vis = t08visService.findOne(subtitleRequest.getCd_item_subtitle());
		if (t08vis == null) {
			return new RestDefaultReturn<T08VIS>(false, "Record not found!");
		} else {
			t08vis.setNrStart(subtitleRequest.getNr_start());
			t08vis.setNrEnd(subtitleRequest.getNr_end());
			t08vis.setDsTexto(subtitleRequest.getDs_texto());
			t08vis.setDtAlteracao(new Date());
			t08visService.save(t08vis);
			return new RestDefaultReturn<T08VIS>(true, t08vis);
		}	
	}
	
}
