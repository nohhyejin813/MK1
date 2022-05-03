package gchc.salary.insurance.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import gchc.salary.insurance.domain.SalesHistory;
import gchc.salary.insurance.parameter.SalesHistoryParameter;

/*
 * */

@Repository
public interface SalesHistoryRepository {
	List<SalesHistory> getList();
	
	List<Map<String,Object>> selectList(Map<String,Object> param) throws Exception;
	
	List<SalesHistory> selectPostByBoard(Map<String, Object> param);
	
	int boardListCnt(Map<String, Object> param);
	
	SalesHistory get(int salesHistoryId);
	
	void write(SalesHistoryParameter board);
	
	
}
