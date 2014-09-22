package cn.voicet.gc.web.action;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.AgentDao;
import cn.voicet.gc.web.form.AgentForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("agentAnalyAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class AgentAnalyAction extends BaseAction implements ModelDriven<AgentForm>{
	private static Logger log = Logger.getLogger(AgentAnalyAction.class);
	@Resource(name=AgentDao.SERVICE_NAME)
	private AgentDao agentDao;
	private AgentForm agentForm = new AgentForm();
	
	public AgentForm getModel() {
		return agentForm;
	}
	
	public String home()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("sdt:"+agentForm.getSdt()+", edt:"+agentForm.getEdt());
		agentDao.queryAgentAnalyList(ds, agentForm);
		return "show_agent_analy";
	}
	
	/**
	 * 导出数据
	 * @return
	 */
	public String export()
	{
		log.info("sdt:"+agentForm.getSdt()+", edt:"+agentForm.getEdt());
		agentDao.exportAgentAnalyData(agentForm, response);
		return null;
	}
	
	/**
	 * 接听情况
	 * @return
	 */
	public String answer()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("callio:"+agentForm.getCallio()+", telnum:"+agentForm.getTelnum());
		agentDao.queryAgentAnserList(ds, agentForm);
		return "show_agent_answer";
	}
	
	/**
	 * 详情
	 * @return
	 */
	public String detail()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("tid:"+agentForm.getTid()+", ttid:"+agentForm.getTtid());
		int iKind = agentDao.queryAgentAnserDetailList(ds, agentForm);
		log.info("iKind:"+iKind);
		String[] retStr = {"show_agent_answer_hf0","show_agent_answer_hf1","show_agent_answer_hf2","show_agent_answer_hf3"};
		if(iKind>0 && iKind<4)
		{
			log.info("retStr:"+retStr[iKind]);
			return retStr[iKind];
		}
		else
		{
			return ERROR;
		}
	}
	
	/**
	 * view save
	 */
	public String viewhf()
	{
		flag = (String)request.getParameter("flag");
		if(flag.equals("1"))
		{
			return "show_edit_huifang1";
		}
		else if(flag.equals("2"))
		{
			return "show_edit_huifang2";
		}
		else if(flag.equals("3"))
		{
			return "show_edit_huifang3";
		}
		else
		{
			return ERROR;
		}
	}
	
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
