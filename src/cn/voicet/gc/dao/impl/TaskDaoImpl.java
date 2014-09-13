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
import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.gc.web.form.TaskForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

@Repository(TaskDao.SERVICE_NAME)
@SuppressWarnings("unchecked")
public class TaskDaoImpl extends BaseDaoImpl implements TaskDao {

	private static Logger log = Logger.getLogger(TaskDaoImpl.class);

	public void queryTaskList(final DotSession ds) {
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

	public void queryTelByTid(final DotSession ds, final TaskForm taskForm) {
		this.getJdbcTemplate().execute(new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				CallableStatement cs = conn.prepareCall("{call web_tasktel_query(?,?,?)}");
				cs.setInt(1, taskForm.getTid());
				cs.setString(2, taskForm.getTelnum());
				cs.setInt(3, 0);
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
	
}
