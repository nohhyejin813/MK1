package gchc.salary.insurance.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import gchc.salary.insurance.domain.SalesMember;
import gchc.salary.insurance.parameter.SalesMemberParameter;

/*
 * */

@Repository
public interface SalesMemberRepository {
	List<SalesMember> getList();
	
	SalesMember get(int memberId);
	
	SalesMember detail(String id);
	
//	SalesMember signin(String id, String password);
	
	
	void join(SalesMemberParameter member);
	
	void update(SalesMemberParameter member);
	
	void delete(String id);
}
