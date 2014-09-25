package cn.voicet.gc.action;
import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.BlackDao;
import cn.voicet.gc.form.BlackForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("blackAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class BlackAction extends BaseAction implements ModelDriven<BlackForm>{
	private static Logger log = Logger.getLogger(BlackAction.class);
	@Resource(name=BlackDao.SERVICE_NAME)
	private BlackDao blackDao;
	private BlackForm blackForm = new BlackForm();
	
	public BlackForm getModel() {
		return blackForm;
	}
	
	public String list(){
		DotSession ds = DotSession.getVTSession(request);
		log.info("telnum:"+blackForm.getTelnum());
		blackDao.queryBlackList(ds, blackForm);
		return "show_blacklist";
	}
	
	/**
	 * 添加或更新黑名单
	 * @return 黑名单列表页面
	 */
	public String saveTelnum(){
		log.info("bid:"+blackForm.getBid()+", telnum:"+blackForm.getTelnum()+" noteinfo:"+blackForm.getNoteinfo());
		blackDao.saveTelnum(blackForm);
		blackForm.setTelnum(null);
		return list();
	}
	
	public String deleteTelnum(){
		log.info("bid:"+blackForm.getBid());
		blackDao.deleteTelnumByBid(blackForm);
		return null;
	}
	
	/**
	 * 清空黑名单 
	 * @return
	 */
	public String emptyBlack()
	{
		blackDao.emptyBlack();
		return null;
	}
	
	/**
	 * 导出黑名单数据
	 */
	public String export()
	{
		blackDao.exportData(blackForm, response);
		return null;
	}
	
	/**
	 * excel批量导入
	 * @return
	 * @throws IOException 
	 */
	public String importBlacknum() throws IOException
	{
		blackDao.batchImportData(uploadExcel);	
		return null;
	}
	
	
	//myFile属性用来封装上传的文件  
    private File uploadExcel;  
    //myFileContentType属性用来封装上传文件的类型  
    private String uploadExcelContentType;  
    //myFileFileName属性用来封装上传文件的文件名  
    private String uploadExcelFileName;
    //控制文件类型
	private static String[] allowFileType = { "xls", "XLS", "xlsx", "XLSX" };

	public File getUploadExcel() {
		return uploadExcel;
	}
	public void setUploadExcel(File uploadExcel) {
		this.uploadExcel = uploadExcel;
	}
	public String getUploadExcelContentType() {
		return uploadExcelContentType;
	}
	public void setUploadExcelContentType(String uploadExcelContentType) {
		this.uploadExcelContentType = uploadExcelContentType;
	}
	public String getUploadExcelFileName() {
		return uploadExcelFileName;
	}
	public void setUploadExcelFileName(String uploadExcelFileName) {
		this.uploadExcelFileName = uploadExcelFileName;
	}
}
