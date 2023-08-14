package com.ict.board.model.service;

import java.util.List;
import java.util.Map;

import com.ict.board.model.vo.Board_VO;

public interface Board_Service {
	// 리스트
	public List<Board_VO> getList();

	// 전체 게시물 개수 구하기
	public int getTotalCount();

	// 페이징 처리를 위한 리스트
	public List<Board_VO> getList(int offset, int limit);
	
	// 삽입
	public int getInsert(Board_VO bv);
	
	// 조회수 업데이트
	public int getBoardHit (String idx);
	
	// 상세보기
	public Board_VO getBoardOneList(String idx);
	
	int getLevUpdate(Map<String, Integer> map);
	// 댓글 삽입
	int getAnsInsert(Board_VO bv);
	
	// 원글 수정
	public int getUpdate(Board_VO bv);
}
