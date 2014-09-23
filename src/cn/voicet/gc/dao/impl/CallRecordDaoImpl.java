package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.CallRecordDao;
import cn.voicet.gc.form.CallRecordForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(CallRecordDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class CallRecordDaoImpl extends BaseDaoImpl implements CallRecordDao {

	private static Logger log = Logger.getLogger(CallRecordDaoImpl.class);

	public void queryCallRecordList(final DotSession ds, final CallRecordForm callRecordForm) {
		log.info("sp:web_session_talk_query(?,?,?,?,?,?,?,?,?,?)");
		this.getJdbcTemplate().execute("{call web_session_talk_query(?,?,?,?,?,?,?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setString(1, ds.curCTS);
				cs.setString(2, callRecordForm.getCalltxt()[0]);
				cs.setString(3, callRecordForm.getCalltxt()[1]);
				cs.setString(4, callRecordForm.getCalltxt()[2]);
				cs.setString(5, callRecordForm.getCalltxt()[3]);
				cs.setString(6, callRecordForm.getCalltxt()[4]);
				cs.setString(7, callRecordForm.getCalltxt()[5]);
				cs.setString(8, callRecordForm.getCalltxt()[6]);
				cs.setString(9, callRecordForm.getCalltxt()[7]);
				cs.setInt(10, 100);
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
