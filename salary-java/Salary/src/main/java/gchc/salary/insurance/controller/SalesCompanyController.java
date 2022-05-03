package gchc.salary.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import gchc.salary.insurance.domain.SalesMember;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import gchc.salary.insurance.domain.SalesCompany;
import gchc.salary.insurance.parameter.SalesCompanyParameter;
import gchc.salary.insurance.repository.SalesCompanyRepository;
import gchc.salary.insurance.service.SalesMemberService;
import gchc.salary.insurance.service.SalesCompanyService;


@RestController
@RequestMapping("/company")
@Api(tags = "가망 기업관리 API")
public class SalesCompanyController {
	@Autowired
	private SalesCompanyService salesCompanyService;
	
	// 목록리턴
	@GetMapping
	@ApiOperation(value = "목록 조회", notes = "가망 기업 목록을 조회할 수 있습니다.")
	public List<SalesCompany> getList(){
		return salesCompanyService.getList();
	}	
}
