package gchc.salary.insurance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import gchc.salary.insurance.domain.PageModel;
import gchc.salary.insurance.domain.SalesHistory;
import gchc.salary.insurance.parameter.SalesHistoryParameter;
import gchc.salary.insurance.repository.SalesHistoryRepository;

/*
 * */

@Service
public class SalesHistoryService {
	@Autowired
	private SalesHistoryRepository repository;
	
	// 목록리턴
	public List<SalesHistory> getList(){
		return repository.getList();
	}
	
	  // 게시판 이름으로 게시물 리스트 조회
	  public List<SalesHistory> findPosts(Map<String, Object> param, PageModel page) {
	    param.put("limit", page.getLimit());
	    param.put("count", page.getCount());
	    System.out.println(param);
	    return repository.selectPostByBoard(param);
	  }	
	
	// 등록된 게시물 총 카운트
	public int boardListCnt(Map<String, Object> param) {
		return repository.boardListCnt(param);
	}
	  
	//상세보기
	public SalesHistory get(int salesHistoryId) {
		return repository.get(salesHistoryId);
	}
		
	
	// 글등록
	public void write(SalesHistoryParameter parameter) {
		// 조회하여 리턴된 정보
		SalesHistory board = repository.get(parameter.getSalesHistoryId());
		repository.write(parameter);
		
	}	
	
}
