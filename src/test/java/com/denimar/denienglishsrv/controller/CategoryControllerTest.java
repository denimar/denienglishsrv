package com.denimar.denienglishsrv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.denimar.denienglishsrv.Application;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.object.IsCompatibleType.typeCompatibleWith;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class CategoryControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;	
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testListCategories() throws Exception {
        mockMvc.perform(get("/category/list?cd_tipo=1"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.success").value(true))
	        .andExpect(jsonPath("$.total").value(33))
        	.andExpect(jsonPath("$.message").value(""));        
	}

	@Test
	public void testGetCategoria() throws Exception {
        mockMvc.perform(get("/category/get?cd_categoria=156"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.success").value(true))
	        .andExpect(jsonPath("$.total").value(1))
	    	.andExpect(jsonPath("$.message").value(""))
	    	.andExpect(jsonPath("$.records[0].ds_categoria").value("VOA Newscast"));
        
        //Try to get a nonexistent category
        mockMvc.perform(get("/category/get?cd_categoria=999999"))
   			.andExpect(jsonPath("$.success").value(false));
	}

	@Test
	public void testAddCategory() throws Exception {
		String newCategory = "New Category";
		
        mockMvc.perform(get("/category/add?cd_tipo=1&ds_categoria=" + newCategory))
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$.success").value(true))
        	.andExpect(jsonPath("$.total").value(1))
        	.andExpect(jsonPath("$.message").value(""))
        	.andExpect(jsonPath("$.records[0].t01tpo.ds_tipo").value("Texts"))    	
        	.andExpect(jsonPath("$.records[0].ds_categoria").value(newCategory));
        
        //Try to insert a repeated category
       	mockMvc.perform(get("/category/add?cd_tipo=1&ds_categoria=" + newCategory))
       		.andExpect(jsonPath("$.success").value(false));       	
	}

	@Test
	public void testDelCategory() throws Exception {
        mockMvc.perform(get("/category/del?cd_categoria=256"))
	    	.andExpect(status().isOk())
	    	.andExpect(jsonPath("$.success").value(true))
	    	.andExpect(jsonPath("$.total").value(1))
	    	.andExpect(jsonPath("$.message").value(""))
	    	.andExpect(jsonPath("$.records[0].cd_categoria").value(256));

      //Try to delete a nonexistent category
        mockMvc.perform(get("/category/del?cd_categoria=999999"))
       		.andExpect(jsonPath("$.success").value(false));       	
	}

	@Test
	public void testUpdCategory() throws Exception {
		String updCategory = "Altered Category";
		
        mockMvc.perform(get("/category/upd?cd_categoria=236&ds_categoria=" + updCategory))
    	.andExpect(status().isOk())
    	.andExpect(jsonPath("$.success").value(true))
    	.andExpect(jsonPath("$.total").value(1))
    	.andExpect(jsonPath("$.message").value(""))
    	.andExpect(jsonPath("$.records[0].ds_categoria").value(updCategory));

      //Try to update a nonexistent category
        mockMvc.perform(get("/category/upd?cd_categoria=999999&ds_categoria=" + updCategory))
       		.andExpect(jsonPath("$.success").value(false));       	

	}

}
