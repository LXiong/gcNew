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
import cn.voicet.util.DotSession;
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

	public void saveHuiFangInfo(final DotSession ds, final int flag, final HuiFangForm huiFangForm) {
		String sp_huifangs[] = {
				"{call web_huifang_enter1(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
				"{call web_huifang_enter2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
				"{call web_huifang_enter3(?,?,?,?,?,?,?,?,?,?,?,?,?)}"};
		if(flag>0 && flag<4)
		{
			log.info("sp:"+sp_huifangs[flag-1]);
			this.getJdbcTemplate().execute(sp_huifangs[flag-1], new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
					cs.setInt("tid", huiFangForm.getTid());
					cs.setInt("ttid", huiFangForm.getTtid());
					if(flag==1)
					{
						cs.setInt("hfqk", huiFangForm.getHfqk());
						cs.setString("xm", huiFangForm.getHfname());
						cs.setInt("xb", huiFangForm.getSex());
						cs.setInt("ywbwdc", huiFangForm.getYwbwdc());
						cs.setInt("qdmyddc", huiFangForm.getManyid());
						cs.setString("sjppxh", huiFangForm.getPhonemodel());
						cs.setInt("jkfl", huiFangForm.getJkfl());
						cs.setString("gkd", huiFangForm.getGkd());
						cs.setString("bz", huiFangForm.getRemark());
						cs.setString("bz1", huiFangForm.getBz1());
						cs.setString("bz2", huiFangForm.getBz2());
						cs.setString("bz3", huiFangForm.getBz3());
					}
					else if(flag==2)
					{
						cs.setInt("jffs", huiFangForm.getJffs());
						cs.setInt("fwtd", huiFangForm.getManyid());
						cs.setString("jy", huiFangForm.getCustomjy());
						cs.setInt("whjj", huiFangForm.getHfqk());
						cs.setInt("qk", huiFangForm.getSuccfail());
						cs.setString("xm", huiFangForm.getHfname());
						cs.setString("zjh", huiFangForm.getHfzjh());
						cs.setString("dh2", huiFangForm.getHfstel());
						cs.setString("dz", huiFangForm.getAddress());
						cs.setString("pp", huiFangForm.getPhonemodel());
						cs.setString("email", huiFangForm.getEmail());
						cs.setString("bz", huiFangForm.getRemark());
						cs.setString("bz1", huiFangForm.getBz1());
						cs.setString("bz2", huiFangForm.getBz2());
						cs.setString("bz3", huiFangForm.getBz3());
					}
					else if(flag==3)
					{
						cs.setInt("cgf", huiFangForm.getYxsuccfail());
						cs.setString("tclr", huiFangForm.getTccon());
						cs.setString("qxtclr", huiFangForm.getQxtccon());
						cs.setString("sbyy", huiFangForm.getYxfaildecause());
						cs.setString("lxdz", huiFangForm.getAddress());
						cs.setInt("hjjj", huiFangForm.getSuccfail());
						cs.setString("bz", huiFangForm.getRemark());
						cs.setString("bz1", huiFangForm.getBz1());
						cs.setString("bz2", huiFangForm.getBz2());
						cs.setString("bz3", huiFangForm.getBz3());
					}
					cs.setString("agttel", ds.agttelnum);
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
