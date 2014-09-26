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

import cn.voicet.gc.dao.TaskDao;
import cn.voicet.gc.form.TaskForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(TaskDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class TaskDaoImpl extends BaseDaoImpl implements TaskDao {

	private static Logger log = Logger.getLogger(TaskDaoImpl.class);

	public void queryTaskList(final DotSession ds) {
		log.info("sp:web_task_list()");
		this.getJdbcTemplate().execute("{call web_task_list()}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
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
	

	public void queryAcdSelectedByTid(final DotSession ds, final TaskForm taskForm) {
		log.info("sp:web_ctsgrp_list(?)");
		this.getJdbcTemplate().execute("{call web_ctsgrp_list(?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, taskForm.getTid());
				cs.execute();
				ResultSet rs = cs.getResultSet();
				rs = cs.getResultSet();
				ds.list2 = new ArrayList();
				if(rs!=null){
					while (rs.next()) {
						 Map map = new HashMap();
						 VTJime.putMapDataByColName(map, rs);
		        		 ds.list2.add(map);
					}
				}		
				return null;
			}
		});
	}

	
	public String saveTask(final TaskForm taskForm) {
		return (String)this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = null;
				if(taskForm.getTid()!=0)
				{
					log.info("sp:web_task_update(?,?,?,?,?,?)");
					cs = conn.prepareCall("{call web_task_update(?,?,?,?,?)}");
					cs.clearParameters();
					cs.setInt(1, taskForm.getTid());
					cs.setString(2, taskForm.getTname());
					cs.setInt(3, taskForm.getKind());
					cs.setString(4, taskForm.getTaskinfo());
					cs.registerOutParameter(5, Types.VARCHAR);
					cs.execute();
					log.info("ret:"+cs.getString(5));
					return cs.getString(5);
				}
				else
				{
					log.info("sp:web_task_insert(?,?,?,?)");
					cs = conn.prepareCall("{call web_task_insert(?,?,?,?)}");
					cs.clearParameters();
					cs.setString(1, taskForm.getTname());
					cs.setInt(2, taskForm.getKind());
					cs.setString(3, taskForm.getTaskinfo());
					cs.registerOutParameter(4, Types.INTEGER);
					cs.execute();
					log.info("tid:"+cs.getString(4));
					return cs.getString(4);
				}
			}
		});
	}

	public String deleteTaskByTid(final TaskForm taskForm) {
		log.info("sp:web_task_delete(?,?)");
		return (String)this.getJdbcTemplate().execute("{call web_task_delete(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, taskForm.getTid());
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.execute();
				cs.getString(2);
				log.info("ret:"+cs.getString(2));
				return cs.getString(2);
			}
		});
	}
	

	/************************************************************************/
	/******************************** 号码管理   *********************************/
	/************************************************************************/

	public void queryTelByTid(final DotSession ds, final TaskForm taskForm) {
		log.info("sp:web_tasktel_query(?,?,?,?,?,?)");
		this.getJdbcTemplate().execute("{call web_tasktel_query(?,?,?,?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, taskForm.getTid());
				cs.setString(2, taskForm.getTelnum());
				if(taskForm.getDdstate()==20)
				{
					cs.setString(3, null);
				}
				else
				{
					cs.setInt(3, taskForm.getDdstate());
				}
				if(taskForm.getCallret()==20)
				{
					cs.setString(4, null);
				}
				else
				{
					cs.setInt(4, taskForm.getCallret());
				}
				cs.setInt(5, taskForm.getStart());
				cs.setInt(6, 500);
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

	public void exportData(final TaskForm taskForm, final HttpServletResponse response) {
		final String sp_exports[]={"{call web_tasktel_export(?)}","{call web_huifang_export1(?)}","{call web_huifang_export2(?)}","{call web_huifang_export3(?)}"};
		final String excelExportFile[]={"huifang0_export.xls","huifang1_export.xls","huifang2_export.xls","huifang3_export.xls"};
		final String outputFileName[]={"huifangexport0.xls","huifangexport1.xls","huifangexport2.xls","huifangexport3.xls"};
		final int iKind = taskForm.getKind();
		//
		log.info("sp:"+sp_exports[iKind]+", excel:"+excelExportFile[iKind]);
		if(iKind >=0 &&iKind <=3){
			this.getJdbcTemplate().execute(sp_exports[iKind], new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
					//set param
					cs.setInt(1, taskForm.getTid());
					cs.execute();
					//
					ResultSet rs = cs.getResultSet();
					ResultSetMetaData rsm =rs.getMetaData();
					int columnCount = rsm.getColumnCount();
					//
					String filePath = ServletActionContext.getServletContext().getRealPath("excelTemplate")+"/"+excelExportFile[iKind];
					HSSFWorkbook wb=DotSession.fromRStoExcel(filePath, 1, true, rs, columnCount);
					try {
						response.reset();
						response.setHeader("Content-Disposition", "attachment;filename=" + outputFileName[iKind]);
						response.setContentType("application/vnd.ms-excel;charset=UTF-8");	
						wb.write(response.getOutputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
			});
		}
	}

	public void batchImportData(final File uploadExcel, final int iTid, final int iKind) {
		final int MAX_COL_CHECK = 200;
		final String sp_name[] = {
				"{call web_tasktel_import(?,?,?)}",
				"{call web_huifang_import1(?,?,?,?,?,?,?,?,?)}",
				"{call web_huifang_import2(?,?,?,?,?,?,?)}",
				"{call web_huifang_import3(?,?,?,?,?,?,?,?,?)}"};
		log.info("sp:"+sp_name[iKind]);
		//col_max[] excel actual column num
		final int COL_ACTUAL_NUM[] = {2,8,6,8};
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				PreparedStatement ps = null;
				//close session auto commit
				conn.setAutoCommit(false);
				ps = conn.prepareStatement(sp_name[iKind]);
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
					for(int i=1; i<totalRowNum;i++)
					{
						Row row = sheet.getRow(i);
						Cell cell;
						bCheckOK=true;
						//curCol
						for(int j=0;j<COL_ACTUAL_NUM[iKind];j++)
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
						}// end col
						//exec procedure
						if(bCheckOK)
						{
							//set task number
							ps.setInt(1, iTid);
							for(int j=0; j<COL_ACTUAL_NUM[iKind]; j++)
							{
								ps.setString(j+2, cellValues[j]);
							}
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

	public StringBuffer errorString;  
	private boolean checkCellOK(int iKind, int curRow, int curCol, String cellValue) {
		if(null==cellValue || cellValue.length()<=0) return false;
		//
		switch (iKind) {
		case 0:
			switch (curCol) {
				case 1:
					//ttid
					if(cellValue.equals("") || null==cellValue)
					{
						errorString.append("第"+curRow+"行,第"+curCol+"列:"+"电话编号不能为空");
						return false;
					}
					break;
				case 2:
					//telnum
					if(cellValue.equals("") || null==cellValue)
					{
						errorString.append("第"+curRow+"行,第"+curCol+"列:"+"电话号码不能为空");
						return false;
					}
					break;
					
				default:
					break;
			}
			break;
			//
		case 1:
			switch (curCol) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					
					return true;
				default:
					break;
			}
			break;
			//
		case 2:
			switch (curCol) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
				case 5:
				case 6:
					break;
				default:
					break;
			}
			break;
			//
		case 3:
			switch (curCol) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				default:
					break;
			}
			break;
		default:
			break;
		}
		return true;
	}

	public void emptyTaskTel(final TaskForm taskForm) {
		log.info("sp:web_tasktel_empty(?)");
		this.getJdbcTemplate().execute("{call web_tasktel_empty(?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, taskForm.getTid());
				cs.execute();
				return null;
			}
		});
	}

	public void deleteTaskTelByTtid(final TaskForm taskForm) {
		log.info("sp:web_tasktel_delete(?,?)");
		this.getJdbcTemplate().execute("{call web_tasktel_delete(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, taskForm.getTid());
				cs.setInt(2, taskForm.getTtid());
				cs.execute();
				return null;
			}
		});
	}

	public void recallTel(final TaskForm taskForm) {
		log.info("sp:web_tasktel_recall(?,?)");
		this.getJdbcTemplate().execute("{call web_tasktel_recall(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, taskForm.getTid());
				cs.setInt(2, taskForm.getTtid());
				cs.execute();
				return null;
			}
		});
	}

	public void saveTaskTel(final TaskForm taskForm) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = null;
				if(taskForm.getTtid()>0)
				{
					log.info("sp:web_tasktel_update(?,?,?)");
					cs = conn.prepareCall("{call web_tasktel_update(?,?,?)}");
					cs.setInt(1, taskForm.getTid());
					cs.setInt(2, taskForm.getTtid());
					cs.setString(3, taskForm.getTelnum());
				}
				else
				{
					log.info("sp:web_tasktel_add(?,?)");
					cs = conn.prepareCall("{call web_tasktel_add(?,?)}");
					cs.setInt(1, taskForm.getTid());
					cs.setString(2, taskForm.getTelnum());
				}
				cs.execute();
				return null;
			}
		});
	}

	public int blackFilter(final TaskForm taskForm) {
		log.info("sp:web_task_checkblack(?,?)");
		return (Integer)this.getJdbcTemplate().execute("{call web_task_checkblack(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, taskForm.getTid());
				cs.registerOutParameter(2, Types.INTEGER);
				cs.execute();
				log.info("rn:"+cs.getInt(2));
				return cs.getInt(2);
			}
		});
	}

	public void setAcdByTid(final TaskForm taskForm) {
		log.info("sp:web_task_setacd(?,?)");
		this.getJdbcTemplate().execute("{call web_task_setacd(?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				cs.setInt(1, taskForm.getTid());
				String s = "";
				for(int i=0; i<taskForm.getCts().length; i++)
				{
					s+=taskForm.getCts()[i];
				}
				s = s.substring(1,s.length());
				log.info("s:"+s);
				cs.setString(2, s);
				cs.execute();
				return null;
			}
		});
	}

}
