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
	
	private static Logger logger = Logger.getLogger(SysParamDaoImpl.class);

	/** 默认外呼主叫 */
	public String getAni(final String defcts) {
		logger.info("fn:sys_getparam ('"+defcts+"_golable','manualani')");
		return (String)this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = null;
				cs = conn.prepareCall("{?=call sys_getparam ('"+defcts+"_golable','manualani')}");
				cs.registerOutParameter(1, Types.VARCHAR);
				cs.execute();
				return cs.getString(1);
			}
		});
	}
	
	/** 外呼等待时长  */
	public int getMaxwait(final String defcts) {
		logger.info("fn:sys_getparam ('cts"+defcts+"_golable','maxwait')");
		return this.getJdbcTemplate().queryForInt("select dbo.sys_getparam ('"+defcts+"_golable','maxwait')");
	}

	public void saveParam(final DotSession ds, final String defcts, final String ani, final int maxwait) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = null;
				cs = conn.prepareCall("{call sys_setparam(?,?,?)}");
				logger.info("sp:sys_setparam(?,?,?)");
				cs.setString("segment", defcts+"_golable");
				cs.setString("key", "manualani");
				cs.setString("val", ani);
				cs.execute();
				//
				cs.clearParameters();		
				cs = conn.prepareCall("{call sys_setparam(?,?,?)}");
				logger.info("sp:sys_setparam(?,?,?)");
				cs.setString("segment", defcts+"_golable");
				cs.setString("key", "maxwait");
				cs.setInt("val", maxwait);
				cs.execute();
				//
				return null;
			}
		});
	}
	
}
