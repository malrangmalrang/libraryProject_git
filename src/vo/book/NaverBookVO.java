package vo.book;

import java.util.ArrayList;

public class NaverBookVO {

	public String lastBuildDate;
	public int total;
	public int start;
	public int display;
	
	public ArrayList<NaverBookDTO> items;

	@Override
	public String toString() {
		return "NaverBookVO [lastBuildDate=" + lastBuildDate + ", total=" + total + ", start=" + start + ", display="
				+ display + ", items=" + items + "]";
	}
}
