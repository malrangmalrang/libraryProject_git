package vo.book;

import java.sql.Date;

public class RentBean {

	private int no;
	private Date rent_date;
	private Date due_date;
	private Date return_date;
	private String status;
	private int member_no;
	private int book_no;
	
	public RentBean() {

	}
	
	public RentBean(int no, Date rent_date, Date due_date, Date return_date, String status, int member_no, int book_no) {
		this.no = no;
		this.rent_date = rent_date;
		this.due_date = due_date;
		this.return_date = return_date;
		this.status = status;
		this.member_no = member_no;
		this.book_no = book_no;
	}

	@Override
	public String toString() {
		return "RentBean [no=" + no + ", rent_date=" + rent_date + ", due_date=" + due_date + ", return_date="
				+ return_date + ", status=" + status + ", member_no=" + member_no + ", book_no=" + book_no + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Date getRent_date() {
		return rent_date;
	}

	public void setRent_date(Date rent_date) {
		this.rent_date = rent_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public int getBook_no() {
		return book_no;
	}

	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
}
