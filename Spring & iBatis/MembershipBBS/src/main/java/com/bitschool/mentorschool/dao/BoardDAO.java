package com.bitschool.mentorschool.dao;

import java.math.BigInteger;
import java.util.List;

import com.bitschool.mentorschool.vo.BoardVO;

public interface BoardDAO {
	
	int insert(BoardVO board);
	
	BoardVO select(BigInteger bno);
	
	List<BoardVO> selectList(int page, int pageSize);
	
	int update(BoardVO board);
	
	int delete(BoardVO board);
	
	int deleteByBno(BigInteger bno);
	
	int getTotalPages(int pageSize);
	
}
