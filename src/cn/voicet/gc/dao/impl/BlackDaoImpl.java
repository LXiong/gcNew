package cn.voicet.gc.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
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
	
	public void exportData(final BlackForm blackForm, final HttpServletResponse response) {
		log.info("sp:web_black_query(?,?,?,?)");
		this.getJdbcTemplate().execute("{call web_black_query(?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				//set param
				cs.setString(1, blackForm.getTelnum());
				cs.setInt(2, 0);	//0：表示查询全部记录，用于导出
				cs.registerOutParameter(3, Types.INTEGER);
				cs.registerOutParameter(4, Types.INTEGER);
				cs.execute();
				ResultSet rs = cs.getResultSet();
				ResultSetMetaData rsm =rs.getMetaData();
				int columnCount = rsm.getColumnCount();
				//
				String filePath = ServletActionContext.getServletContext().getRealPath("excelTemplate")+"/"+"black_export.xls";
				HSSFWorkbook wb=DotSession.fromRStoExcel(filePath, 1, true, rs, columnCount);
				try {
					response.reset();
					response.setHeader("Content-Disposition", "attachment;filename=" + "black_export.xls");
					response.setContentType("application/vnd.ms-excel;charset=UTF-8");	
					wb.write(response.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}

	public void batchImportData(final File uploadExcel) {
		log.info("sp:web_black_insert(?,?,?)");
		final int MAX_COL_CHECK = 200;
		final int COL_ACTUAL_NUM = 2;
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				PreparedStatement ps = null;
				//close session auto commit
				conn.setAutoCommit(false);
				ps = conn.prepareStatement("{call web_black_insert(?,?,?)}");
				boolean bCheckOK;
				String cellValues[]=new String[MAX_COL_CHECK];
				//
				try 
				{
					InputStream inStream=new FileInputStream(uploadExcel);
					Workbook wb = VTJime.create(inStream);
					Sheet sheet = wb.getSheetAt(0);
					int totalRowNum = sheet.getPhysicalNumberOfRows();
					//curRow
					for(int i=1; i<=totalRowNum;i++)
					{
						Row row = sheet.getRow(i);
						Cell cell;
						bCheckOK=true;
						//curCol
						for(int j=0;j<COL_ACTUAL_NUM;j++)
						{
							if(null!=row)
							{
								cell = row.getCell(j);
								if(null!=cell)
								{
									cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									cellValues[j] = row.getCell(j).getStringCellValue();
								}
								else
								{
									cellValues[j]="";
								}
								if(!checkCellOK(i, j, cellValues[j]))
								{
									bCheckOK = false;
								}
							}
							else
							{
								log.info("第["+(i+1)+"]行 is null");
							}
						}// end col
						if(bCheckOK)
						{
							//exec procedure
							//set task number
							for(int j=0; j<COL_ACTUAL_NUM; j++)
							{
								ps.setString(j+1, cellValues[j]);
							}
							ps.setString(3, null);
							ps.addBatch();
							//
							if(i % 1000==0){
				        		//执行批量更新    
				        		ps.executeBatch();
				        		//语句执行完毕，提交本事务 
				        		conn.commit();
				        		ps.clearBatch();
				        	}
						}
					}// end row
					ps.executeBatch();
					conn.commit();
					ps.clearBatch();
					ps.close();
					conn.setAutoCommit(true);
					return null;
				} 
				catch (Exception e) 
				{
					log.error(e);
					return null;
				}
			}
		});
	}
	

	private boolean checkCellOK(int curRow, int curCol, String cellValue) {
		//
		switch (curCol) {
			case 0:
				//telnum
				if(null==cellValue || cellValue.length()<=0)
				{
					log.info("telnum is null:"+cellValue);
					return false;
				}
				break;
			default:
				break;
		}
		return true;
	}

}
