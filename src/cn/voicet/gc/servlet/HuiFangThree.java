package cn.voicet.gc.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.voicet.gc.dao.HuiFangDao;
import cn.voicet.servlet.BaseServlet;

public class HuiFangThree extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(HuiFangThree.class);
	private HuiFangDao huiFangDao;
	
	/**
	 * 查询回访2信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map<String, Object> hf3Map = huiFangDao.getHuiFang3Info();
		log.info("hf3Map:"+hf3Map);
		request.setAttribute("hf3Map", hf3Map);
		return "f:/huifang3.jsp";
	}
	
	/**
	 * 保存回访1信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String saveHuiFangThree(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String[] hf = request.getParameterValues("huifangthreetxt");
		log.info("hf length:"+hf.length);
		for(int i=0; i<hf.length; i++)
		{
			log.info("hf:"+hf[i]);
		}
		huiFangDao.saveHuiFangThreeInfo(hf);
		return query(request, response);
	}
	
	public void init() throws ServletException {
		ServletContext servletContext = this .getServletContext();   
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);   
        huiFangDao = (HuiFangDao)ctx.getBean("huiFangDao" );   
        log.info("huiFangDao:"+huiFangDao);
	}
	
}
