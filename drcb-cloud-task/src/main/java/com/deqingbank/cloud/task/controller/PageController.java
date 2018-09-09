package com.deqingbank.cloud.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("host","this is my host info~");
		return "index";
	}
}
