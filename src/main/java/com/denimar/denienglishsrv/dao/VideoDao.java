package com.denimar.denienglishsrv.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.denimar.denienglishsrv.domain.T05ITM;
import com.denimar.denienglishsrv.domain.T08VDO;
import com.denimar.denienglishsrv.dto.CreateVideoRequestDTO;
import com.denimar.denienglishsrv.helper.ItemHelper;
import com.denimar.denienglishsrv.helper.VideoHelper;

@Repository
@Transactional
public class VideoDao {
	
	@Autowired
	private ItemHelper itemHelper;
	@Autowired
	private VideoHelper videoHelper;
	
	@Transactional
	public T08VDO addVideo(CreateVideoRequestDTO createVideoRequest) throws Exception{
		T05ITM t05itm = itemHelper.createItem(createVideoRequest.getCd_categoria(), createVideoRequest.getDs_item(), createVideoRequest.getBt_imagem_item());
		return videoHelper.createVideo(t05itm, createVideoRequest.getDs_url());
	}

}
