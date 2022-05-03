package gchc.salary.insurance.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import gchc.salary.insurance.domain.PageInfo;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import gchc.salary.insurance.domain.SalesHistory;
import gchc.salary.insurance.parameter.SalesHistoryParameter;
import gchc.salary.insurance.parameter.SalesMemberParameter;
import gchc.salary.insurance.repository.SalesHistoryRepository;
import gchc.salary.insurance.service.SalesMemberService;
import gchc.salary.insurance.util.Pagination;
import gchc.salary.insurance.service.SalesHistoryService;


@RestController
@RequestMapping("/history")
@Api(tags = "SalesHistory API")
public class SalesHistoryController {
	@Autowired
	private SalesHistoryService SalesHistoryService;
	
	// 목록리턴
	@PostMapping("/list")
	@ApiOperation(value = "목록 조회", notes = "영업활동 목록을 조회할 수 있습니다.")
	public List<SalesHistory> getList(){
		return SalesHistoryService.getList();
	}	
	
	// 목록 페이징 
	@ApiOperation(value = "게시판 글 리스트", notes = "게시판 게시글 리스트를 조회한다.")
	@PostMapping(value = "/historyList")
	public Object getHistoryList (@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) throws Exception {			

		//기본 데이터 row 갯수, page 표기 갯수
		final int rowPerPage = 1;
    	int nowPage = currentPage;
		int startRow = (nowPage - 1) * rowPerPage;
		
		Map<String, Object> param = new HashMap();
		
		param.put("startRow", startRow);
		param.put("endRow", rowPerPage);

		Map<String, Object> mp = new HashMap<String, Object>();
		List<Map<String,Object>> list = SalesHistoryService.getListPage(param);		
		
		//목록 total Count
		int listCount = SalesHistoryService.boardListCnt(param);

		// 확인용
//        System.out.println("총 게시글 수  : " + listCount);
        
        // 클릭한 페이지, 총 게시글 수 전달
        
        PageInfo paging = Pagination.getPageInfo(currentPage, listCount);       
        
        int startPage = paging.getStartPage();
        int endPage = paging.getEndPage();
		List<Integer> intPage = new ArrayList<>();
		for(int i = startPage ; i<=endPage ; i++){
			intPage.add(i);	
		}	
		ArrayList<Integer> pageArray = new ArrayList<Integer>(intPage);        

		
//        System.out.println(paging);
		int pre = paging.getPre();
		int next = paging.getNext();
		mp.put("next", next);
		mp.put("pre", pre);
		mp.put("board_info_name", "기업활동리스트");
		mp.put("board_list", list);
		mp.put("paging", paging);
		mp.put("page_array", pageArray);

		
		Object result = mp;
//		System.out.println(result);

		return result;
		
	}
	
	// 목록 페이징 
		@ApiOperation(value = "리스트 Top5", notes = "게시판 게시글 리스트 Top5 조회한다.")
		@PostMapping(value = "/listTop5")
		public Object listTop5() throws Exception{			

			
			Map<String, Object> param = new HashMap();
			
			param.put("startRow", 0);
			param.put("endRow", 5);

			Map<String, Object> mp = new HashMap<String, Object>();
			List<Map<String,Object>> list = SalesHistoryService.getListPage(param);		
			
			//목록 total Count
			int listCount = SalesHistoryService.boardListCnt(param);

			mp.put("board_info_name", "기업활동리스트");
			mp.put("board_list", list);
			
			Object result = mp;
//			System.out.println(result);

			return result;
			
		}	
	
//	// 목록 페이징 
//		@ApiOperation(value = "게시판 글 리스트", notes = "게시판 게시글 리스트를 조회한다.")
//		@GetMapping(value = "/listOld")
//		//public ListResult<SalesHistory> getListPage(
//		public Object getlistOld (@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) throws Exception {			
////			  @RequestParam(value = "salesMemo", required = false) String salesMemo,
////		      @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) throws Exception {
//
//			//기본 데이터 row 갯수, page 표기 갯수
//			final int rowPerPage = 1;
//	    	final int pagePerBlock = 10; 
//	    	//String pageNo = param.get("pageNo").toString().isEmpty() ? "1" : param.get("pageNo").toString();
//	    	int nowPage = pageNo;
//	    	//System.out.println("pageNo : "+nowPage);
//			int startRow = (nowPage - 1) * rowPerPage;
//			
//			Map<String, Object> param = new HashMap();
//			
//			param.put("startRow", startRow);
//			param.put("endRow", rowPerPage);    	
//			
//			//목록 total Count
//			int totalCnt = SalesHistoryService.boardListCnt(param);
//			
//			int totalPage, startPage, endPage, no;
//			
//			totalPage = (int) Math.ceil((double)totalCnt/rowPerPage);
//			startPage = nowPage - (nowPage - 1) % pagePerBlock;
//			endPage = startPage + pagePerBlock-1;
//			
//			if (endPage > totalPage) {
//				endPage = totalPage;
//			}
//			
//			no = totalCnt - ((nowPage - 1) * rowPerPage);		
//			
//			int pageMin = ((nowPage -1) / pagePerBlock) * pagePerBlock + 1;
//			int pageMax = pageMin + (pagePerBlock -1);
//			if(pageMax > totalPage){
//				pageMax = totalPage;
//			}		
//			
//			List<Integer> intPage = new ArrayList<>();
//			for(int i = pageMin ; i<=pageMax ; i++){
//				intPage.add(i);	
//			}	
//			ArrayList<Integer> pageArray = new ArrayList<Integer>(intPage);		
//
//			// 이전 버튼
//			int pre = pageMin -1;
//			if(pre < 1) {
//				pre = 1;
//			}
//			
//			// 다음버튼
//			int next = pageMax + 1;
//			if (next > totalPage) {
//				next = totalPage;
//			}			
//
//			Map<String, Object> mp = new HashMap<String, Object>();
//			List<Map<String,Object>> list = SalesHistoryService.getListPage(param);		
//	    	
//			
//	 		mp.put("startPage", startPage); 
//			mp.put("pagePerBlock", pagePerBlock);
//			mp.put("pageNo", nowPage); 
//			mp.put("totalCnt", totalCnt);
//			mp.put("totalPage", totalPage);
//			mp.put("board_list", list);
//			mp.put("no", no);
//			mp.put("next", next);
//			mp.put("pre", pre);
//			mp.put("page_array", pageArray);
//			mp.put("board_info_name", "기업활동리스트");
//			
//			Object result = mp;
//			System.out.println(result);
//
//			return result;
//			
//		}	
	
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
