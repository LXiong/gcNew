package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.DatabaseConfigDao;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@SuppressWarnings("unchecked")
@Repository(DatabaseConfigDao.SERVICE_NAME)
public class DatabaseConfigDaoImpl extends BaseDaoImpl implements DatabaseConfigDao {
	
	private static Logger log = Logger.getLogger(DatabaseConfigDaoImpl.class);
	
	public void databaseInfo(final DotSession ds) {
		log.info("sp:sys_database_query(?,?,?,?,?,?,?)");
		this.getJdbcTemplate().execute("{call sys_database_query(?,?,?,?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.rbm);
				cs.setString(2, ds.roleID);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.registerOutParameter(5, Types.VARCHAR);
				cs.registerOutParameter(6, Types.VARCHAR);
				cs.registerOutParameter(7, Types.VARCHAR);
				cs.execute();
				ds.list = new ArrayList();
				ResultSet rs = cs.getResultSet();
				if (rs != null) {
					while (rs.next()) {
						Map map = new HashMap();
						VTJime.putMapDataByColName(map, rs);
						ds.list.add(map);
					}
					String serverinfo = cs.getString(3);
					String databasename = cs.getString(4);
					String lastbackupdt = cs.getString(5);
					String lasttrunkdt = cs.getString(6);
					String defbackupfilename = cs.getString(7);
					ds.map = new HashMap();
					ds.map.put("serverinfo", serverinfo);
					ds.map.put("databasename", databasename);
					ds.map.put("lastbackupdt", lastbackupdt);
					ds.map.put("lasttrunkdt", lasttrunkdt);
					ds.map.put("defbackupfilename",defbackupfilename);
				}
				return null;
			}
		});
	}

	public void emptyDatabase(final DotSession ds) {
		log.info("sp:sys_database_truncatelog(?,?)");
		this.getJdbcTemplate().execute("{call sys_database_truncatelog(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.account);
				cs.setString(2, ds.roleID);
				cs.execute();
				return null;
			}
		});
	}

	public void backupDatabase(final DotSession ds, final String defbackupfilename) {
		log.info("sp:sys_database_backup(?,?,?)");
		this.getJdbcTemplate().execute("{call sys_database_backup(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.account);
				cs.setString(2, ds.roleID);
				cs.setString(3, defbackupfilename);
				cs.execute();
				return null;
			}
		});
	}

}
