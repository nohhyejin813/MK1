package gchc.salary.insurance.util;

import gchc.salary.insurance.domain.PageInfo;

public class Pagination {
	
	
	// PageInfo 객체를 리턴하는 static 메소드
    // currentPage와 listCount를 파라미터로 받는다.
    public static PageInfo getPageInfo(int currentPage, int listCount) {
        
        // 페이지 정보를 담아줄 PageInfo 참조변수 선언
        PageInfo pi = null;
        
        int pageLimit = 10;     // 한 페이지에서 보여질 페이지 버튼 수
        int maxPage;            // 전체 페이지 중 가장 마지막 페이지
        int startPage;          // 현재 페이지에서 보여질 페이징 버튼의 시작 페이지
        int endPage;            // 현재 페이지에서 보여질 페이징 버튼의 끝 페이지
        
        int boardLimit = 1;    // 한 페이지에서 보여질 게시글 갯수
        
        int pre;
        int next;
        
        // * maxPage :
        /*
         * 1. (총 게시글 수 / 한 페이지 게시글 수) => 실수형이 나오고, + 0.9
         * => ex) 26 / 10 = 2.6, 
         * =>     2.6 + 0.9 = 3.5
         * =>     (int)3.5 = 3
         *       총 페이지 = 1 2 3
         */
        maxPage = (int)((double)listCount / boardLimit + 0.9);
        
        // * startPage : 
        /*
         * 1. ( 클릭한 페이징 번호 / 페이지 버튼 수) => 실수형이 나오고, + 0.9
         * => ex) 5 / 10 = 0.5,
         * =>       0.5 + 0.9 = 1.4,
         * =>      (int)1.4 = 1,
         * =>      1 - 1 = 0,
         * =>      0 * 10 = 0,
         * =>      0 + 1 = 1;
         * 이로써, 5를 눌러도 1 ~ 10까지의 페이징이 표시된다. 5 ~ 14가 아니다. 
         */
        startPage = (((int)((double)currentPage / pageLimit + 0.9)) - 1) * pageLimit + 1;
        
        // * endPage - 현재 페이지에서 보여질 마지막 페이지 수
        // endPage = 1 + 10 - 1 = 10
        endPage = startPage + pageLimit - 1;
        
        // 만약 페이징이 13까지 있다면, 20까지 표시되는 것이 아니라 13까지만 표시된다.
        if(maxPage < endPage) {
            endPage = maxPage;
        }
        
		// 이전 버튼
		pre = startPage -1;
		if(pre < 1) {
			pre = 1;
		}
		
		// 다음버튼
		next = endPage + 1;
		if (next > maxPage) {
			next = maxPage;
		}	        
        
        // PageInfo 생성자를 이용하여 새로운 인스턴스를 생성한다.
        pi = new PageInfo(currentPage, listCount, pageLimit, maxPage, startPage, endPage, boardLimit, pre, next);
        
        return pi;
    }	
	
	
//	public int blockSize = 10;
//	public int pageSize = 20;
//	public String currentPageStyle = "";
//	public String currentPageClass = "";
//	public String pageClass = "";
//	public String pageScriptFnc = "fPage";
//	public String pageHtml = "";
//
//	public String getUserPageString(int rowCount, int pageIndex) {
//		if (rowCount == 0) {
//			return "";
//		} else {
//			int totalPage = rowCount / this.pageSize + (rowCount % this.pageSize > 0 ? 1 : 0);
//			int curBlock = (pageIndex - 1) / this.blockSize + 1;
//			int startPage = (curBlock - 1) * this.blockSize + 1;
//			int endPage = curBlock * this.blockSize;
//			if (endPage > totalPage) endPage = totalPage; 
//			
//			boolean isShowPrev = (startPage > 1 ? true : false);
//	        boolean isShowNext = (endPage < totalPage ? true : false);
//
//
//			
//			String pageNum = "";
//
//			for (int i = startPage; i <= endPage; ++i) {
//				if (i == pageIndex) {
//					
//					pageNum = pageNum + i;
//					
//
//				} else if (!this.pageClass.isEmpty()) {
//					pageNum = pageNum + i;
//				} else {
//					pageNum = pageNum + i;
//				}
//			}
//
//
//			return pageNum;
//		}
//	}

}
