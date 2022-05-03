package gchc.salary.insurance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import gchc.salary.insurance.domain.SalesCompany;
import gchc.salary.insurance.parameter.SalesCompanyParameter;
import gchc.salary.insurance.repository.SalesCompanyRepository;

/*
 * 
 * TODO:
 * 
 * */

@Service
public class SalesCompanyService {
	@Autowired
	private SalesCompanyRepository repository;
	
	// 목록리턴
	public List<SalesCompany> getList(){
		return repository.getList();
	}
	
}
