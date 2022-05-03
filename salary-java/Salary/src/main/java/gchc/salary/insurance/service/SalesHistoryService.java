package gchc.salary.insurance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
	
	  // 리스트 조회
//	  public List<SalesHistory> getListPage(Map<String, Object> param) {
//		  return repository.selectPostByBoard(param);
//	  }	
		public List<Map<String,Object>> getListPage(Map<String,Object> param) throws Exception{
			
//			List<Map<String, Object>> list = repository.selectList(param);
//			return list;
			return repository.selectList(param);
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
