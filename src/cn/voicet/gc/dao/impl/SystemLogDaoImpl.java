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

import cn.voicet.gc.dao.SystemLogDao;
import cn.voicet.gc.form.SystemLogForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(SystemLogDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class SystemLogDaoImpl extends BaseDaoImpl implements SystemLogDao {

	public int findLogTotalPage(final DotSession ds, final SystemLogForm systemLogForm) {
		int totalP = (Integer)this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sys_log_query_page_t(?,?,?,?,?,?,?,?)}");
				cs.setString(1, ds.rbm);
				cs.setString(2, ds.roleID);
				cs.setString(3, systemLogForm.getStartdate());
		        cs.setString(4, systemLogForm.getEnddate());
		        cs.setString(5, systemLogForm.getMsgtype());
		        cs.setString(6, systemLogForm.getSender());
		        cs.setInt(7, systemLogForm.getPageSize());
		        cs.registerOutParameter(8, Types.INTEGER);
				cs.execute();
				Integer count = cs.getInt(8);
				return count;
			}
		});
		return totalP;
	}

	public void findLogInfoList(final DotSession ds, final SystemLogForm systemLogForm) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call sys_log_query_page(?,?,?,?,?,?,?,?)}");
				cs.setString(1, ds.rbm);
				cs.setString(2, ds.roleID);
				cs.setString(3, systemLogForm.getStartdate());
		        cs.setString(4, systemLogForm.getEnddate());
		        cs.setString(5, systemLogForm.getMsgtype());
		        cs.setString(6, systemLogForm.getSender());
				cs.setInt(7, systemLogForm.getCurPage());
				cs.setInt(8, systemLogForm.getPageSize());
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
