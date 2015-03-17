package cn.voicet.gc.action;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.CallRecordDao;
import cn.voicet.gc.form.CallRecordForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.SimpleFileUtils;
import cn.voicet.util.SimpleJxlUtils;

import com.opensymphony.xwork2.ModelDriven;

@Controller("callRecordAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class CallRecordAction extends BaseAction implements ModelDriven<CallRecordForm>{
	private static Logger log = Logger.getLogger(CallRecordAction.class);
	@Resource(name=CallRecordDao.SERVICE_NAME)
	private CallRecordDao callRecordDao;
	private CallRecordForm callRecordForm = new CallRecordForm();
	
	public CallRecordForm getModel() {
		return callRecordForm;
	}
	
	public String list()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("form sdt:"+callRecordForm.getSdt()+", edt:"+callRecordForm.getEdt());
		if(null!=callRecordForm.getSdt() || null!=callRecordForm.getEdt())
		{
			ds.cursdt = callRecordForm.getSdt();
			ds.curedt = callRecordForm.getEdt();
		}
		log.info("ds cursdt:"+ds.cursdt+", curedt:"+ds.curedt);
		callRecordDao.queryCallRecordList(ds, callRecordForm);
		return "show_call";
	}
	
	// 下载网络文件
	public void downloadNet() throws MalformedURLException {
		DotSession ds = DotSession.getVTSession(request);
		String wav = request.getParameter("wavFile");
		log.info("wav:"+wav);
		String ip= request.getParameter("ip");
		log.info("ip:"+ip);
		URL url = new URL(ip+"/"+wav);
		log.info("url:"+url);
		String filename = wav.substring(wav.indexOf("talk")+5, wav.length());
		log.info("filename:"+filename);
		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			// 设置输出的格式
			response.reset();
			response.setContentType("bin");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[1024];
			int len;
			while ((len = inStream.read(b)) > 0)
			response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportData()
	{
		String templateFile = request.getSession().getServletContext().getRealPath("excelTemplate")+"/"+"record_exportTemplate.xls";
		log.info("templateFile:"+templateFile);
		String destFile = request.getSession().getServletContext().getRealPath("download")+"/"+"record_exportTemplate.xls";
		log.info("destFile:"+destFile);
		//
		DotSession ds = DotSession.getVTSession(request);
		log.info("form sdt:"+callRecordForm.getSdt()+", edt:"+callRecordForm.getEdt());
		if(null!=callRecordForm.getSdt() || null!=callRecordForm.getEdt())
		{
			ds.cursdt = callRecordForm.getSdt();
			ds.curedt = callRecordForm.getEdt();
		}
		log.info("ds cursdt:"+ds.cursdt+", curedt:"+ds.curedt);
		callRecordDao.queryAllCallRecordList(ds, callRecordForm);
		//
		SimpleJxlUtils.writeExcelFile(ds.list, "reList", templateFile, destFile);
		//
		SimpleFileUtils.downloadExcelFile(destFile, response);
		ds.list=null;
	}
}
