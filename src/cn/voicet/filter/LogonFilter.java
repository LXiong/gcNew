package cn.voicet.filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.voicet.gc.dao.SysParamDao;
import cn.voicet.util.DotSession;

public class LogonFilter implements Filter{
	private static Logger log = Logger.getLogger(LogonFilter.class);
	private List<String> list = new ArrayList<String>();
	private SysParamDao sysParamDao;
	/**
	 *  
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		list.add("/index.action");
		list.add("/ajaxlogin.action");
		list.add("/error.jsp");
		//
		ServletContext servletContext = filterConfig.getServletContext();   
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);   
        sysParamDao = (SysParamDao)ctx.getBean("sysParamDao" );   
        log.info("sysParamDao:"+sysParamDao);
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String path = request.getServletPath();
		log.info("access path: " + path);
		//
		if (list!=null && list.contains(path)) {
			chain.doFilter(request, response);
			return;
		}
		DotSession ds = (DotSession)request.getSession().getAttribute("vts");
		
		if(path.indexOf("acdmonitor")<0)
		{
			if(null!=request.getSession().getAttribute("acdm"))
			{
				log.info("stop acdgrp monitor:"+ds.curClientLocal+", "+ds.curCTS+", 2235, -1, -1");
				sysParamDao.stopAcdMonitor(ds);
				request.getSession().setAttribute("acdm",null);
			}
			else
			{
				log.info("acdm is null");
			}
		}
		if(path.indexOf("subtelmonitor")<0)
		{
			if(null!=request.getSession().getAttribute("subtelm"))
			{
				log.info("stop subtel monitor:"+ds.curClientLocal+", "+ds.curCTS+", 2240, -1, -1");
				sysParamDao.stopSubtelMonitor(ds);
				request.getSession().setAttribute("subtelm",null);
			}
			else
			{
				log.info("subtelm is null");
			}
		}
		if (ds != null) {
			chain.doFilter(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/error.jsp");
	}

	public void destroy() {
		
	}
}
