package com.denimar.denienglishsrv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denimar.denienglishsrv.domain.T02CTG;
import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.domain.T90IMG;
import com.denimar.denienglishsrv.dto.CreateVideoRequestDTO;
import com.denimar.denienglishsrv.helper.ItemHelper;
import com.denimar.denienglishsrv.helper.VideoHelper;
import com.denimar.denienglishsrv.service.T02CTGService;
import com.denimar.denienglishsrv.service.T05ITMService;
import com.denimar.denienglishsrv.service.T08VDOService;
import com.denimar.denienglishsrv.service.T90IMGService;

@Repository
@Transactional
public class VideoDao {
	
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
	private VideoHelper videoHelper;
	
	@Transactional
	public T08VDO addVideo(CreateVideoRequestDTO createVideoRequest) throws Exception{

		T02CTG t02ctg = t02ctgService.findOne(createVideoRequest.getCd_categoria());
		if (t02ctg == null) {
			throw new Exception("Category not found!");
		}	
				
		//Create the item
		T05ITM t05itm = new T05ITM();
		t05itm.setT02ctg(t02ctg);
		t05itm.setDsItem(createVideoRequest.getDs_item());
		t05itmService.save(t05itm);
		
		//Create the video
		T08VDO t08vdo = new T08VDO();
		t08vdo.setT05itm(t05itm);
		t08vdo.setTpVideo(createVideoRequest.getTp_video());		
		t08vdo.setIdVideo(createVideoRequest.getId_Video());
		t08vdoService.save(t08vdo);

		String urlPoster = videoHelper.getUtlPoster(createVideoRequest.getTp_video(), createVideoRequest.getId_Video());
		byte[] videoImage = videoHelper.getBytesArrayFromURL(urlPoster);
		
		//Save the image
		T90IMG t90img = new T90IMG();
		t90img.setBtImagem(videoImage);
		t90img.setT05itm(t05itm);
		t90imgService.save(t90img);
		
		return t08vdo;
	}

}
