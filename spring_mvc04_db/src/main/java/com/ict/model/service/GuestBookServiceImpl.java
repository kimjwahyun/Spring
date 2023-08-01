package com.ict.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.model.dao.GuestBookDAO;
import com.ict.model.vo.GuestBookVO;
@Service
public class GuestBookServiceImpl implements GuestBookService{
 
	// DAO에 가서 DB 처리 결과를 받아오자 
	@Autowired
	private GuestBookDAO guestBookDAO;
	
	public GuestBookDAO getGuestBookDAO() {
		return guestBookDAO;
	}

	public void setGuestBookDAO(GuestBookDAO guestBookDAO) {
		this.guestBookDAO = guestBookDAO;
	}

	@Override
	public List<GuestBookVO> getGuestBookList() {
		return guestBookDAO.getGuestBookList();
	}

	@Override
	public GuestBookVO getGuestBookOneList(String idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGuestBookInsert(GuestBookVO gvo) {
		
		return guestBookDAO.getGuestBookInsert(gvo);
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
