package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.AgentDao;
import cn.voicet.gc.web.form.AgentForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(AgentDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class AgentDaoImpl extends BaseDaoImpl implements AgentDao {

	private static Logger log = Logger.getLogger(AgentDaoImpl.class);

	public void queryAgentList(final DotSession ds) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call web_agent_list()}");
				cs.execute();
				ResultSet rs = cs.getResultSet();
				ds.initData();
				ds.list = new ArrayList();
				if(rs!=null){
					while (rs.next()) {
						 Map map = new HashMap();
						 VTJime.putMapDataByColName(map, rs);
		        		 ds.list.add(map);
					}
				}
				return null;
			}
		});
	}
	
	public void queryAgentAnalyList(final DotSession ds, final AgentForm agentForm) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call web_agent_analy(?,?)}");
				cs.setString(1, agentForm.getSdt());
				cs.setString(2, agentForm.getEdt());
				cs.execute();
				ResultSet rs = cs.getResultSet();
				ds.initData();
				ds.list = new ArrayList();
				if(rs!=null){
					while (rs.next()) {
						 Map map = new HashMap();
						 VTJime.putMapDataByColName(map, rs);
		        		 ds.list.add(map);
					}
				}
				return null;
			}
		});
	}

	
	public void saveAgent(final AgentForm agentForm) {
		String[] sp_agents = {"{call web_agent_add(?,?,?,?,?)}","{call web_agent_update(?,?,?,?,?)}"};
		String n = agentForm.getAgttxt()[0];
		this.getJdbcTemplate().execute(sp_agents[Integer.parseInt(n)], new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, agentForm.getAgttxt()[1]);
				cs.setString(2, agentForm.getAgttxt()[2]);
				cs.setString(3, agentForm.getAgttxt()[3]);
				cs.setString(4, agentForm.getAgttxt()[4]);
				cs.setString(5, null);
				cs.execute();
				return null;
			}
		});
	}

	
	public void deleteAgentByAccount(final AgentForm agentForm) {
		String sp_agent_delete = "{call web_agent_delete(?)}";
		this.getJdbcTemplate().execute(sp_agent_delete, new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, agentForm.getAccount());
				cs.execute();
				return null;
			}
		});
	}

}