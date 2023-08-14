package com.ict.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.board.model.vo.Board_VO;


@Repository
public class Board_DAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 전체 게시물의 수 구하기
	public int getTotalCount() {
		int count = sqlSessionTemplate.selectOne("board.count");
		return count;
	}

	public List<Board_VO> getList(int offset, int limit) {
		// LIMIT : 한 페이지에 출력할 데이터의 양
		// OFFSET : LIMIT * (페이지 번호 - 1)
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("limit", limit);
		map.put("offset", offset);
		return sqlSessionTemplate.selectList("board.list", map);
	}

	public List<Board_VO> getList() {
		return sqlSessionTemplate.selectList("board.list");
	}
	public int getInsert(Board_VO bv) {
		int result = sqlSessionTemplate.insert("board.insert", bv);
		return result;
	}
	public int getBoardHit(String idx) {
		int result = sqlSessionTemplate.update("board.hit", idx);
		return result;
	}
	public Board_VO getBoardOneList(String idx) {
		Board_VO bv = sqlSessionTemplate.selectOne("board.onelist", idx);
		return bv;
	}
	
	public int getLevUpdate(Map<String, Integer> map) {
		return sqlSessionTemplate.update("board.levupdate", map);
	}
	// 댓글 삽입
	public int getAnsInsert(Board_VO bv) {
		return sqlSessionTemplate.insert("board.ansinsert", bv);
	}
	
	// 원글 수정
	public int getUpdate(Board_VO bv) {
		return sqlSessionTemplate.update("board.update", bv);
	}
}
