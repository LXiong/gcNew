package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.AcdDao;
import cn.voicet.gc.form.AcdForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(AcdDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class AcdDaoImpl extends BaseDaoImpl implements AcdDao {

	private static Logger log = Logger.getLogger(AcdDaoImpl.class);

	public void queryAcdList(final DotSession ds, final AcdForm acdForm) {
		log.info("sp:web_acd_list(?)");
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call web_acd_list(?)}");
				cs.setString(1, acdForm.getCts());
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
	
	public void queryAcdAnalyList(final DotSession ds) {
		log.info("sp:web_acd_analy(?,?,?)");
		this.getJdbcTemplate().execute("{call web_acd_analy(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
				cs.setString(2, ds.cursdt);
				cs.setString(3, ds.curedt);
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

	public void saveAcd(final DotSession ds, final AcdForm acdForm) {
		log.info("sp:web_acd_update(?,?,?,?,?,?,?,?,?)");
		this.getJdbcTemplate().execute("{call web_acd_update(?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
				cs.setString(2, acdForm.getAcdtxt()[0]);	//为空或不存在,添加; 存在,更新
				cs.setString(3, acdForm.getAcdtxt()[1]);
				cs.setString(4, acdForm.getAcdtxt()[2]);
				cs.setString(5, acdForm.getAcdtxt()[3]);
				cs.setString(6, acdForm.getAcdtxt()[4]);
				cs.setString(7, acdForm.getAcdtxt()[5]);
				cs.setString(8, acdForm.getAcdtxt()[6]);
				cs.setString(9, acdForm.getAcdtxt()[7]);
				cs.execute();
				return null;
			}
		});
	}
	
	public void deleteAcdByGrpid(final AcdForm acdForm) {
		log.info("sp:web_acd_remove(?,?)");
		this.getJdbcTemplate().execute("{call web_acd_remove(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, acdForm.getCts());
				cs.setInt(2, acdForm.getGrpid());
				cs.execute();
				return null;
			}
		});
	}

	public void setTaskForAcd(final AcdForm acdForm) {
		log.info("sp:web_acd_settask(?,?,?)");
		this.getJdbcTemplate().execute("{call web_acd_settask(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, acdForm.getCts());
				cs.setInt(2, acdForm.getGrpid());
				cs.setInt(3, acdForm.getTaskid());
				cs.execute();
				return null;
			}
		});
	}

	public void getAcdMonitorList(final DotSession ds) {
		log.info("sp:web_acd_monitor(?)");
		this.getJdbcTemplate().execute("{call web_acd_monitor(?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
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

	public void setCaller(final DotSession ds, final AcdForm acdForm) {
		log.info("sp:web_acd_setcaller(?,?,?)");
		this.getJdbcTemplate().execute("{call web_acd_setcaller(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
				cs.setInt(2, acdForm.getGrpid());
				cs.setString(3, acdForm.getAni());
				cs.execute();
				return null;
			}
		});
	}

	public void changeTrunkByGrpid(final DotSession ds, final AcdForm acdForm) {
		log.info("sp:web_acd_trkchg(?,?,?)");
		this.getJdbcTemplate().execute("{call web_acd_trkchg(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
				cs.setInt(2, acdForm.getGrpid());
				cs.setInt(3, acdForm.getTrknum());
				cs.execute();
				return null;
			}
		});
	}

	public void changeCallState(final DotSession ds, final AcdForm acdForm) {
		this.getJdbcTemplate().execute("{call web_acd_setcall(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
				cs.setInt(2, acdForm.getGrpid());
				cs.setInt(3, acdForm.getCallstate());
				cs.execute();
				return null;
			}
		});
	}

}
