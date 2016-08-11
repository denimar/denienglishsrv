package com.denimar.denienglishsrv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.denimar.denienglishsrv.domain.T07CTD;
import com.denimar.denienglishsrv.domain.T07TXT;
import com.denimar.denienglishsrv.dto.TextContentRequestDTO;
import com.denimar.denienglishsrv.service.T07CTDService;
import com.denimar.denienglishsrv.service.T07TXTService;
import com.denimar.denienglishsrv.vo.RestDefaultReturn;

@RestController
@RequestMapping("/text")
@CrossOrigin
public class TextController {

	@Autowired	
	private T07TXTService t07txtService;
	@Autowired	
	private T07CTDService t07ctdService;
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)	
	public RestDefaultReturn<T07TXT> getTextosItem(@RequestParam("cd_item") final long cd_item)  {
		List<T07TXT> list = t07txtService.findByT05itm_CdItemOrderByNrSeq(cd_item);
		if (list.size() == 0) {
			return new RestDefaultReturn<T07TXT>(false, "Item not found!");	
		} else {
			return new RestDefaultReturn<T07TXT>(true, list);
		}	
	}	
	
	@RequestMapping(value = "/content/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestDefaultReturn<T07CTD> getTextContent(@RequestParam("cd_texto") final int cd_texto)  {
		T07CTD t07ctd = t07ctdService.findByT07txt_CdTexto(cd_texto);
		if (t07ctd == null) {
			return new RestDefaultReturn<T07CTD>(false, "Text not found!");	
		} else {
			return new RestDefaultReturn<T07CTD>(true, t07ctd);
		}	
	}	
 
	@RequestMapping(value = "/content/set", produces = MediaType.APPLICATION_JSON_VALUE)
	public RestDefaultReturn<T07CTD> seTextContent(@RequestBody TextContentRequestDTO textContentRequestDTO)  {
		T07CTD t07ctd = t07ctdService.findByT07txt_CdTexto(textContentRequestDTO.getCd_texto());
		if (t07ctd == null) {
			return new RestDefaultReturn<T07CTD>(false, "Text not found!");	
		} else {
			t07ctd.setTxConteudo(textContentRequestDTO.getTx_conteudo());
			t07ctdService.save(t07ctd);
			return new RestDefaultReturn<T07CTD>(true, t07ctd);
		}	
	}
	
}
