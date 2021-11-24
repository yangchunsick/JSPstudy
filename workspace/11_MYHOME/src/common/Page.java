package common;

public class Page {
	
	private int totalRecord;			// DB에서 전체 게시글 갯수 (DB에서 가져옴)
	private int recordPerPage = 3;		// 한 페이지에 표시하는 게시글 갯수 (여기서 정함)
	private int totalPage;				// 전체 페이지 갯수 (totalRecord, recordPerPage로 계산)
	
	/****************************************************
	 	- 전체 10개 게시글
	 	- 한 페이지당 3개 게시글
	 		page = 1, beginRecord = 1, endRecord = 3
	 		page = 2, beginRecord = 4, endRecord = 6
	 		page = 3, beginRecord = 7, endRecord = 9
	 		page = 4, beginRecord = 10, endRecord = 11
	 ******************************************************/

	private int page;					// 현재 페이지 번호 (파라미터로 받아 옴)
	private int beginRecord;			// 각 페이지에 표시되는 시작 게시글 번호 (page, recordPerPage로 계산)
	private int endRecord;				// 각 페이지에 표시되는 종료 게시글 번호 (beginRecord, recordPerPage, totalRecord로 계산)
	
	/**********************************************************************
		- 전체 12 페이지
		- 한 블록당 5개 페이지
		1 block <1 2 3 4 5>,  page = 1 ~5, beginPage = 1, endPage = 5
		2 block <6 7 8 9 10>, page = 6 ~10, beginPage = 6, endPage = 10
		3 block <  11 12  >,  page = 11 ~15, beginPage = 11, endPage = 12	
	 **********************************************************************/
	private int pagePerBlock = 5;		// 한 블록에 표시하는 페이지 갯수(여기서 정함)
	private int beginPage;				// 각 블록에 표시하는 시작 페이지 번호(page, pagePerBlock로 계산)
	private int endPage;				// 각 블록에 표시하는 종료 페이지 번호(beginPage, pagePerBlock, totalPage로 계산)
	
	
	
	
	
	
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage() { /* 수정  */
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock +1;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage() { /* 수정 */
		endPage = beginPage + pagePerBlock - 1;
		if(totalPage < endPage) {
			endPage = totalPage;
		}
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getRecordPerpage() {
		return recordPerPage;
	}
	public void setRecordPerpage(int recordPerpage) {
		this.recordPerPage = recordPerpage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage() { /* 수정 */
		totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) {
			totalPage++;;
		// totalPage는 totalRecord와 recordPerPage로 몫을 구한 값으로 나타낸다
		// 만약 나머지가 있을 경우에는 totalPage를 ++ 하나 더 만들어 주기로 했다.
		}
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getBeginRecord() {
		return beginRecord;
	}
	public void setBeginRecord() { /* 수정 */
		beginRecord = (page - 1) * recordPerPage + 1;
	}
	public int getEndRecord() {
		return endRecord;
	}
	public void setEndRecord() {  /* 수정  */
		endRecord = beginRecord + recordPerPage - 1;
		if(totalRecord < endRecord) {
			endRecord = totalRecord;	
		}
	}
	
}
