package cn.voicet.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.voicet.util.DotSession;

public class LogonFilter implements Filter {
	private static Logger log = Logger.getLogger(LogonFilter.class);
	private List<String> list = new ArrayList<String>();
	/**
	 *  
	 */
	public void init(FilterConfig arg0) throws ServletException {
		list.add("/index.action");
		list.add("/ajaxlogin.action");
		list.add("/error.jsp");
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String path = request.getServletPath();
		log.info("access path: " + path);
		if (list!=null && list.contains(path)) {
			chain.doFilter(request, response);
			return;
		}
		DotSession ds = (DotSession)request.getSession().getAttribute("vts");
		if (ds != null) {
			chain.doFilter(request, response);
			return;
		}
		//response.sendRedirect(request.getContextPath() + "/");
		response.sendRedirect(request.getContextPath() + "/error.jsp");
	}

	public void destroy() {
		
	}
}
