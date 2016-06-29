package com.denimar.denienglishsrv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	
    @RequestMapping("/hello")
    @ResponseBody
    String home() {
    	String teste = "Texto 1111 "
        return teste;
    }	

}
