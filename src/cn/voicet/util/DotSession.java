package cn.voicet.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@SuppressWarnings("unchecked")
public class DotSession {

	public String account;
	public String password;
	public String username;
	public String roleName;
	public String agttelnum;
	public String roleID;
	public SubPathTitle subPathTitle;
	public String rbm;
	public String rbn;
	public String curBM;
	public String curHM;
	public String navPath;
	public String opCode;//opera code tlype
	public int isedit;
	public int workyear;	//工作�?
	public int yearlock;	//年锁�?
	public int curGolbalPage;//全局当前�?
	//private List stackList;
	public Map map;
	public List list;
	public List list2;
	public List list3;
	public List list4;
	public List list5;
	public List ctsList;
	public int curYear;	//当前年份
	private int stackPos=0;
	private StackInfo[] arrayStackInfo;
	public String curCTS = "cts100";
	
	public String bmhm;
	
	public int sflag;
	
	public String html;
	
	String curDate = String.format("%tF", new Date());
	public String cursdt = curDate;
	public String curedt = curDate;
	
	private class StackInfo{
		
		public StackInfo(){}
		List list;
		List list2;
		List list3;
		List list4;
		List list5;
	}
	public void pushAllList() {
		if(stackPos<8){
			arrayStackInfo[stackPos].list=list;
			arrayStackInfo[stackPos].list2=list2;
			arrayStackInfo[stackPos].list3=list3;
			arrayStackInfo[stackPos].list4=list4;
			arrayStackInfo[stackPos++].list5=list5;
		}
		System.out.println("pushAllList cur:"+stackPos);
	}
	public int getStackLevel(){
		return stackPos;
	}
	public boolean hasStack() {
		return stackPos>0;
	}
	
	public void popAllList() {
		if(stackPos>0){
			list=arrayStackInfo[--stackPos].list;
			list2=arrayStackInfo[stackPos].list2;
			list3=arrayStackInfo[stackPos].list3;
			list4=arrayStackInfo[stackPos].list4;
			list5=arrayStackInfo[stackPos].list5;
			arrayStackInfo[stackPos].list=null;
			arrayStackInfo[stackPos].list2=null;
			arrayStackInfo[stackPos].list3=null;
			arrayStackInfo[stackPos].list4=null;
			arrayStackInfo[stackPos].list5=null;
		}
	}
	
	public void clearStack(){
		for(int i=0;i<stackPos;i++){
			arrayStackInfo[i].list=null;
			arrayStackInfo[i].list2=null;
			arrayStackInfo[i].list3=null;
			arrayStackInfo[i].list4=null;
			arrayStackInfo[i].list5=null;
		}
		stackPos=0;
	}
	
	public void popAllList(int iPos){
		for(;iPos<stackPos;){
			popAllList();
		}
	}
	
	public void initData() {
		list = new ArrayList();
		list2 = new ArrayList();
		list3 = new ArrayList();
		list4 = new ArrayList();
		list5 = new ArrayList();
	}
	
	public void emptyData() {
		map=null;
		list=null;;
		list2=null;
		list3=null;
		list4=null;
		list5=null;
	}
	
	public DotSession() {
		account = "none";
		roleName = "none";
		curBM="";
		roleID = "0";
		opCode = "";
		map = new HashMap();
		subPathTitle = new SubPathTitle();
		curYear = Calendar.getInstance().get(Calendar.YEAR);
		list=null;
		list5=null;
		//
		arrayStackInfo = new StackInfo[8];
		for (int i=0; i<8; i++) {
			arrayStackInfo[i] = new StackInfo();
		}
		sflag=0;
	}
	
	public void clear() {
		account = "none";
		roleID = "0";
	} 
	
	public static DotSession getVTSession(HttpSession session) {
		DotSession ds = (DotSession)session.getAttribute("vts");
		return ds;
	}
	
	public static DotSession getVTSession(HttpServletRequest request) {
		DotSession ds = (DotSession)request.getSession().getAttribute("vts");
		return ds;
	}
	
	/** 将结果集数据放进Map */
	public static void putMapData(Map map,ResultSet rs) {
		try {
			//ResultSetMetaData rsm =rs.getMetaData();
			//获取列数
			int col = rs.getMetaData().getColumnCount();
			for(int i=0; i<col; i++) {
				if(null!=rs.getString(i+1) && !rs.getString(i+1).equals("")){
					map.put("c"+i, rs.getString(i+1));
				}else{
					map.put("c"+i, "");
				}
			}
		} catch (Exception e) {
			System.out.println("");
		}
	}
	/** 将结果集数据放进Map */
	public static void putMapDataByColName(Map map,ResultSet rs) {
		String sColName;
		try {
			//获取列数
			ResultSetMetaData rsm =rs.getMetaData();
			int col = rsm.getColumnCount();
			for(int i=1; i<=col; i++) {
				sColName=rsm.getColumnName(i);
				if(null!=rs.getString(i) && !rs.getString(i).equals("")){
					//��ȡ�������
					int dataType = rsm.getColumnType(i);
					if(dataType==3)
					{
						map.put(sColName, rs.getFloat(i));
					}
					else
					{
						map.put(sColName, rs.getString(i));
					}
					
				}else{
					map.put(sColName, "");
				}
			}
		} catch (Exception e) {
			System.out.println("");
		}
	}

	
	/**
	 * m money
	 * i int
	 * f float
	 * String ix[][]={{"-1","o"},{"2","m"},{"3","i"},{"4","f"}}; 
	 * @param cs
	 * @param sr String	ҳ��������� 
	 * @param ix index	�Դ洢������˳��Ϊ�±�,��0��ʼ����,������������	
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public static void prepareParamFromInputArray(CallableStatement cs, String sr[],String ix[][]) throws NumberFormatException, SQLException
	{
		for(int i=0;i<ix.length;i++)
		{
			if(ix[i][1].equalsIgnoreCase("s"))
			{
				cs.setString(i+1,sr[Integer.parseInt(ix[i][0])]);
			}
			else if(ix[i][1].equalsIgnoreCase("m") || ix[i][1].equalsIgnoreCase("f"))
			{
				cs.setFloat(i+1,Float.parseFloat(sr[Integer.parseInt(ix[i][0])]));
			}
			else if(ix[i][1].equalsIgnoreCase("i"))
			{
				cs.setInt(i+1,Integer.parseInt(sr[Integer.parseInt(ix[i][0])]));
			}
			else if(ix[i][1].equalsIgnoreCase("o"))
			{
				//--discard 
			}
		}
	}
	
	public static HSSFWorkbook fromRStoExcel(String tempFile, int headlinenum, boolean isDrawBoard, ResultSet rs, int columnCount)
	{
		String dataType[]=new String[60];//Data type
		String FieldName[]=new String[60];//Data type
		HSSFWorkbook workBook;
		
		try {
			File file = new File(tempFile);
			if(file.exists() && file.isFile())
			{
				workBook = new HSSFWorkbook(new FileInputStream(tempFile));
				HSSFSheet sheet = workBook.getSheetAt(0);
		        HSSFCell cell;
		        HSSFRow row1 = sheet.getRow(headlinenum);//HeadRowNum  
		        HSSFRow row2 = sheet.getRow(headlinenum+1);//HeadRowNum  
				for(int i=0;i<columnCount;i++)
		        {
					if(null!=row1) 
						{
						cell=row1.getCell(i);
						if (null!=cell)
						{
							FieldName[i]=cell.getStringCellValue();
							cell.setCellValue("");
						}
						else
						{
							FieldName[i]="^";
						}
					}
					if (null!=row2)
						{
						cell=row1.getCell(i);
						if (null!=cell)
						{
							dataType[i]=cell.getStringCellValue();
							cell.setCellValue("");
						}
					}
		        }
				//
				HSSFCellStyle style = workBook.createCellStyle();
				if(isDrawBoard)
		        {
			        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		        }
				//add line data ;
				 int i=0;
				 while (rs.next()) 
				 {
					 row1 = sheet.getRow(i+headlinenum);
					 if(null==row1) row1=sheet.createRow(i+headlinenum);
					 for(int j=0;j<columnCount;j++)
					 {
						 cell = row1.getCell(j);
						 if (null==cell) cell = row1.createCell(j);
						 if(null!=cell && isDrawBoard) cell.setCellStyle(style);
						 if(null!= cell && null!=FieldName[j] && FieldName[j]!="^")
						 {
							 if(null!=dataType[j] && dataType[j].equals("N"))
							 {
									String excelData = (String)rs.getString(FieldName[j]); 
									if(null!=excelData && excelData.length()>0)
									{
										cell.setCellValue(Double.parseDouble(excelData));
									}
							}
							else
							{
								
								cell.setCellValue((String)rs.getString(FieldName[j]));
							}	 
						 }
					 }//end for
					 i++;
				 }//end while
				 return workBook;
			}//end temp
			else
			{
				return null;
			}
		}//end try
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	
	public static final String LH_SERVER_IP1 = "http://192.168.1.114";	//cts100
	public static final String LH_SERVER_IP2 = "http://192.168.1.11";	//cts200
	public static final String VT_SERVER_IP = "http://192.168.1.200";	//cts200
	
	public String ipflag = "lh";
	
	public String getIpWithCTS(String icts)
	{
		if(ipflag.equalsIgnoreCase("lh"))
		{
			if(icts.equals("cts100"))
			{
				return LH_SERVER_IP1;
			}
			else if(icts.equals("cts200"))
			{
				return LH_SERVER_IP2;
			}
			else
			{
				return null;
			}
		}
		else if(ipflag.equalsIgnoreCase("vt"))
		{
			return VT_SERVER_IP;
		}
		else 
		{
			return null;
		}
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAgttelnum() {
		return agttelnum;
	}
	public void setAgttelnum(String agttelnum) {
		this.agttelnum = agttelnum;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRbm() {
		return rbm;
	}
	public void setRbm(String rbm) {
		this.rbm = rbm;
	}
	public String getRbn() {
		return rbn;
	}
	public void setRbn(String rbn) {
		this.rbn = rbn;
	}
	public String getNavPath() {
		return navPath;
	}
	public void setNavPath(String navPath) {
		this.navPath = navPath;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	public int getIsedit() {
		return isedit;
	}
	public void setIsedit(int isedit) {
		this.isedit = isedit;
	}
	public int getWorkyear() {
		return workyear;
	}
	public void setWorkyear(int workyear) {
		this.workyear = workyear;
	}
	public int getYearlock() {
		return yearlock;
	}
	public void setYearlock(int yearlock) {
		this.yearlock = yearlock;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public List getList2() {
		return list2;
	}
	public void setList2(List list2) {
		this.list2 = list2;
	}
	public List getList3() {
		return list3;
	}
	public void setList3(List list3) {
		this.list3 = list3;
	}
	public List getList4() {
		return list4;
	}
	public void setList4(List list4) {
		this.list4 = list4;
	}
	public List getList5() {
		return list5;
	}
	public void setList5(List list5) {
		this.list5 = list5;
	}
	public List getCtsList() {
		return ctsList;
	}
	public void setCtsList(List ctsList) {
		this.ctsList = ctsList;
	}
	public String getCurCTS() {
		return curCTS;
	}
	public void setCurCTS(String curCTS) {
		this.curCTS = curCTS;
	}
	public int getSflag() {
		return sflag;
	}
	public void setSflag(int sflag) {
		this.sflag = sflag;
	}
	public String getCurDate() {
		return curDate;
	}
	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}
	public String getCursdt() {
		return cursdt;
	}
	public void setCursdt(String cursdt) {
		this.cursdt = cursdt;
	}
	public String getCuredt() {
		return curedt;
	}
	public void setCuredt(String curedt) {
		this.curedt = curedt;
	}
}

