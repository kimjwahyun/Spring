package com.ict.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.model.vo.GuestBookVO;

@Repository // DAO (데이터에 접근하는 객체)에 사용하는 컴포넌트
public class GuestBookDAO {

	@Autowired // root-context에서 설정한 bean 객체를 매핑 시켜준다.
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 서비스에서 db 처리 하는 메서드를 모두 받아 주어야 한다.
	// 전체보기 | 반환값이 List<GuestBookVO> 
	public List<GuestBookVO> guestlist(){
		// List<GuestBookVO> list = sqlSessionTemplate.selectList("guestbook.list");
		// return list;
		return sqlSessionTemplate.selectList("guestbook.list");
	}

	// 방명록 쓰기 (insert)
	public int getGuestBookInsert(GuestBookVO gvo) {
		//GuestBookVO gvo = sqlSessionTemplate.insert("guestbook.insert", gvo);
		return sqlSessionTemplate.insert("guestbook.insert", gvo);
	}
	
	// 상세보기
	public GuestBookVO getGuestBookOneList(String idx) {
		return sqlSessionTemplate.selectOne("guestbook.onelist", idx);
	}
	
}
