package cn.voicet.gc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;

import cn.voicet.gc.dao.HuiFangDao;
import cn.voicet.util.VTJime;

public class HuiFangDaoImpl extends BaseDaoImpl implements HuiFangDao {

	private static Logger log = Logger.getLogger(HuiFangDaoImpl.class);
	public Map<String, Object> getHuiFang1Info() {
		Map map = (Map)this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call web_huifang_org1(?,?)}");
				cs.setInt(1, 1);
				cs.setInt(2, 1);
				cs.execute();
				ResultSet rs = cs.getResultSet();
				Map<String, Object> map = null;
				if(rs!=null){
					while (rs.next()) {
						map = new HashMap<String, Object>();
						ResultSetMetaData rsm =rs.getMetaData();
						int colCount = rsm.getColumnCount();
						String colName;
						for(int i=1; i<=colCount; i++)
						{
							colName=rsm.getColumnName(i).toLowerCase();
							map.put(colName, rs.getString(i));
						}
					}
				}
				return map;
			}
		});
		return map;
	}
	
	public void saveHuiFangOneInfo(final String[] hf) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call web_huifang_enter1(?,?,?,?,?,?,?,?,?,?,?)}");
				String ix[][] = 
					{
						{"0","i"},
						{"1","i"},
						
						{"2","i"},
						{"3","s"},
						{"4","i"},
						{"5","i"},
						{"6","i"},
						{"7","s"},
						{"8","i"},
						{"9","s"},
						{"10","s"},
					};
				VTJime.prepareParamFromInputArray(cs, hf, ix);
				cs.execute();
				return null;
			}
		});
	}

}
