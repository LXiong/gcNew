package cn.voicet.gc.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.voicet.gc.dao.HuiFangDao;

public class HuiFang extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(HuiFang.class);
	private HuiFangDao huiFangDao;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/huifang.jsp").forward(request, response);
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}
	
	public void init() throws ServletException {
		ServletContext servletContext = this .getServletContext();   
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);   
        huiFangDao = (HuiFangDao)ctx.getBean("huiFangDao" );   
        log.info("huiFangDao:"+huiFangDao);
	}
	
}
