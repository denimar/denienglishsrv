package com.denimar.denienglishsrv.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
@Transactional
public class ItemControllerTest {

	private MockMvc mockMvc;
	private String imageBase64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAADA0lEQVR42pWTWUiUURTH//fbnOWbSVKbJte0NA1LKisDE5SUJu2haKFSSgJ7iXqohB58MCjMHoJefKhpJUpConLKomghpMU2sdLUzDUdM3O+0ZlvvqVvvhHbHzpwudx77vndw/+cQ/AXu3oIJHMejPYI8MHz0FcIz1oxUXQQ6u9vyc+HhqMgeRnU/ICauJdE5q4ilgX24L3qeTOgDt+7w6LzeON7pSV7zw/QFOD1KdApMy07MWvXMS6llCecTfOGhZyqH6o4CLHVKaC/Zl/3iOdkcjHkXwC+68ZiJJQ7uYRchlAUQE8DKD7kVARA/gZVUSB23ZPUrqpSY9HE+SmAeAuz/JTjlWlZeRSR27VAA8BEgdDmUAKyF5DcGsgHlZ6D8SdVbiNcGUw++nWAp858gEvaUcXG5wP+p5A07fYffICKinU6oLKyDtWHc8BAyyRsKQKfbkNsP11uWe89Ss6WgypaaHOZ580voC0cKI6FRNmxdvNNnHdu0wHFpRdw7dJqMMoAFDEA2SPC+76l4VHHoIPUVYLNjrc1Wefy6TQvabqFgbAztJ+StZTHQhpQVi2zNqiBIU1PP2SBwdgHofnN8OBiUlsBZmV8RGN4Ir/Ex/rwss2HnBWmkICEm6yCqAt5//E4lqQawPkNGO0Unr8e/pJFqstAtiy3nAmPmlnC2sbR2KKi8dU4NjooxMeyenxPbwCXXQqyMkxYnkYQGDRh1P35XH2zZ7suYt9Feo1BSb1uTggQyuBB75cZqL3Cor9VE01rGXsKj00bRMREuLVCWODtYtUJ8q4oZqtcrwNe1IAzylGu2KTMPGZaHwgzAkIUbTfplQ6WUVUoqNJ0SN+i0dPx7C5MbkfqDohTjdR2DrNlIakhLi1vLmP2aDfd2hImvcGGioPktaD77d0PNN9RkFyCj3/MQlMNYv2eyBOzE1cWWqPTacaqjwKksQGM9TXLHzsf3jBYh3cvKkPPX4cpaM79oGMiyWJFNBQaWD5Zb/OA0EY43w33qNq07UhoBv4J+F/7DpzVJ/gPpW7RAAAAAElFTkSuQmCC";	
	
	@Autowired
    private WebApplicationContext webApplicationContext;	
	
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();		
	}

	@Test
	public void testGetAllItems() throws Exception {
        mockMvc.perform(get("/item/listall?cd_tipo=1"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.success").value(true))
	        .andExpect(jsonPath("$.total").value(40))
	    	.andExpect(jsonPath("$.message").value(""));
	}

	@Test
	public void testGetItems() throws Exception {
        mockMvc.perform(get("/item/list?cd_categoria=227"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.success").value(true))
	        .andExpect(jsonPath("$.total").value(23))
	    	.andExpect(jsonPath("$.message").value(""));
	}

	@Test
	public void testGetItem() throws Exception {
        mockMvc.perform(get("/item/get?cd_item=2476"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.success").value(true))
	        .andExpect(jsonPath("$.total").value(1))
	    	.andExpect(jsonPath("$.message").value(""))
	    	.andExpect(jsonPath("$.records[0].ds_item").value("Ozzy Osbourne - Crazy Train"));
        
        //Try to get a nonexistent item
        mockMvc.perform(get("/item/get?cd_item=999999"))
   			.andExpect(jsonPath("$.success").value(false));
	}

	@Test
	public void testAddItem() throws Exception {
        mockMvc.perform(
       		post("/item/add")
       			.param("cd_categoria", "227")
        		.param("ds_item", "inserted item")
        		.content(imageBase64)
        	)
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.success").value(true))
	        .andExpect(jsonPath("$.total").value(1))
	    	.andExpect(jsonPath("$.message").value(""));
        
        //Try to add a repeated item
        mockMvc.perform(
        	post("/item/add")
        		.param("cd_categoria", "227")
        		.param("ds_item", "inserted item")
        		.content(imageBase64)
        	)
	        .andExpect(jsonPath("$.success").value(false));
	}

	@Test
	public void testDelItem() throws Exception {
        mockMvc.perform(get("/item/del?cd_item=2476"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.success").value(true))
	        .andExpect(jsonPath("$.total").value(1))
	    	.andExpect(jsonPath("$.message").value(""))
	    	.andExpect(jsonPath("$.records[0].ds_item").value("Ozzy Osbourne - Crazy Train"));
        
        //Try to delete a nonexistent item
        mockMvc.perform(get("/item/del?cd_item=999999"))
       		.andExpect(jsonPath("$.success").value(false));       	
	}

	@Test
	public void testRenameItem() throws Exception {
        mockMvc.perform(post("/item/rename?cd_item=2476&ds_item=Renamed Item"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.success").value(true))
	        .andExpect(jsonPath("$.total").value(1))
	    	.andExpect(jsonPath("$.message").value(""))
	    	.andExpect(jsonPath("$.records[0].ds_item").value("Renamed Item"));
        
        //Try to rename a nonexistent item
        mockMvc.perform(post("/item/rename?cd_item=999999"))
       		.andExpect(jsonPath("$.success").value(false));       	
	}

	@Test
	public void testUpdImageItem() throws Exception {
        mockMvc.perform(
        	post("/item/upd-image")
        		.param("cd_item", "2476")
        		.content(imageBase64)
        	)
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.success").value(true))
	        .andExpect(jsonPath("$.total").value(1))
	    	.andExpect(jsonPath("$.message").value(""))
	    	.andExpect(jsonPath("$.records[0].ds_item").value("Ozzy Osbourne - Crazy Train"));
    
	    //Try to update a image of a nonexistent item
        mockMvc.perform(
        	post("/item/upd-image")
        		.param("cd_item", "999999")
        		.content(imageBase64)
        	)		
	        .andExpect(jsonPath("$.success").value(false));
	}

}
