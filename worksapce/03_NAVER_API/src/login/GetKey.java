package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class GetKey {

	public static String getKey() {
		
		String clientId = "xk9mxq4QGMYdtLh81V1V";
		String clientSecret = "Raq6Gsqaq_";
		
		String code = "0"; // 키 발급 "0",  이미지 비교 "1"
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
		
		// 요청 헤더(request header) : 아이디, 시크릿
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id", clientId);  // map에 저장하는 put
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);	// <= entry 
				
		URL url = null;
		HttpURLConnection con = null;
		String captchaKey = null;
		
		
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
				
				JSONObject obj = new JSONObject(sb.toString());
				captchaKey = (String)obj.get("key");		
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
				System.out.println("네이버로 부터 반환 받은 에러 : " + sb.toString());
			}
		}catch(MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiURL, e);
		}catch(IOException e) {
			throw new RuntimeException("연결에 실패했거나, API 응답을 읽는데 실패 했습니다. : " + apiURL, e);
		} finally {
			con.disconnect();
		}
		return captchaKey;		
	}
	
}
