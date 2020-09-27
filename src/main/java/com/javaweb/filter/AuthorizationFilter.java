package com.javaweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.constant.SysConstant;
import com.javaweb.model.UserModel;
import com.javaweb.util.SessionUtil;

public class AuthorizationFilter implements Filter {

	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		String url = httpRequest.getRequestURI();
		if (url.startsWith(httpRequest.getContextPath()+"/admin")) {
			UserModel userLogin = (UserModel) SessionUtil.getInstance().getValue(httpRequest, SysConstant.USERLOGINMODEL);
			if(userLogin != null) {
				if(userLogin.getRole().getCode().equals(SysConstant.ADMIN)) {
					filterChain.doFilter(servletRequest, servletResponse);
				}else if(userLogin.getRole().getCode().equals(SysConstant.USER)) {
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/dang-nhap?message=not_permission&alert=danger");
				}
			}else {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/dang-nhap?message=not_login&alert=danger");
			}
		}else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
