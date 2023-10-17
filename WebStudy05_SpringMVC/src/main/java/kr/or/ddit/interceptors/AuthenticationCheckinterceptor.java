package kr.or.ddit.interceptors;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationCheckinterceptor implements HandlerInterceptor{
	private String loginPage;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Principal principal = request.getUserPrincipal();
		boolean pass = principal!=null;
		if(!pass) {
			response.sendRedirect(request.getContextPath()+loginPage);
		}
		return pass;
	}
}
