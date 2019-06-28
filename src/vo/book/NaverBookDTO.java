package vo.book;

public class NaverBookDTO {

	public String title;
	public String link;
	public String image;
	public String author;
	public String price;
	public String discount;
	public String publisher;
	public String pubdate;
	public String isbn;
	public String description;
	
	@Override
	public String toString() {
		return "NaverBookDTO [title=" + title + ", link=" + link + ", image=" + image + ", author=" + author
				+ ", price=" + price + ", discount=" + discount + ", publisher=" + publisher + ", pubdate=" + pubdate
				+ ", isbn=" + isbn + ", description=" + description + "]";
	}
}
