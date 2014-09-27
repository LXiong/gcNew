package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.SysParamDao;
import cn.voicet.util.DotSession;

@Repository(SysParamDao.SERVICE_NAME)
public class SysParamDaoImpl extends BaseDaoImpl implements SysParamDao {
	
	private static Logger log = Logger.getLogger(SysParamDaoImpl.class);

	public int getAni() {
		return this.getJdbcTemplate().queryForInt("select dbo.sys_getparam ('cts100_golable','ani')");
	}

	public int getMaxwait() {
		return this.getJdbcTemplate().queryForInt("select dbo.sys_getparam ('cts100_golable','maxwait')");
	}

	public void saveParam(final DotSession ds, final int ani, final int maxwait) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = null;
				cs = conn.prepareCall("{call sys_setparam(?,?,?)}");
				log.info("sp:sys_setparam(?,?,?)");
				cs.setString(1, "cts100_golable");
				cs.setString(2, "ani");
				cs.setInt(3, ani);
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
				cs.clearParameters();
				cs = conn.prepareCall("{call web_cts_alter_chggolbal(?)}");
				log.info("sp:web_cts_alter_chggolbal(?)");
				cs.setString(1, ds.curCTS);
				cs.execute();
				//
				return null;
			}
		});
	}
	
}
