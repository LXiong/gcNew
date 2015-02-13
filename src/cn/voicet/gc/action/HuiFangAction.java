package cn.voicet.gc.action;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.HuiFangDao;
import cn.voicet.gc.form.HuiFangForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("huiFangAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class HuiFangAction extends BaseAction implements ModelDriven<HuiFangForm>{
	private static Logger log = Logger.getLogger(HuiFangAction.class);
	@Resource(name=HuiFangDao.SERVICE_NAME)
	private HuiFangDao huiFangDao;
	private HuiFangForm huiFangForm = new HuiFangForm();
	
	public HuiFangForm getModel() {
		return huiFangForm;
	}
	
	public String list()
	{
		flag = (String)request.getParameter("flag");
		log.info("flag:"+flag+", tid:"+huiFangForm.getTid()+", ttid:"+huiFangForm.getTtid());
		Map<String, Object> hfMap = huiFangDao.getHuiFangInfo(Integer.parseInt(flag), huiFangForm);
		log.info("hfMap:"+hfMap);
		request.setAttribute("hfMap", hfMap);
		if(flag.equals("1"))
		{
			return "show_huifang1";
		}
		else if(flag.equals("2"))
		{
			return "show_huifang2";
		}
		else if(flag.equals("3"))
		{
			return "show_huifang3";
		}
		else
		{
			return ERROR;
		}
	}
	
	public String save()
	{
		rflag = rflag + 1;
		DotSession ds = DotSession.getVTSession(request);
		log.info("agttel:"+ds.agttelnum);
		huiFangDao.saveHuiFangInfo(ds, Integer.parseInt(flag), huiFangForm);
		return list();
	}
	
	public String agentAnswer()
	{
		log.info("tid:"+huiFangForm.getTid()+", ttid:"+huiFangForm.getTtid());
		Map<String, Object> hfMap = huiFangDao.queryAgentAnserDetailList(huiFangForm);
		log.info("hfMap:"+hfMap);
		request.setAttribute("hfMap", hfMap);
		flag = String.valueOf(hfMap.get("iKind"));
		if(flag.equals("1"))
		{
			return "show_hfedit1";
		}
		else if(flag.equals("2"))
		{
			return "show_hfedit2";
		}
		else if(flag.equals("3"))
		{
			return "show_hfedit3";
		}
		else
		{
			return ERROR;
		}
	}
	

	public String agentSave()
	{
		rflag = rflag + 1;
		DotSession ds = DotSession.getVTSession(request);
		log.info("agttel:"+ds.agttelnum);
		huiFangDao.saveHuiFangInfo(ds, Integer.parseInt(flag), huiFangForm);
		return agentAnswer();
	}
	
	public String viewtel()
	{
		log.info("tid:"+huiFangForm.getTid()+", ttid:"+huiFangForm.getTtid());
		Map<String, Object> hfMap = huiFangDao.queryAgentAnserDetailList(huiFangForm);
		log.info("hfMap:"+hfMap);
		request.setAttribute("hfMap", hfMap);
		flag = String.valueOf(hfMap.get("iKind"));
		if(flag.equals("1"))
		{
			return "show_hfview1";
		}
		else if(flag.equals("2"))
		{
			return "show_hfview2";
		}
		else if(flag.equals("3"))
		{
			return "show_hfview3";
		}
		else
		{
			return ERROR;
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String getHuiFangType() throws IOException
	{
		log.info("tid:"+huiFangForm.getTid());
		int type = huiFangDao.getHuiFangType(huiFangForm);
		log.info("type:"+type);
		response.getWriter().write(String.valueOf(type));
		return null;
	}
	

	private String flag;
	private int rflag=1;	//return flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getRflag() {
		return rflag;
	}
	public void setRflag(int rflag) {
		this.rflag = rflag;
	}
}
