package com.ict.controller;

import java.util.List;

import org.checkerframework.checker.units.qual.mol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.GuestBookService;
import com.ict.model.vo.GuestBookVO;

// MCV에서 DB를 쓸 때 최종 목적은 정보를 검색해서 가져오기 위함.
// jsp => Controller => Service => DAO => mapper => DB
// 이고 Controller에서 ModelAndView 객체를 만들어서 검색한 정보를 jsp에서 출력함.

// Controller 어노테이션 설정
// spring 컨테이너에서 자바 클래스들을 bean 객테로 등록해 활용하기 위함.
@Controller
public class GuestBookController {
	// @Autowired : GuestBookService 클래스를 스프링에서 사용하기 위해서 쓴다.
	// 이때의 변수명은 guestBookService 클래스명의 맨 앞글자만 소문자로 설정.
	// GuestBookService의 모든 메서드를 사용할 수 있다.
	@Autowired
	private GuestBookService guestBookService;
	
	// 일처리가 있으면 서비스로 이동하자!
	// 리스트
	@GetMapping("/guestbook_list.do")
	public ModelAndView getGuestList() {
		// new ModelAndView("객체 생성 후 갈곳 지정");
		ModelAndView mv = new ModelAndView("guestbook/list");
		List<GuestBookVO> glist = guestBookService.getGuestBookList();
		// 전체보기를 위한 guestBookService 클래스의 guestList 메서드를 호출
		// Controller => Service => DAO => mapper => DB 에서 가져온 정보를 list라는 변수에 저장
		mv.addObject("glist", glist); // list를 jsp에서 쓰기위에 list라는 변수에 저장
		// 갈곳과 데이터를 저장해서 mv를 반환한다.
		// (list.jsp로 가서 list라는 객체 데이터를 전달)
		return mv; 
	}
	
	// 방명록 글쓰기 페이지로 (단순)이동
	// 단순 이동은 Controller에서 끝
	// 그냥 단순 jsp로 가는거라서 파라미터값이 없음
	@GetMapping("/guestbookAddForm.do")
	public ModelAndView getguestbookAddForm() {
		return new ModelAndView("guestbook/write");
	}
	
	// 글을 다쓰고 난 후 저장버튼 
	@PostMapping("/guestbook_writeOK.do")
	public ModelAndView getGuestBookInsert(GuestBookVO gvo) {
		// **스프링에서는 인자값으로 VO를 받으면 jsp에서 보낸 파라미터를 자동 매핑 시켜준다.
		// 파라미터 값은 그냥 vo를 쓰면된다.
		// redirect : 기존의 request(파라미터)를 끊고, 새로운 request를 보냄
		// => 방명록(글)을 다 썼으면 목록을 보여주기 위함.
		ModelAndView mv = new ModelAndView("redirect:/guestbook_list.do");
		int result = guestBookService.getGuestBookInsert(gvo);
		return mv;
	}
	
	@GetMapping("/guestbook_oneList.do")
	public ModelAndView getGuestBookOneList(@ModelAttribute("idx") String idx) {
		// mv => 객체 생성 후 갈 곳 저장
		ModelAndView mv = new ModelAndView("guestbook/onelist");
		GuestBookVO gvo = guestBookService.getGuestBookOneList(idx);
		// DB로 가서 한줄 검색 후 가져옴
		mv.addObject("gvo",gvo);
		return mv;
	}

	@PostMapping("/guestbook_delete_Form.do")
	public ModelAndView getGuestBook
	
}
