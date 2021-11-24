package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StudentService {	
	
	public void execute(HttpServletRequest request,HttpServletResponse response)throws IOException;
	// IOException는 PrintWriter 예외 처리를 위함
}
