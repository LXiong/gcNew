package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.DatabaseConfigDao;
import cn.voicet.util.DotSession;

@SuppressWarnings({"deprecation","unchecked"})
@Repository(DatabaseConfigDao.SERVICE_NAME)
public class DatabaseConfigDaoImpl extends BaseDaoImpl implements
		DatabaseConfigDao {
	
	public void databaseInfo(final DotSession ds) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sys_database_query(?,?,?,?,?,?,?)}");
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
						map.put("name", rs.getString("name"));
						map.put("filename", rs.getString("filename"));
						map.put("sizex", rs.getInt("size"));
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
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sys_database_truncatelog(?,?)}");
				cs.setString(1, ds.rbm);
				cs.setString(2, ds.roleID);
				cs.execute();
				return null;
			}
		});
	}

	public void backupDatabase(final DotSession ds, final String defbackupfilename) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sys_database_backup(?,?,?)}");
				cs.setString(1, ds.rbm);
				cs.setString(2, ds.roleID);
				cs.setString(3, defbackupfilename);
				cs.execute();
				return null;
			}
		});
	}

	public void databaseIndexManage(final DotSession ds) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sys_database_index(?,?)}");
				cs.setString(1, ds.account);
				cs.setInt(2, Integer.valueOf(ds.map.get("dbindex").toString()));
				cs.execute();
				return null;
			}
		});
	}

}
