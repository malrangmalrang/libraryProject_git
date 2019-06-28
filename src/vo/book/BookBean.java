package vo.book;

import java.sql.Date;
import java.sql.Timestamp;

public class BookBean {

	private int no;
	private String title;
	private String author;
	private String publisher;
	private Date publish_date;
	private int price;
	private String isbn;
	private String image;
	private String status;
	private String keyword1;
	private String keyword2;
	private String keyword3;
	private String category;
	private String rent_code;
	private String bar_code;
	private Timestamp reg_date;
	
	private float averageGrade;
	private int rentCount;
	private int favorCount;
	private int bestRentCount;
	
	@Override
	public String toString() {
		return "BookBean [no=" + no + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", publish_date=" + publish_date + ", price=" + price + ", isbn=" + isbn + ", image=" + image
				+ ", status=" + status + ", keyword1=" + keyword1 + ", keyword2=" + keyword2 + ", keyword3=" + keyword3
				+ ", category=" + category + ", rent_code=" + rent_code + ", bar_code=" + bar_code + ", reg_date="
				+ reg_date + ", averageGrade=" + averageGrade + ", rentCount=" + rentCount + ", favorCount="
				+ favorCount + ", bestRentCount=" + bestRentCount + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public String getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRent_code() {
		return rent_code;
	}

	public void setRent_code(String rent_code) {
		this.rent_code = rent_code;
	}

	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public float getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(float averageGrade) {
		this.averageGrade = averageGrade;
	}

	public int getRentCount() {
		return rentCount;
	}

	public void setRentCount(int rentCount) {
		this.rentCount = rentCount;
	}

	public int getFavorCount() {
		return favorCount;
	}

	public void setFavorCount(int favorCount) {
		this.favorCount = favorCount;
	}

	public int getBestRentCount() {
		return bestRentCount;
	}

	public void setBestRentCount(int bestRentCount) {
		this.bestRentCount = bestRentCount;
	}
}
