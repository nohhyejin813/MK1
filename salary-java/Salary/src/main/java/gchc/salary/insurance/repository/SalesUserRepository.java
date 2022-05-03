package gchc.salary.insurance.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import gchc.salary.insurance.domain.SalesMember;
import gchc.salary.insurance.domain.SalesUser;
import gchc.salary.insurance.parameter.SalesMemberParameter;
import gchc.salary.insurance.parameter.SalesUserParameter;

/*
 * */

@Repository
public interface SalesUserRepository {
	SalesUser get(int memberId);

	SalesUser login(String id, String password);
	
//	void insertUser(SalesUserParameter user);
//	SalesUser insertUser(String id, String password, String name, String email);
//	void insertUser(SalesUser salesUser);
	void insertUser(SalesUser user); // 되는거

	
	void register(SalesUserParameter member);
	
}
