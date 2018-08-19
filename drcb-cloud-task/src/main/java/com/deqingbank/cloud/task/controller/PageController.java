package com.deqingbank.cloud.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
public class PageController {

	//@RequestMapping("/taskManagement")
	public String taskManagement() {
		return "/pages/taskManagement";
	}
}
