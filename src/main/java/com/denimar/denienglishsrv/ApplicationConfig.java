package com.denimar.denienglishsrv;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com. fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;;


@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
	    MappingJackson2HttpMessageConverter messageConverter = new  MappingJackson2HttpMessageConverter();
	
	    ObjectMapper mapper = new ObjectMapper();
	    //Registering Hibernate4Module to support lazy objects
	    mapper.registerModule(new Hibernate5Module());
	
	    messageConverter.setObjectMapper(mapper);
	    return messageConverter;
	
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    //Here we add our custom-configured HttpMessageConverter
	    converters.add(jacksonMessageConverter());
	    super.configureMessageConverters(converters);
	}
}	