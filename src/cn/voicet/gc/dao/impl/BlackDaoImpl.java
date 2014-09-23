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

import cn.voicet.gc.dao.BlackDao;
import cn.voicet.gc.form.BlackForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(BlackDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class BlackDaoImpl extends BaseDaoImpl implements BlackDao {

	private static Logger log = Logger.getLogger(BlackDaoImpl.class);
	public void queryBlackList(final DotSession ds, final BlackForm blackForm) {
		log.info("sp:web_black_query(?,?,?,?)");
		this.getJdbcTemplate().execute("{call web_black_query(?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, blackForm.getTelnum());
				cs.setInt(2, 500);
				cs.registerOutParameter(3, Types.INTEGER);
				cs.registerOutParameter(4, Types.INTEGER);
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
				log.info("@totalnum:"+cs.getInt(3));
				log.info("@querynum:"+cs.getInt(4));
				return null;
			}
		});
	}

	public void saveTelnum(final BlackForm blackForm) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				String black_insert = "{call web_black_insert(?,?,?)}";
				String black_update = "{call web_black_update(?,?,?)}";
				CallableStatement cs = null;
				if(blackForm.getBid()!=0)
				{
					cs = conn.prepareCall(black_update);
					cs.setInt(1, blackForm.getBid());
					cs.setString(2, blackForm.getTelnum());
					cs.setString(3, blackForm.getNoteinfo());
					cs.execute();
					return null;
				}
				else
				{
					cs = conn.prepareCall(black_insert);
					cs.setString(1, blackForm.getTelnum());
					cs.setString(2, blackForm.getNoteinfo());
					cs.registerOutParameter(3, Types.INTEGER);
					cs.execute();
					log.info("bid:"+cs.getInt(3));
					return cs.getInt(3);
				}
			}
		});
	}

	public void deleteTelnumByBid(final BlackForm blackForm) {
		log.info("sp:web_black_delete(?)");
		this.getJdbcTemplate().execute("{call web_black_delete(?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, blackForm.getBid());
				cs.execute();
				return null;
			}
		});
	}

	public void emptyBlack() {
		log.info("sp:web_black_empty()");
		this.getJdbcTemplate().execute("{call web_black_empty()}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.execute();
				return null;
			}
		});
	}
}
