package gchc.salary.insurance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import gchc.salary.insurance.domain.SalesMember;
import gchc.salary.insurance.domain.SalesUser;
import gchc.salary.insurance.domain.PageModel;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import gchc.salary.insurance.domain.SalesHistory;
import gchc.salary.insurance.parameter.SalesHistoryParameter;
import gchc.salary.insurance.parameter.SalesMemberParameter;
import gchc.salary.insurance.repository.SalesHistoryRepository;
import gchc.salary.insurance.response.ListResult;
import gchc.salary.insurance.service.SalesMemberService;
import gchc.salary.insurance.util.PagingUtils;
import gchc.salary.insurance.service.ResponseService;
import gchc.salary.insurance.service.SalesHistoryService;


@RestController
@RequestMapping("/history")
@Api(tags = "SalesHistory API")
public class SalesHistoryController {
	@Autowired
	private SalesHistoryService SalesHistoryService;
	private ResponseService responseService;
	
	// 목록리턴
	@PostMapping("/list")
	@ApiOperation(value = "목록 조회", notes = "영업활동 목록을 조회할 수 있습니다.")
	public List<SalesHistory> getList(){
		return SalesHistoryService.getList();
	}	
	
	
	// 목록 페이징 
	@ApiOperation(value = "게시판 글 리스트", notes = "게시판 게시글 리스트를 조회한다.")
	@GetMapping(value = "/listNew")
	public ListResult<SalesHistory> listNew(
//		  @RequestParam(value = "salesMemo", required = false) String salesMemo,
	      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	      @RequestParam(value = "count", required = false, defaultValue = "5") int count) {
	    PageModel pageModel = PagingUtils.page(page, count);

	    Map<String, Object> param = new HashMap();
//	    param.put("salesMemo", "%" + salesMemo + "%");
	    int total = SalesHistoryService.boardListCnt(param);
	    
	    
	    pageModel.setTotal(total);
	    List<SalesHistory> posts = SalesHistoryService.findPosts(param, pageModel);

	    PagingUtils.setTotalPage(pageModel);

	    return responseService.getListResult(pageModel, posts);
	    //return (ListResult<SalesHistory>) posts;
	}
	
	
	
	/*
	 * 등록 처리.
	 * @param member
	 */	
//	@ResponseBody
//	@PostMapping("/write")
//	@ApiOperation(value = "등록 처리", notes = "게시글 등록")
//	 public int write(SalesHistoryParameter parameter) {
//		SalesHistoryService.write(parameter);
//		return parameter.getSalesHistoryId();
//	}	
	
	
	/* TODO : signup2 확인하기 */
	@ResponseBody
	@PostMapping("/write")
	@ApiOperation(value = "등록 처리", notes = "게시글 등록")
	public ResponseEntity<Map <String, Object>> signup2(@RequestBody SalesHistoryParameter parameter, HttpServletResponse res) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			SalesHistoryService.write(parameter);
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;					

			
		} catch (RuntimeException e) {
				resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}	
}
