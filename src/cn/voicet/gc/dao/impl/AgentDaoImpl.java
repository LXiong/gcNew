package cn.voicet.gc.dao.impl;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
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
	
	public void exportAgentAnalyData(final AgentForm agentForm, final HttpServletResponse response) {
		final String excelExportFile = "agt_analy_export.xls";
		final String outputFileName="agent_analy.xls";
		//
		this.getJdbcTemplate().execute("{call web_agent_analy(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, agentForm.getSdt());
				cs.setString(2, agentForm.getEdt());
				cs.execute();
				//
				ResultSet rs = cs.getResultSet();
				ResultSetMetaData rsm =rs.getMetaData();
				int columnCount = rsm.getColumnCount();
				//
				String filePath = ServletActionContext.getServletContext().getRealPath("excelTemplate")+"/"+excelExportFile;
				HSSFWorkbook wb=DotSession.fromRStoExcel(filePath, 1, true, rs, columnCount);
				try {
					response.reset();
					response.setHeader("Content-Disposition", "attachment;filename=" + outputFileName);
					response.setContentType("application/vnd.ms-excel;charset=UTF-8");	
					wb.write(response.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	
	public String saveAgent(final AgentForm agentForm) {
		String sp_agent_update = "{call web_agent_update(?,?,?,?,?,?,?)}";
		return (String)this.getJdbcTemplate().execute(sp_agent_update, new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, agentForm.getAgttxt()[0]);
				cs.setString(2, agentForm.getAgttxt()[1]);
				cs.setString(3, agentForm.getAgttxt()[2]);
				cs.setString(4, agentForm.getAgttxt()[3]);
				cs.setString(5, agentForm.getAgttxt()[4]);
				cs.setString(6, agentForm.getAgttxt()[5]);
				cs.registerOutParameter(7, Types.VARCHAR);
				cs.execute();
				log.info("ret:"+cs.getString(7));
				return cs.getString(7);
			}
		});
	}

	
	public void deleteAgentByAccount(final AgentForm agentForm) {
		String sp_agent_delete = "{call web_agent_delete(?)}";
		this.getJdbcTemplate().execute(sp_agent_delete, new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, agentForm.getAgtid());
				cs.execute();
				return null;
			}
		});
	}

	public void initAgentpwdByAgtid(final AgentForm agentForm) {
		this.getJdbcTemplate().execute("{call web_agent_initpwd(?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, agentForm.getAgtid());
				cs.execute();
				return null;
			}
		});
	}

	public void queryAgentAnserList(final DotSession ds, final AgentForm agentForm) {
		this.getJdbcTemplate().execute("{call web_agent_callquery(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, agentForm.getAgent());
				cs.setInt(2, agentForm.getCallio());
				cs.setString(3, agentForm.getTelnum());
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

}
