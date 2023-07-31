package com.ict.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.model.vo.GuestbookVO;
import com.ict.model.vo.MembersVO;

@Repository
public class GuestbookDAO {
	// 실제 Mapper를 호출하는 클래스
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sqlSessionTemplate = sessionTemplate;
	}
	
	public List<GuestbookVO> guestbookList() { 
		List<GuestbookVO> list = sqlSessionTemplate.selectList("guestbook.list");
		return list;
	}
}
