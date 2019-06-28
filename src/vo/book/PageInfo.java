package vo.book;

public class PageInfo {
	// 페이지 관련 정보를 저장하는 클래스
	private int nowPage;
	private int startPage;
	private int endPage;
	private int MaxPage;
	private int listCount;
	
	@Override
	public String toString() {
		return "PageInfo [nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", MaxPage=" + MaxPage
				+ ", listCount=" + listCount + "]";
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getMaxPage() {
		return MaxPage;
	}

	public void setMaxPage(int maxPage) {
		MaxPage = maxPage;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
}
