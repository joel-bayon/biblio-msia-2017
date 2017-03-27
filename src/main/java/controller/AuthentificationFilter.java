package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthentificationFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chaineDesFiltres)
			throws IOException, ServletException {
	HttpServletRequest request = (HttpServletRequest)arg0;
	HttpServletResponse response = (HttpServletResponse)arg1;
	
	if(request.getSession().getAttribute("nom") == null) {
		response.sendRedirect(request.getContextPath()+"/authentification/connect.spring");
		return;
	}
	chaineDesFiltres.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
