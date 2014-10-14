package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.SysParamDao;
import cn.voicet.util.DotSession;

@Repository(SysParamDao.SERVICE_NAME)
public class SysParamDaoImpl extends BaseDaoImpl implements SysParamDao {
	
	private static Logger log = Logger.getLogger(SysParamDaoImpl.class);

	public String getAni() {
		return (String)this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = null;
				cs = conn.prepareCall("{?=call sys_getparam ('cts100_golable','ani')}");
				cs.registerOutParameter(1, Types.VARCHAR);
				cs.execute();
				return cs.getString(1);
			}
		});
	}

	public int getMaxwait() {
		return this.getJdbcTemplate().queryForInt("select dbo.sys_getparam ('cts100_golable','maxwait')");
	}

	public void saveParam(final DotSession ds, final String ani, final int maxwait) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = null;
				cs = conn.prepareCall("{call sys_setparam(?,?,?)}");
				log.info("sp:sys_setparam(?,?,?)");
				cs.setString(1, "cts100_golable");
				cs.setString(2, "ani");
				cs.setString(3, ani);
				cs.execute();
				//		
				cs.clearParameters();		
				cs = conn.prepareCall("{call sys_setparam(?,?,?)}");
				log.info("sp:sys_setparam(?,?,?)");
				cs.setString(1, "cts100_golable");
				cs.setString(2, "maxwait");
				cs.setInt(3, maxwait);
				cs.execute();
				//
				return null;
			}
		});
	}
	
}
