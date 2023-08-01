package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.GuestBookService;
import com.ict.model.vo.GuestBookVO;


@Controller
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	public GuestBookService getGuestBookService() {
		return guestBookService;
	}

	public void setGuestBookService(GuestBookService guestBookService) {
		this.guestBookService = guestBookService;
	}

	// 일처리가 있으면 서비스로 가자 !
	@GetMapping("/guestbook_list.do")
	public ModelAndView getGuestBookList(){
		ModelAndView mv = new ModelAndView("guestbook/list");
		List<GuestBookVO> glist = guestBookService.getGuestBookList();
		mv.addObject("glist", glist);
		return mv;
	}
	
	@GetMapping("/guestbookAddForm.do")
	public ModelAndView getGuestbookAddForm() {
		return new ModelAndView("guestbook/write");
	}
	
	@PostMapping("/guestbook_writeOK.do")
	public ModelAndView getGuestBookInsert(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:/guestbook_list.do");
		int result = guestBookService.getGuestBookInsert(gvo);
		return mv;
	}

}





