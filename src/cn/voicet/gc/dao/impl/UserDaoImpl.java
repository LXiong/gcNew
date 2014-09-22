package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.UserDao;
import cn.voicet.gc.form.UserForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(UserDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	public Map<String, Object> userLogin(final UserForm userForm) {
		return  (Map<String, Object>) this.getJdbcTemplate().execute(
			new ConnectionCallback() {
				public Object doInConnection(Connection conn)
						throws SQLException, DataAccessException {
					CallableStatement cs = conn.prepareCall("{call web_userlogin(?,?)}");
					cs.setString(1, userForm.getAccount());
					cs.setString(2, userForm.getPassword());
					cs.execute();
					ResultSet rs = cs.getResultSet();
					Map<String, Object> map = null;
					if (rs != null) {
						while (rs.next()) {
							map = new HashMap<String, Object>();
							VTJime.putMapDataByColName(map, rs);
						}
					}
					return map;
				}
			});
	}
	

	public void queryCTSList(final DotSession ds) {
		this.getJdbcTemplate().execute("{call web_cts_list()}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				ResultSet rs = cs.getResultSet();
				ds.initData();
				ds.ctsList = new ArrayList();
				if(rs!=null){
					while (rs.next()) {
						 Map map = new HashMap();
						 VTJime.putMapDataByColName(map, rs);
		        		 ds.ctsList.add(map);
					}
				}
				return null;
			}
		});
	}

	public boolean updateUserPassword(final DotSession ds, final UserForm userForm) {
		String sp = "{call web_userchgpwd(?,?,?,?)}";
		return (Boolean)this.getJdbcTemplate().execute(sp, new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.account);
				cs.setString(2, userForm.getOldpwd());
				cs.setString(3, userForm.getNewpwd());
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.execute();
				log.info("ret:"+cs.getString(4));
				if(cs.getString(4).equals("ok"))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		});
	}

}
