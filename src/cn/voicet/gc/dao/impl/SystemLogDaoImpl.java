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

import cn.voicet.gc.dao.SystemLogDao;
import cn.voicet.gc.form.SystemLogForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(SystemLogDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class SystemLogDaoImpl extends BaseDaoImpl implements SystemLogDao {

	private static Logger log = Logger.getLogger(SystemLogDaoImpl.class);
	
	public int findLogTotalPage(final DotSession ds, final SystemLogForm systemLogForm) {
		log.info("sp:sys_log_query_page_t(?,?,?,?,?,?,?,?)");
		return (Integer)this.getJdbcTemplate().execute("{call sys_log_query_page_t(?,?,?,?,?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.rbm);
				cs.setString(2, ds.roleID);
				cs.setString(3, systemLogForm.getStartdate());
		        cs.setString(4, systemLogForm.getEnddate());
		        cs.setString(5, systemLogForm.getMsgtype());
		        cs.setString(6, systemLogForm.getSender());
		        cs.setInt(7, systemLogForm.getPageSize());
		        cs.registerOutParameter(8, Types.INTEGER);
				cs.execute();
				log.info("total:"+cs.getInt(8));
				return cs.getInt(8);
			}
		});
	}

	public void findLogInfoList(final DotSession ds, final SystemLogForm systemLogForm) {
		log.info("sp:sys_log_query_page(?,?,?,?,?,?,?,?)");
		this.getJdbcTemplate().execute("{call sys_log_query_page(?,?,?,?,?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
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
