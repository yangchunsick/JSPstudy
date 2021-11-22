package naver.captcha;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Ex02_ApiCaptchaImage {

	public static void main(String[] args) {

		String clientId = "xk9mxq4QGMYdtLh81V1V";
		String clientSecret = "Raq6Gsqaq_";
		
		String key = "GOuHomczHdhSMgJi";
		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
		
		// 요청 헤더(request header) : 아이디, 시크릿
				Map<String, String> requestHeaders = new HashMap<String, String>();
				requestHeaders.put("X-Naver-Client-Id", clientId);  // map에 저장하는 put
				requestHeaders.put("X-Naver-Client-Secret", clientSecret);	// <= entry 
					
				URL url = null;
				HttpURLConnection con = null;
				
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
						
						BufferedInputStream bis = new BufferedInputStream(con.getInputStream()); // 이미지를 읽어 들이기 위해 버퍼인풋스트림을 사용한다.
						String filename = Long.valueOf((new Date()).getTime()).toString() + ".jpg";
						File file = new File(filename);
						BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
						byte[] b = new byte[1024];
						int readCount = 0;					
						while (true){
							readCount = bis.read(b);
							if(readCount == -1) {
								break;
							}
							bos.write(b, 0, readCount); 	// b 배열의 0번 인덱스 부터 readCount한 만큼 읽는다.
						}
						System.out.println(filename + "이미지 캡차가 생성되었습니다.");
						if(bos != null) {bos.close();}
						if(bos != null) {bos.close();}
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
		

	}

}
