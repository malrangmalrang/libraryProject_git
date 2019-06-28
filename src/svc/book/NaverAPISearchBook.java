package svc.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

import vo.book.NaverBookVO;

public class NaverAPISearchBook {

	private static NaverAPISearchBook instance;

	public static NaverAPISearchBook getInstance() {
		if (instance ==null) {
			instance = new NaverAPISearchBook();
		}

		return instance;
	}

	private NaverAPISearchBook() {

	}

	public String searchBook(String isbn) {
		
		StringBuffer response = null;
		NaverBookVO naverBookVO = null;
		
		if (isbn.equals("978899537949301")) { // 'JAVA의 정석-요약집'일 경우
			isbn = "9788995379493"; // 'JAVA의 정석' ISBN 사용
		}
		
		String clientId = "I4Xm8lCuYCiSooAP6e7S";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "PLc7Ath6co";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(isbn, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/book?query="+ text; // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/book.xml?query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            //System.out.println(response.toString());
            
            naverBookVO = new Gson().fromJson(response.toString(), NaverBookVO.class);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //return response.toString();
        return naverBookVO.items.get(0).description;
	}
	
	/*public static void main(String[] args) {
		NaverAPISearchBook apiSearchBook = NaverAPISearchBook.getInstance();
		NaverBookVO naverBookVO = new Gson().fromJson(apiSearchBook.searchBook("9788983797476"), NaverBookVO.class);
		System.out.println("naverBookVO.toString(): " + naverBookVO.toString());
		System.out.println(naverBookVO.items.get(0).description);
	}*/
	
}
