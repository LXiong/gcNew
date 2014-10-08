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
		String wav = request.getParameter("wavFile");
		log.info("wav:"+wav);
		URL url = new URL("http://192.168.1.201/Message/"+wav);
		log.info("url:"+url);
		String filename = wav.substring(wav.indexOf("talk")+1, wav.length());
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
}
