package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.CallRecordDao;
import cn.voicet.gc.form.CallRecordForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(CallRecordDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class CallRecordDaoImpl extends BaseDaoImpl implements CallRecordDao {

	private static Logger log = Logger.getLogger(CallRecordDaoImpl.class);

	public void queryCallRecordList(final DotSession ds, final CallRecordForm callRecordForm) {
		log.info("sp:web_session_talk_query(?,?,?,?,?,?,?,?,?,?)");
		this.getJdbcTemplate().execute("{call web_session_talk_query(?,?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString("ctsname", ds.curCTS);
				cs.setString("sdt", ds.cursdt);
				cs.setString("edt", ds.curedt);
				cs.setString("ani", callRecordForm.getCalltxt()[0]);
				cs.setString("dnis", callRecordForm.getCalltxt()[1]);
				if(callRecordForm.getCalltxt()[2].equals("2"))	//2:不限
				{
					cs.setString("callio", null);
				}
				else
				{
					cs.setString("callio", callRecordForm.getCalltxt()[2]);
				}
				if(callRecordForm.getCalltxt()[3].equals(""))	//2:不限
				{
					cs.setString("wait", null);
				}
				else
				{
					cs.setString("wait", callRecordForm.getCalltxt()[3]);
				}
				if(callRecordForm.getCalltxt()[4].equals(""))	//2:不限
				{
					cs.setString("talk", null);
				}
				else
				{
					cs.setString("talk", callRecordForm.getCalltxt()[4]);
				}
				cs.setString("agent", callRecordForm.getCalltxt()[5]);
				cs.setInt("peeknum", 500);
				cs.execute();
				ResultSet rs = cs.getResultSet();
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
	
	public void queryAllCallRecordList(final DotSession ds, final CallRecordForm callRecordForm) {
		log.info("sp:web_session_talk_query(?,?,?,?,?,?,?,?,?,?)");
		this.getJdbcTemplate().execute("{call web_session_talk_query(?,?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString("ctsname", ds.curCTS);
				cs.setString("sdt", ds.cursdt);
				cs.setString("edt", ds.curedt);
				cs.setString("ani", callRecordForm.getCalltxt()[0]);
				cs.setString("dnis", callRecordForm.getCalltxt()[1]);
				if(callRecordForm.getCalltxt()[2].equals("2"))	//2:不限
				{
					cs.setString("callio", null);
				}
				else
				{
					cs.setString("callio", callRecordForm.getCalltxt()[2]);
				}
				if(callRecordForm.getCalltxt()[3].equals(""))	//2:不限
				{
					cs.setString("wait", null);
				}
				else
				{
					cs.setString("wait", callRecordForm.getCalltxt()[3]);
				}
				if(callRecordForm.getCalltxt()[4].equals(""))	//2:不限
				{
					cs.setString("talk", null);
				}
				else
				{
					cs.setString("talk", callRecordForm.getCalltxt()[4]);
				}
				cs.setString("agent", callRecordForm.getCalltxt()[5]);
				cs.setInt("peeknum", 0);	//0导出所有记录
				cs.execute();
				ResultSet rs = cs.getResultSet();
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
