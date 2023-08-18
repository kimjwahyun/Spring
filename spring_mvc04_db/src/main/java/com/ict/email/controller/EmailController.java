package com.ict.email.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.email.model.service.MailService;

@Controller
public class EmailController {
	@Autowired
	private MailService mailService;
	
	@RequestMapping("/email_send.do")
	public ModelAndView sendMail() {
		try {
			ModelAndView mv = new ModelAndView("redirect:/");
			
			// 혹시 DB처리, 기타 등
			
			// 임시번호 만들기
			Random random = new Random();
            String randNumber = String.valueOf(random.nextInt(1000000) % 1000000);
            if(randNumber.length() < 6) {
                int substract = 6 - randNumber.length();
                StringBuffer sb = new StringBuffer();
                for(int i=0; i<substract; i++) {
                    sb.append("0");
                }

                sb.append(randNumber);
                randNumber = sb.toString();

            }
			// 임시번호 DB에 저장해서 사용
            mailService.sendemail(randNumber, "whkgus94@naver.com");
            return mv;
		} catch (Exception e) {
			return null;
		}
	}
}
