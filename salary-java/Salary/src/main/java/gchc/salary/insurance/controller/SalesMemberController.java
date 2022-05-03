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

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import gchc.salary.insurance.domain.SalesMember;
import gchc.salary.insurance.parameter.SalesMemberParameter;
import gchc.salary.insurance.repository.SalesMemberRepository;
import gchc.salary.insurance.service.SalesMemberService;

/*
 * 게시판 컨트롤러
 * @author 노자바
-	REST AP의 URI정보와 입력 parameter에 대한 Validation을 처리한다.
-	실제 동작하는 로직
-	사용자에게 입력을 받고 돌려주는 역할
 * */

@RestController
@RequestMapping("/member")
@Api(tags = "영업담당자 API")
public class SalesMemberController {
	@Autowired
	private SalesMemberService memberService;
	
	// 목록리턴
	@GetMapping
	@ApiOperation(value = "목록 조회", notes = "게시물 목록을 조회할 수 있습니다.")
	public List<SalesMember> getList(){
		return memberService.getList();
	}
	
	/*
	 * 
	 * 상세보기
	 */	
	@ApiOperation(value = "상세 조회", notes = "회원 번호에 해당하는 상세정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberId", value = "Member 번호", example = "1")
	})
	@GetMapping("/{memberId}")
	public SalesMember get(@PathVariable int memberId) {
		return memberService.get(memberId);
	}
	
	/*
	 * 
	 * id  조회 상세보기
	 */	
	@ApiOperation(value = "id  조회 상세보기", notes = "회원 번호에 해당하는 상세정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "Member id", example = "test")
	})
//	@GetMapping("/{id}")
	@GetMapping("/detail/{id}")
	public SalesMember detail(@PathVariable String id) {
		return memberService.detail(id);
	}	
	
	
	
	/*
	 * 등록/수정 처리.
	 * @param member
	 */	
	@ResponseBody
	@PostMapping("/join")
	@ApiOperation(value = "등록 처리", notes = "신규 회원 저장 및 기존 회원 업데이트가 가능합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", example = "test"),
		@ApiImplicitParam(name = "password", value = "password", example = "1234")
	})
	 public int join(SalesMemberParameter parameter) {
		memberService.join(parameter);
		return parameter.getMemberId();
	}
	
	/*
	 * 등록/수정 처리.
	 * @param member
	 */	
	@PostMapping("/update")
	@ApiOperation(value = "수정 처리", notes = "기존 회원 업데이트합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", example = "test"),
		@ApiImplicitParam(name = "password", value = "password", example = "1234")
	})
	 public int update(SalesMemberParameter parameter) {
		memberService.update(parameter);
		return parameter.getMemberId();
	}	


	/* 
	 * 삭제처리 
	 * @param Seq
	 * */
	// SWAGGER (VUE api 호출시 not working....)
	@DeleteMapping("/{id}")
	@ApiOperation(value = "삭제 처리", notes = "아이디에 해당하는 정보를 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "아이디", example = "test"),
	})
	public boolean delete(@PathVariable String id) {
		SalesMember member = memberService.detail(id);
		if(member == null) {
			return false;
		}
		memberService.delete(id);
		return true;
	}
	
}
