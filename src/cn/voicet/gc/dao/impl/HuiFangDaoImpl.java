package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.HuiFangDao;
import cn.voicet.gc.form.HuiFangForm;
@Repository(HuiFangDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class HuiFangDaoImpl extends BaseDaoImpl implements HuiFangDao {

	private static Logger log = Logger.getLogger(HuiFangDaoImpl.class);

	public Map<String, Object> getHuiFangInfo(final int flag, final HuiFangForm huiFangForm) {
		String sp_huifangs[] = {"{call web_huifang_org1(?,?)}","{call web_huifang_org2(?,?)}","{call web_huifang_org3(?,?)}"};
		if(flag>0 && flag<4)
		{
			log.info("sp:"+sp_huifangs[flag-1]);
			return (Map<String, Object>)this.getJdbcTemplate().execute(sp_huifangs[flag-1], new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
					cs.setInt(1, huiFangForm.getTid());
					cs.setInt(2, huiFangForm.getTtid());
					cs.execute();
					ResultSet rs = cs.getResultSet();
					Map<String, Object> map = null;
					if(rs!=null){
						while (rs.next()) {
							map = new HashMap<String, Object>();
							ResultSetMetaData rsm =rs.getMetaData();
							int colCount = rsm.getColumnCount();
							String colName;
							for(int i=1; i<=colCount; i++)
							{
								colName=rsm.getColumnName(i).toLowerCase();
								map.put(colName, rs.getString(i));
							}
						}
					}
					return map;
				}
			});
		}
		else
		{
			return null;
		}
	}

	public void saveHuiFangInfo(final int flag, final HuiFangForm huiFangForm) {
		String sp_huifangs[] = {
				"{call web_huifang_enter1(?,?,?,?,?,?,?,?,?,?,?)}",
				"{call web_huifang_enter2(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
				"{call web_huifang_enter3(?,?,?,?,?,?,?,?,?)}"};
		if(flag>0 && flag<4)
		{
			log.info("sp:"+sp_huifangs[flag-1]);
			this.getJdbcTemplate().execute(sp_huifangs[flag-1], new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
					cs.setInt(1, huiFangForm.getTid());
					cs.setInt(2, huiFangForm.getTtid());
					if(flag==1)
					{
						cs.setInt(3, huiFangForm.getHfqk());
						cs.setString(4, huiFangForm.getHfname());
						cs.setInt(5, huiFangForm.getSex());
						cs.setInt(6, huiFangForm.getYwbwdc());
						cs.setInt(7, huiFangForm.getManyid());
						cs.setString(8, huiFangForm.getPhonemodel());
						cs.setInt(9, huiFangForm.getJkfl());
						cs.setString(10, huiFangForm.getGkd());
						cs.setString(11, huiFangForm.getRemark());
					}
					else if(flag==2)
					{
						cs.setInt(3, huiFangForm.getJffs());
						cs.setInt(4, huiFangForm.getManyid());
						cs.setString(5, huiFangForm.getCustomjy());
						cs.setInt(6, huiFangForm.getSuccfail());
						cs.setInt(7, huiFangForm.getHfqk());
						cs.setString(8, huiFangForm.getHfname());
						cs.setString(9, huiFangForm.getHfzjh());
						cs.setString(10, huiFangForm.getHfstel());
						cs.setString(11, huiFangForm.getAddress());
						cs.setString(12, huiFangForm.getPhonemodel());
						cs.setString(13, huiFangForm.getEmail());
						cs.setString(14, huiFangForm.getRemark());
					}
					else if(flag==3)
					{
						cs.setInt(3, huiFangForm.getYxsuccfail());
						cs.setString(4, huiFangForm.getTccon());
						cs.setString(5, huiFangForm.getQxtccon());
						cs.setString(6, huiFangForm.getYxfaildecause());
						cs.setString(7, huiFangForm.getAddress());
						cs.setInt(8, huiFangForm.getSuccfail());
						cs.setString(9, huiFangForm.getRemark());
					}
					cs.execute();
					return null;
				}
			});
		}
	}
	
	public int getHuiFangType(final HuiFangForm huiFangForm) {
		log.info("sp:web_task_querykind(?,?)");
		return (Integer)this.getJdbcTemplate().execute("{call web_task_querykind(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, huiFangForm.getTid());
				cs.registerOutParameter(2, Types.INTEGER);
				cs.execute();
				return cs.getObject(2);
			}
		});
	}
	
	

	public Map<String, Object> queryAgentAnserDetailList(final HuiFangForm huiFangForm) {
		log.info("sp:web_tasktel_info(?,?,?)");
		return (Map<String, Object>)this.getJdbcTemplate().execute("{call web_tasktel_info(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, huiFangForm.getTid());
				cs.setInt(2, huiFangForm.getTtid());
				cs.registerOutParameter(3, Types.INTEGER);
				cs.execute();
				ResultSet rs = cs.getResultSet();
				Map<String, Object> map = new HashMap<String, Object>();
				if(rs!=null){
					while (rs.next()) {
						ResultSetMetaData rsm =rs.getMetaData();
						int colCount = rsm.getColumnCount();
						String colName;
						for(int i=1; i<=colCount; i++)
						{
							colName=rsm.getColumnName(i).toLowerCase();
							map.put(colName, rs.getString(i));
						}
					}
				}
				log.info("out param iKind:"+cs.getInt(3));
				map.put("iKind", cs.getInt(3));
				return map;
			}
		});
	}

}
