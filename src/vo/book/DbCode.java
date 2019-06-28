package vo.book;

public class DbCode {

	// 대출기간 (2주, 14일)
	public static final int RENT_PERIOD = 14;
	
	/* book TB category */
	public static final String BOOK_CATEGORY_A = "프로그래밍";
	public static final String BOOK_CATEGORY_B = "네트워크";
	public static final String BOOK_CATEGORY_C = "서버";
	public static final String BOOK_CATEGORY_D = "웹&디자인";
	public static final String BOOK_CATEGORY_E = "오라클";
	public static final String BOOK_CATEGORY_F = "IT&자격증";
	public static final String BOOK_CATEGORY_G = "자기계발";
	public static final String BOOK_CATEGORY_H = "기타";
	
	/* member TB type */
	public static final String MEMBER_TYPE_WAIT = "미승인"; 
	public static final String MEMBER_TYPE_STUDENT = "교육생";
	public static final String MEMBER_TYPE_FINISH = "수료생";
	public static final String MEMBER_TYPE_FAILURE = "중도탈락";
	public static final String MEMBER_TYPE_TEACHER = "강사";
	public static final String MEMBER_TYPE_ADMIN = "관리자";

	/* book TB status */
	public static final String BOOK_STATUS_IN = "대출가능";
	public static final String BOOK_STATUS_REQ = "신청중";
	public static final String BOOK_STATUS_OUT = "대출중";
	public static final String BOOK_STATUS_LST = "분실/파손";

	/* rental TB status */
	public static final String RENTAL_STATUS_REQ = "신청"; // 2019-06-1 "신청중"에서 "신청"으로 코드 변경
	public static final String RENTAL_STATUS_OUT = "대출중";
	public static final String RENTAL_STATUS_RETURN = "반납";
	public static final String RENTAL_STATUS_OVERDUE = "연체";
	public static final String RENTAL_STATUS_LOST = "분실/파손";

	/* board TB type */
	public static final String BOARD_TYPE_QNA = "qna";
	public static final String BOARD_TYPE_NOTICE = "notice";

	/* review TB ispublic */
	public static final String REVIEW_PUBLIC_TRUE = "공개";
	public static final String REVIEW_PUBLIC_FALSE = "비공개";

	/* wish_book TB status */
	public static final String WISH_BOOK_STATUS_REQ = "신청중";
	public static final String WISH_BOOK_STATUS_CONFIRM = "승인";
	public static final String WISH_BOOK_STATUS_REJECT = "거절";
	
}
