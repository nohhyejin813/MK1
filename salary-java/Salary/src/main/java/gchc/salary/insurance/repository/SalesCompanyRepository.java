package gchc.salary.insurance.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import gchc.salary.insurance.domain.SalesCompany;
import gchc.salary.insurance.parameter.SalesCompanyParameter;

/*
 * */

@Repository
public interface SalesCompanyRepository {
	List<SalesCompany> getList();
	
}
