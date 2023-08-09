package com.ict.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.model.dao.GuestBookDAO;
import com.ict.model.vo.GuestBookVO;
@Service
public class GuestBookServiceImpl implements GuestBookService{
	// DAO 호출
	@Autowired
	private GuestBookDAO guestBookDAO;
	
	// @Override를 하는 이유 : GuestBookServiceImpl은 GuestBookService을 상속 받으므로써
	// 					   GuestBookService의 모든 메서드를 써야한다.
	@Override
	public List<GuestBookVO> getGuestBookList() {
		// List<GuestBookVO> list = guestBookDAO.guestlist();
		// return list;
		return guestBookDAO.guestlist();
	}
	
	// 방명록 쓰기
	@Override
	public int getGuestBookInsert(GuestBookVO gvo) {
		return guestBookDAO.getGuestBookInsert(gvo);
	}
	
	@Override
	public GuestBookVO getGuestBookOneList(String idx) {
		// GuestBookVO gvo = guestBookDAO.getGuestBookOneList();
		// return gvo;
		return guestBookDAO.getGuestBookOneList(idx);
	}

	@Override
	public int getGuestBookUpdate(GuestBookVO gvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getGuestBookDelete(String idx) {
		// TODO Auto-generated method stub
		return 0;
	}

}
