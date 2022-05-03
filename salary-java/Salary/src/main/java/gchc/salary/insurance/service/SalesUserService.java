package gchc.salary.insurance.service;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import gchc.salary.insurance.domain.SalesMember;
import gchc.salary.insurance.domain.SalesUser;
import gchc.salary.insurance.parameter.SalesMemberParameter;
import gchc.salary.insurance.parameter.SalesUserParameter;
import gchc.salary.insurance.repository.SalesUserRepository;

/*
 * */

@Service
public class SalesUserService {
	@Autowired
	private SalesUserRepository repository;
	
	// 로그인 계정은 고정값을 이용한다.
	// TODO : db 조회 변경
	public SalesUser login(String id, String password) {
		return repository.login(id, password);
	}
	
	// 계정 정보 전달함
	public String getServerInfo() {
		return "made by nonono";
	}
	
	
	// 등록처리 // 되는거
	@Options(useGeneratedKeys = true)
	public void addUser(SalesUser salesUser) {
		// 조회하여 리턴된 정보
		repository.insertUser(salesUser);
	}
	/*
	 * 
	 * 
	 * // 등록처리 public void register(SalesUserParameter parameter) { // 조회하여 리턴된 정보
	 * SalesUser member = repository.get(parameter.getMemberId());
	 * repository.register(parameter);
	 * 
	 * }
	 */
}
