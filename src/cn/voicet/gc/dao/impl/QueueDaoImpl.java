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

import cn.voicet.gc.dao.QueueDao;
import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(QueueDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class QueueDaoImpl extends BaseDaoImpl implements QueueDao {

	private static Logger log = Logger.getLogger(QueueDaoImpl.class);
	public void queryQueueList(final DotSession ds, final QueueForm queueForm) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call web_task_list()}");
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

	public void saveQueue(final QueueForm queueForm) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				String queue_insert = "{call web_task_insert(?,?,?,?,?,?)}";
				String queue_update = "{call web_task_update(?,?,?,?)}";
				CallableStatement cs = null;
				if(queueForm.getTid()!=0)
				{
					cs = conn.prepareCall(queue_update);
					cs.setInt(1, queueForm.getTid());
					cs.setString(2, queueForm.getName());
					cs.setString(3, queueForm.getAni());
					cs.setString(4, queueForm.getOverflowto());
					cs.execute();
					return null;
				}
				else
				{
					cs = conn.prepareCall(queue_insert);
					cs.setString(1, queueForm.getName());
					cs.setString(2, queueForm.getAni());
					cs.setInt(3, queueForm.getKind());
					cs.setInt(4, queueForm.getMaxline());
					cs.setString(5, queueForm.getOverflowto());
					cs.registerOutParameter(6, Types.INTEGER);
					cs.execute();
					log.info("tid:"+cs.getInt(6));
					return cs.getInt(6);
				}
			}
		});
	}

	public void deleteQueueByTid(final QueueForm queueForm) {
		String procedureSql = "{call web_task_delete(?,?)}";
		this.getJdbcTemplate().execute(procedureSql, new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, queueForm.getTid());
				cs.setString(2, null);
				cs.execute();
				return null;
			}
		});
	}
}
