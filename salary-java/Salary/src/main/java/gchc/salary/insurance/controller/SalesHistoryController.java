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
	
	// ????????????
	@PostMapping("/list")
	@ApiOperation(value = "?????? ??????", notes = "???????????? ????????? ????????? ??? ????????????.")
	public List<SalesHistory> getList(){
		return SalesHistoryService.getList();
	}	
	
	// ?????? ????????? 
	@ApiOperation(value = "????????? ??? ?????????", notes = "????????? ????????? ???????????? ????????????.")
	@PostMapping(value = "/historyList")
	public Object getHistoryList (@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) throws Exception {			

		//?????? ????????? row ??????, page ?????? ??????
		final int rowPerPage = 1;
    	int nowPage = currentPage;
		int startRow = (nowPage - 1) * rowPerPage;
		
		Map<String, Object> param = new HashMap();
		
		param.put("startRow", startRow);
		param.put("endRow", rowPerPage);

		Map<String, Object> mp = new HashMap<String, Object>();
		List<Map<String,Object>> list = SalesHistoryService.getListPage(param);		
		
		//?????? total Count
		int listCount = SalesHistoryService.boardListCnt(param);

		// ?????????
//        System.out.println("??? ????????? ???  : " + listCount);
        
        // ????????? ?????????, ??? ????????? ??? ??????
        
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
		mp.put("board_info_name", "?????????????????????");
		mp.put("board_list", list);
		mp.put("paging", paging);
		mp.put("page_array", pageArray);

		
		Object result = mp;
//		System.out.println(result);

		return result;
		
	}
	
	// ?????? ????????? 
		@ApiOperation(value = "????????? Top5", notes = "????????? ????????? ????????? Top5 ????????????.")
		@PostMapping(value = "/listTop5")
		public Object listTop5() throws Exception{			

			
			Map<String, Object> param = new HashMap();
			
			param.put("startRow", 0);
			param.put("endRow", 5);

			Map<String, Object> mp = new HashMap<String, Object>();
			List<Map<String,Object>> list = SalesHistoryService.getListPage(param);		
			
			//?????? total Count
			int listCount = SalesHistoryService.boardListCnt(param);

			mp.put("board_info_name", "?????????????????????");
			mp.put("board_list", list);
			
			Object result = mp;
//			System.out.println(result);

			return result;
			
		}	
	
//	// ?????? ????????? 
//		@ApiOperation(value = "????????? ??? ?????????", notes = "????????? ????????? ???????????? ????????????.")
//		@GetMapping(value = "/listOld")
//		//public ListResult<SalesHistory> getListPage(
//		public Object getlistOld (@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) throws Exception {			
////			  @RequestParam(value = "salesMemo", required = false) String salesMemo,
////		      @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo) throws Exception {
//
//			//?????? ????????? row ??????, page ?????? ??????
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
//			//?????? total Count
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
//			// ?????? ??????
//			int pre = pageMin -1;
//			if(pre < 1) {
//				pre = 1;
//			}
//			
//			// ????????????
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
//			mp.put("board_info_name", "?????????????????????");
//			
//			Object result = mp;
//			System.out.println(result);
//
//			return result;
//			
//		}	
	
	/*
	 * ?????? ??????.
	 * @param member
	 */	
//	@ResponseBody
//	@PostMapping("/write")
//	@ApiOperation(value = "?????? ??????", notes = "????????? ??????")
//	 public int write(SalesHistoryParameter parameter) {
//		SalesHistoryService.write(parameter);
//		return parameter.getSalesHistoryId();
//	}	
	
	
	/* TODO : signup2 ???????????? */
	@ResponseBody
	@PostMapping("/write")
	@ApiOperation(value = "?????? ??????", notes = "????????? ??????")
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
