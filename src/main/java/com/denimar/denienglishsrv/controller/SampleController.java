package com.denimar.denienglishsrv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {
	
    @RequestMapping("/hello")
    @ResponseBody
    String home() {
<<<<<<< HEAD
        return "Hello World 888 !";
=======
        return "Hello World 777 !";
>>>>>>> 38a9d35b9c95a828193191b39bc096e537cca477
    }	

}
