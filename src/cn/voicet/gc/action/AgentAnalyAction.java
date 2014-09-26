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
		log.info("form sdt:"+agentForm.getSdt()+", edt:"+agentForm.getEdt());
		if(null!=agentForm.getSdt() || null!=agentForm.getEdt())
		{
			ds.cursdt = agentForm.getSdt();
			ds.curedt = agentForm.getEdt();
		}
		log.info("ds cursdt:"+ds.cursdt+", curedt:"+ds.curedt);
		agentDao.queryAgentAnalyList(ds);
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
		log.info("agttelnum:"+ds.agttelnum+", callio:"+agentForm.getCallio()+", sdt:"+agentForm.getSdt()+", end:"+agentForm.getEdt()+", telnum:"+agentForm.getTelnum());
		if(null!=agentForm.getSdt() || null!=agentForm.getEdt())
		{
			ds.cursdt = agentForm.getSdt();
			ds.curedt = agentForm.getEdt();
		}
		log.info("ds cursdt:"+ds.cursdt+", curedt:"+ds.curedt);
		agentDao.queryAgentAnswerList(ds, agentForm);
		return "show_agent_answer";
	}
	
	public String emptyAnswer()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("agent telnum:"+ds.agttelnum);
		agentDao.emptyAnswerByAgent(ds);
		return null;
	}
	
}
