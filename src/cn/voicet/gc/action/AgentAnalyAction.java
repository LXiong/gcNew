package cn.voicet.gc.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.AgentDao;
import cn.voicet.gc.form.AgentForm;
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
	
	public String list()
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
		agentDao.queryAgentAnswerList(ds, agentForm);
		return "show_agent_answer";
	}
	
}
