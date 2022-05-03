package gchc.salary.insurance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import gchc.salary.insurance.domain.SalesMember;
import gchc.salary.insurance.parameter.SalesMemberParameter;
import gchc.salary.insurance.repository.SalesMemberRepository;

/*
 * */

@Service
public class SalesMemberService {
	@Autowired
	private SalesMemberRepository repository;
	
	// 목록리턴
	public List<SalesMember> getList(){
		return repository.getList();
	}
	
	//상세보기
	public SalesMember get(int memberId) {
		return repository.get(memberId);
	}
	
	
	// id 상세 보기
	//상세보기
	public SalesMember detail(String id) {
		return repository.detail(id);
	}

	// 등록처리
	public void join(SalesMemberParameter parameter) {
		// 조회하여 리턴된 정보
		SalesMember member = repository.get(parameter.getMemberId());
		repository.join(parameter);
		
	}
	
	// 수정처리
	public void update(SalesMemberParameter parameter) {
		// 조회하여 리턴된 정보
		//Member member = repository.get(parameter.getSeq());
		repository.update(parameter);
		
	}	
	

	public void delete(String id) {
		repository.delete(id);
		
	}
	

	
	
}
