package com.ict.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.checkerframework.common.reflection.qual.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.board.model.service.Board_Service;
import com.ict.board.model.vo.Board_VO;
import com.ict.common.Paging;

@Controller
public class Board_Controller {
	@Autowired
	private Board_Service board_Service;
	// 페이징 기법 사용
	@Autowired
	private Paging paging;
	// 비밀번호 암호화
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/board_list.do")
	public ModelAndView boardList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("board/board_list");
		// 페이징 기법
		// 전체 게시물의 수
		int count = board_Service.getTotalCount();
		paging.setTotalRecord(count);

		// 전체 페이지의 수
		// 전체 게시글의 수가 한 페이지안에 존재하는 원글의 수 보다 작거나 같으면 전체페이수는 1페이지
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			// 나머지가 있으면 1페이지 증가
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}
		// 맨 처음 오면 page는 null이다.
		// 현재 페이지
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		// begin, end 대시 limit, offset
		// limit = paging.getNumPerPage()
		// offset = limit * (현재페이지-1) = (getNowPage() - 1)

		paging.setOffset(paging.getNumPerPage() * (paging.getNowPage() - 1));

		// 시작블록과 끝블록 구하기
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);

		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() - 1);

		// 주위 사항 (endBlock 이 totalPage 보다 클수가 있다.)
		// 사용하는 않는 페이지번호가 나오므로 endBlock이 totalPage보다 크면
		// endBlock를 totalPage로 변경 시키자.
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}

		List<Board_VO> board_list = board_Service.getList(paging.getOffset(), paging.getNumPerPage());
		mv.addObject("board_list", board_list);
		mv.addObject("paging", paging);
		return mv;
	}

	@GetMapping("/board_insertForm.do")
	public ModelAndView boardInsertForm() {
		return new ModelAndView("board/board_write");
	}

	@PostMapping("/board_insert.do")
	public ModelAndView boardInsert(Board_VO bv, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/board_list.do");
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bv.getFile();
			if (file.isEmpty()) {
				bv.setF_name("");
			} else {
				// 같은 이름의 파일이 없도록 UUID 사용
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + bv.getFile().getOriginalFilename();
				bv.setF_name(f_name);

				// 이미지 저장
				byte[] in = bv.getFile().getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			// 패스워드 암호화
			bv.setPwd(passwordEncoder.encode(bv.getPwd()));
			int res = board_Service.getInsert(bv);

			return mv;
		} catch (Exception e) {
		}
		return null;
	}

	@GetMapping("/board_onelist.do")
	public ModelAndView boardOneList(@ModelAttribute("cPage") String cPage, @ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("board/board_onelist");
		// String idx = request.getParameter("idx");
		// String cPage = request.getParameter("cPage");

		// 조회수(hit) 업데이트
		int hit = board_Service.getBoardHit(idx);

		// 상세보기
		Board_VO bv = board_Service.getBoardOneList(idx);

		mv.addObject("bv", bv);
		mv.addObject("cPage", cPage);
		return mv;
	}

	@GetMapping("/board_down.do")
	public void boardDown(@RequestParam("f_name") String f_name, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/images/" + f_name);
			String r_path = URLEncoder.encode(path, "utf-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + r_path);

			File file = new File(new String(path.getBytes()));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			FileCopyUtils.copy(in, out);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@PostMapping("/board_ans_insertForm.do")
	public ModelAndView boardAnsInsertForm(@ModelAttribute("cPage") String cPage, @ModelAttribute("idx") String idx) {
		return new ModelAndView("board/board_ans_write");
	}

	@PostMapping("/board_ans_insert.do")
	public ModelAndView boardAnsInsert(@ModelAttribute("cPage") String cPage, @ModelAttribute("idx") String idx,
			Board_VO bv, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/board_list.do");
		try {

			// 상세보기에서 groups, step, lev를 가져온다.
			Board_VO bvo = board_Service.getBoardOneList(idx);

			int groups = Integer.parseInt(bvo.getGroups());
			int step = Integer.parseInt(bvo.getStep());
			int lev = Integer.parseInt(bvo.getLev());

			// step, lev를 하나씩 올리자
			step++;
			lev++;

			// 원글의 처음 댓글을 달면 그대로고, 원글 댓글의 대한 댓글부터 업데이트가 시작된다.
			// DB에 lev를 업데이트 하자.
			// ** groups와 같은 원글을 찾아서 레벨이 같거나 크면 레벨을 증가시키자
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("groups", groups);
			map.put("lev", lev);

			int result = board_Service.getLevUpdate(map);

			bv.setGroups(String.valueOf(groups));
			bv.setStep(String.valueOf(step));
			bv.setLev(String.valueOf(lev));

			// insert 처리
			// 첨부파일 처리
			String path = request.getSession().getServletContext().getRealPath("/resources/images");
			MultipartFile file = bv.getFile();
			if (file.isEmpty()) {
				bv.setF_name("");
			} else {
				// 같은 이름의 파일이 없도록 UUID 사용
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + bv.getFile().getOriginalFilename();
				bv.setF_name(f_name);

				// 이미지 저장
				byte[] in = bv.getFile().getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
			}
			// 패스워드 암호화
			bv.setPwd(passwordEncoder.encode(bv.getPwd()));
			// 삽입
			int res = board_Service.getAnsInsert(bv);

			return mv;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@PostMapping("/board_updateForm.do")
	public ModelAndView boardUpdateForm(@ModelAttribute("cPage") String cPage, @ModelAttribute("idx") String idx) {
		ModelAndView mv = new ModelAndView("board/board_update");
		Board_VO bv = board_Service.getBoardOneList(idx);
		mv.addObject("bv", bv);
		return mv;
	}

	@PostMapping("/board_update.do")
	public ModelAndView boardUpdate(@ModelAttribute("cPage") String cPage, @ModelAttribute("idx") String idx,
			Board_VO bv, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView();

		// 비밀번호가 맞는지 체크 하자.
		// DB에서 암호 얻기
		Board_VO bv2 = board_Service.getBoardOneList(idx);
		String dpwd = bv2.getPwd();

		// passwordEncoder.matches(암호화 되지 않은 것, 암호화 된 것)
		if (!passwordEncoder.matches(bv.getPwd(), dpwd)) {
			mv.setViewName("board/board_update");
			mv.addObject("pwchk", "fail");
			mv.addObject("bv", bv);
			return mv;
		} else {
			try {
				String path = request.getSession().getServletContext().getRealPath("/resources/images");
				MultipartFile file = bv.getFile();
				if (file.isEmpty()) {
					bv.setF_name(bv.getOld_f_name());
				} else {
					// 같은 이름의 파일이 없도록 UUID 사용
					UUID uuid = UUID.randomUUID();
					String f_name = uuid.toString() + "_" + bv.getFile().getOriginalFilename();
					bv.setF_name(f_name);

					// 이미지 저장
					byte[] in = bv.getFile().getBytes();
					File out = new File(path, f_name);
					FileCopyUtils.copy(in, out);
				}
				// 패스워드 암호화
				bv.setPwd(passwordEncoder.encode(bv.getPwd()));
				int res = board_Service.getUpdate(bv);
				if (res > 0) {
					mv.setViewName("redirect:/board_onelist.do");
					return mv;
				} else {
					return null;
				}
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
	}
}
