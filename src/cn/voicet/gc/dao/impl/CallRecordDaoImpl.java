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
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.stereotype.Repository;

import cn.voicet.gc.dao.CallRecordDao;
import cn.voicet.gc.web.form.AcdForm;
import cn.voicet.gc.web.form.CallRecordForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(CallRecordDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class CallRecordDaoImpl extends BaseDaoImpl implements CallRecordDao {

	private static Logger log = Logger.getLogger(CallRecordDaoImpl.class);

	public void queryCallRecordList(final DotSession ds, final CallRecordForm callRecordForm) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call web_session_talk_query(?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, "cts100");
				cs.setString(2, "2014-09-10");
				cs.setString(3, "2014-09-10");
				cs.setString(4, "8");
				cs.setString(5, "6");
				cs.setString(6, "1");
				cs.setString(7, ">0");
				cs.setString(8, ">0");
				cs.setString(9, "8");
				cs.setString(10, "3");
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
