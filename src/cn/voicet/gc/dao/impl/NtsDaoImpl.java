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

import cn.voicet.gc.dao.NtsDao;
import cn.voicet.gc.web.form.AcdForm;
import cn.voicet.gc.web.form.NtsForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(NtsDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class NtsDaoImpl extends BaseDaoImpl implements NtsDao {

	private static Logger log = Logger.getLogger(NtsDaoImpl.class);

	public void queryNtsList(final DotSession ds, final NtsForm ntsForm) {
		
		this.getJdbcTemplate().execute("{call }", new CallableStatementCallback() {
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
	
	
	public void saveNts(final DotSession ds, final NtsForm ntsForm) {
		String sp_nts_update = "{call web_acd_update(?,?,?,?,?,?,?,?)}";
		this.getJdbcTemplate().execute(sp_nts_update, new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
				cs.execute();
				return null;
			}
		});
	}
	
	public void deleteNts(final NtsForm ntsForm) {
		String sp_nts_delete = "{call web_acd_remove(?,?)}";
		this.getJdbcTemplate().execute(sp_nts_delete, new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				return null;
			}
		});
	}

}
