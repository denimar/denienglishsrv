package com.denimar.denienglishsrv.controller;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T08VIS;
import com.denimar.denienglishsrv.dto.SubtitleImportLyricsRequestDTO;
import com.denimar.denienglishsrv.dto.SubtitleRequestDTO;
import com.denimar.denienglishsrv.helper.RevisionHelper;
import com.denimar.denienglishsrv.helper.SubtitleHelper;
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
	@Autowired
	private SubtitleHelper subtitleHelper;
	@Autowired
	private RevisionHelper revisionHelper;
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestDefaultReturn<T08VIS> getSubtitles(@RequestParam("cd_item") final long cd_item)  {
		T05ITM t05itm = t05itmService.findOne(cd_item);
		if (t05itm == null) {
			return new RestDefaultReturn<T08VIS>(false, "Item not found!");
		} else {
			return new RestDefaultReturn<T08VIS>(true, t08visService.findByT08vdo_t05itmOrderByNrStart(t05itm));
		}	
	}
	
	@RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)	
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
			
			revisionHelper.updText(t08vdo.getT05itm(), true);
			
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
			revisionHelper.updText(t08vis.getT08vdo().getT05itm(), true);
			return new RestDefaultReturn<T08VIS>(true, t08vis);
		}	
	}

	@RequestMapping(value = "/upd", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)	
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
			revisionHelper.updText(t08vis.getT08vdo().getT05itm(), true);
			return new RestDefaultReturn<T08VIS>(true, t08vis);
		}	
	}
	
	@RequestMapping(value = "/incasecond", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)	
	public RestDefaultReturn<T08VIS> incASecond(@RequestParam final int cd_item_subtitle)  {
		T08VIS t08vis = t08visService.findOne(cd_item_subtitle);
		if (t08vis == null) {
			return new RestDefaultReturn<T08VIS>(false, "Record not found!");
		} else {
			t08vis.setNrStart(t08vis.getNrStart() + 1);
			t08vis.setNrEnd(t08vis.getNrEnd() + 1);
			t08vis.setDtAlteracao(new Date());
			t08visService.save(t08vis);
			return new RestDefaultReturn<T08VIS>(true, t08vis);
		}	
	}
	
	@RequestMapping(value = "/decasecond", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)	
	public RestDefaultReturn<T08VIS> decASecond(@RequestParam final int cd_item_subtitle)  {
		T08VIS t08vis = t08visService.findOne(cd_item_subtitle);
		if (t08vis == null) {
			return new RestDefaultReturn<T08VIS>(false, "Record not found!");
		} else {
			t08vis.setNrStart(t08vis.getNrStart() - 1);
			t08vis.setNrEnd(t08vis.getNrEnd() - 1);
			t08vis.setDtAlteracao(new Date());
			t08visService.save(t08vis);
			return new RestDefaultReturn<T08VIS>(true, t08vis);
		}	
	}
	
	
	@RequestMapping(value = "/importsrt", method = RequestMethod.POST)
	public RestDefaultReturn<T08VIS> uploadSrt(MultipartHttpServletRequest request) throws IOException {
        
		long cdItem = Long.parseLong(request.getParameter("cdItem"));
        
        T05ITM t05itm = t05itmService.findOne(cdItem);
		if (t05itm == null) {
			return new RestDefaultReturn<T08VIS>(false, "Item not found!");
		} else {
			T08VDO t08vdo = t08vdoService.findByT05itm(t05itm);
			if (t08vdo == null) {
				return new RestDefaultReturn<T08VIS>(false, "Video not found!");
			} else {	
				MultipartFile file = request.getFile("file");
		        
				t08visService.delete(t08visService.findByT08vdo(t08vdo));
				
				String strFileContent = IOUtils.toString(file.getInputStream());
				subtitleHelper.addSubtitleFromFileStrContent(t08vdo, strFileContent);
				
				revisionHelper.updText(t05itm, true);
				
				return new RestDefaultReturn<T08VIS>(true, t08visService.findByT08vdo_t05itmOrderByNrStart(t05itm));
			}	
		}	
	}	
	
	@RequestMapping(value = "/importlyrics", method = RequestMethod.POST)
	public RestDefaultReturn<T08VIS> uploadLyrics(@RequestBody SubtitleImportLyricsRequestDTO subtitleImportLyricsRequestDTO) throws IOException {
        
        T05ITM t05itm = t05itmService.findOne(subtitleImportLyricsRequestDTO.getCdItem());
		if (t05itm == null) {
			return new RestDefaultReturn<T08VIS>(false, "Item not found!");
		} else {
			T08VDO t08vdo = t08vdoService.findByT05itm(t05itm);
			if (t08vdo == null) {
				return new RestDefaultReturn<T08VIS>(false, "Video not found!");
			} else {	
				t08visService.delete(t08visService.findByT08vdo(t08vdo));
				
				subtitleHelper.addSubtitleFromFileLyrics(t08vdo, subtitleImportLyricsRequestDTO.getLyrics());
				
				revisionHelper.updText(t05itm, true);
				
				return new RestDefaultReturn<T08VIS>(true, t08visService.findByT08vdo_t05itmOrderByNrStart(t05itm));
			}	
		}	

	}	
	
}
	