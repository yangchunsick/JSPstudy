package service.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public interface NoticeService {												/* 스트림을 사용하기 때문에 Exception이 발생 */
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)throws Exception;
	
}
