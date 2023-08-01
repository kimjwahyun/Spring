package com.ict.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.model.vo.GuestBookVO;

@Repository
public class GuestBookDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 서비스에서 db 처리 하는 메서드를 모두 받아 주어야 한다.
	// 리스트 
	public List<GuestBookVO> getGuestBookList(){
		return sqlSessionTemplate.selectList("guestbook.list");
	}
	
	// 삽입
	public int getGuestBookInsert(GuestBookVO gvo) {
		return sqlSessionTemplate.insert("guestbook.insert", gvo);
	}
}
