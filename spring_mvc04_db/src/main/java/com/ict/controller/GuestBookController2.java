package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.GuestBookService;
import com.ict.model.vo.GuestBookVO;


@Controller
public class GuestBookController2 {
	
	@Autowired
	private GuestBookService guestBookService;
	
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
	
	// idx는 onelist.jsp에도 사용하기 때문에 넘겨야 한다.
	@GetMapping("/guestbook_oneList.do")
	public ModelAndView getGuestBookOneList(@ModelAttribute("idx") String idx){
		ModelAndView mv = new ModelAndView("guestbook/onelist");
		GuestBookVO gvo = guestBookService.getGuestBookOneList(idx);
		mv.addObject("gvo",gvo);
		return mv;
	}
	
	@PostMapping("/guestbook_delete_Form.do")
	public ModelAndView getGuestBookDeleteForm(@ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("guestbook/delete");
		// jsp에서 실제 삭제할 때 비밀번호를 검사하기 위해서 getGuestBookOneList()를 실행하자
		GuestBookVO gvo = guestBookService.getGuestBookOneList(idx);
		mv.addObject("gvo",gvo);
		return mv;
	}
	
	@PostMapping("/guestbook_delete.do")
	public ModelAndView getGuestBookDeleteOK(String idx) {
		ModelAndView mv = new ModelAndView("redirect:/guestbook_list.do");
		int result = guestBookService.getGuestBookDelete(idx);
		return mv;
	}
	
	@PostMapping("/guestbook_edit_Form.do")
	public ModelAndView getGuestBookEditForm(String idx) {
		ModelAndView mv = new ModelAndView("guestbook/update");
		GuestBookVO gvo = guestBookService.getGuestBookOneList(idx);
		mv.addObject("gvo",gvo);
		return mv;
	}
	
	@PostMapping("/guestbook_edit.do")
	public ModelAndView getGuestBookEditOK(GuestBookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:/guestbook_oneList.do?idx="+gvo.getIdx());
		int result = guestBookService.getGuestBookUpdate(gvo);
		return mv;
	}
}




