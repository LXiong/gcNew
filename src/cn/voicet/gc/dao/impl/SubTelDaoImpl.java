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

import cn.voicet.gc.dao.SubTelDao;
import cn.voicet.gc.form.SubTelForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(SubTelDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class SubTelDaoImpl extends BaseDaoImpl implements SubTelDao {

	private static Logger log = Logger.getLogger(SubTelDaoImpl.class);

	public void querySubTelList(final DotSession ds) {
		log.info("sp:web_subtel_list(?)");
		this.getJdbcTemplate().execute("{call web_subtel_list(?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
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

	public void saveSubTel(final DotSession ds, final SubTelForm subTelForm) {
		log.info("sp:web_subtel_update(?,?,?,?,?,?,?)");
		this.getJdbcTemplate().execute("{call web_subtel_update(?,?,?,?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
				if(""==subTelForm.getSubteltxt()[0] || null==subTelForm.getSubteltxt()[0])
				{
					cs.setString(2, null);
				}
				else
				{
					cs.setString(2, subTelForm.getSubteltxt()[0]);	//为空或不存在,添加; 存在,更新
				}
				cs.setString(3, subTelForm.getSubteltxt()[1]);
				cs.setString(4, subTelForm.getSubteltxt()[2]);
				cs.setString(5, subTelForm.getSubteltxt()[3]);
				cs.setString(6, subTelForm.getSubteltxt()[4]);
				cs.setString(7, subTelForm.getSubteltxt()[5]);
				cs.execute();
				return null;
			}
		});
	}

	public void deleteSubTelByTelid(final DotSession ds, final SubTelForm subTelForm) {
		log.info("sp:web_subtel_remove(?,?)");
		this.getJdbcTemplate().execute("{call web_subtel_remove(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
				cs.setInt(2, subTelForm.getTelid());
				cs.execute();
				return null;
			}
		});
	}

	public void querySubTelMonitorList(final DotSession ds) {
		log.info("sp:web_subtel_monitor(?)");
		this.getJdbcTemplate().execute("{call web_subtel_monitor(?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
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
