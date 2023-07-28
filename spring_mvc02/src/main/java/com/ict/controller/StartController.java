package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {
	@GetMapping(value = "/start.do")
	public ModelAndView exec(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("admin/output");
		// 배열
		String[] dogName= {"초복이", "중복이", "말복이", "바둑이"};
		mv.addObject("dogName", dogName);
		
		return mv;
	}
}
