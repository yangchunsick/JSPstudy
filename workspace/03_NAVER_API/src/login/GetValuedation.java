package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class GetValuedation {
	
	public static boolean getValuedation(HttpServletRequest request) {
		
		String clientId = "xk9mxq4QGMYdtLh81V1V";
		String clientSecret = "Raq6Gsqaq_";
		
		String code = "1"; // 키 발급 "0",  이미지 비교 "1"
		String key = request.getParameter("key");
		String value = request.getParameter("value"); // 그림 문자
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value=" + value;
		
		// 요청 헤더(request header) : 아이디, 시크릿
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id", clientId);  // map에 저장하는 put
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);	// <= entry 
				
		URL url = null;
		HttpURLConnection con = null;
		boolean result = false;
		
		
		try{
			url = new URL(apiURL);		//오픈 커넥션은 어쩌구를 반환한다...
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			for(Map.Entry<String, String> entry : requestHeaders.entrySet()) {
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
			//				↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 같은 코드임 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓
			//
			// con.setRequestProperty("X-Naver-Client-Id", clientId);
			// con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode(); // 응답코드를 받아오는 메소드 // 응답코드 : 200 == HTTP_OK
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStreamReader isr = new InputStreamReader(con.getInputStream());
				//		↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 같은 코드임 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓
				//
				// InputStream is = con.getInputStream();
				// InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				StringBuilder sb = new StringBuilder();
				while (true) {
					String line = br.readLine();
					if(line == null) {
						break;
					}
					sb.append(line);
				}
			
				JSONObject obj = new JSONObject(sb.toString()); // 라이브러리 추가
				result = (boolean)obj.get("result");		// 네이버로 부터 받은 ("key")키값을 입력
				
			}else {
				InputStreamReader isr = new InputStreamReader(con.getErrorStream());
				//		↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 같은 코드임 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓
				//
				// InputStream is = con.getInputStream();
				// InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				StringBuilder sb = new StringBuilder();
				while (true) {
					String line = br.readLine();
					if(line == null) {
						break;
					}
					sb.append(line);
				}

			}
		}catch(MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiURL, e);
		}catch(IOException e) {
			throw new RuntimeException("연결에 실패했거나, API 응답을 읽는데 실패 했습니다. : " + apiURL, e);
		} finally {
			con.disconnect();
		}
		
		return result;
	}

}
