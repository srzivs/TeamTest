/**
 * 
 */
package egovframework.example.cmmn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author user
 * 인터셉터 클래스 만들기
 * 그 클래스를 실행할 메뉴를 등록하는 일 -> 필요없음:회원가입, 로그인 등 필요함: 부서,사원 등
 */
public class AuthenticInterceptor extends HandlerInterceptorAdapter{
//우리 회원인지 판단해서 아니면 로그인 페이지로 강제이동
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션 가져오기
		HttpSession session = request.getSession(false);
		// memberVO 없으면 강제이동
		if(session == null || session.getAttribute("memberVO") == null) {
			response.sendRedirect("/login.do"); //로그인 강제이동
			return false;
		}
	//POST일때만. post, get인지 확인하는 메소드	
	if("POST".equals(request.getMethod())) {
//	    1) 세션에서 CSRF_TOKEN 을 가져오기
		String sessionToken = (String) session.getAttribute("CSRF_TOKEN");
//	    2) JSP에서 csrf 이름으로 쿼리스트링 정보 가져오기
		String requestToken = request.getParameter("csrf");
//	    3) 1,2를 같은 지 비교: 다르면 에러처리
		if (sessionToken == null || !sessionToken.equals(requestToken)) {
            throw new Exception("csrf 위반입니다.");
         }
		}
		
		return true;  //맞으면 해당 메뉴 사용가능
	}

	
}
