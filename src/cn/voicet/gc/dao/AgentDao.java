package cn.voicet.gc.dao;

import javax.servlet.http.HttpServletResponse;

import cn.voicet.gc.form.AgentForm;
import cn.voicet.util.DotSession;

public interface AgentDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.AgentDaoImpl";
	void queryAgentList(DotSession ds);
	void queryAgentAnalyList(DotSession ds, AgentForm agentForm);
	String saveAgent(AgentForm agentForm);
	void deleteAgentByAccount(AgentForm agentForm);
	void initAgentpwdByAgtid(AgentForm agentForm);
	void exportAgentAnalyData(AgentForm agentForm, HttpServletResponse response);
	void queryAgentAnswerList(DotSession ds, AgentForm agentForm);
	void emptyAnswerByAgent(DotSession ds);
}
