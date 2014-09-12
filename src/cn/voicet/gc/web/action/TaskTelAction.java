package cn.voicet.gc.web.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.service.QueueService;
import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;
import cn.voicet.util.VTJime;

import com.opensymphony.xwork2.ModelDriven;

@Controller("taskTelAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class TaskTelAction extends BaseAction implements ModelDriven<QueueForm>{
	private static Logger log = Logger.getLogger(TaskTelAction.class);
	@Resource(name=QueueService.SERVICE_NAME)
	private QueueService queueService;
	private QueueForm queueForm = new QueueForm();
	
	public QueueForm getModel() {
		return queueForm;
	}
	
	public String home(){
		DotSession ds = DotSession.getVTSession(request);
		queueService.queryTaskTelList(ds, queueForm);
		return "show_tasktel";
	}
	
	/**
	 * 导出队列数据
	 * kind 0：标准，
	 * 		1：回访1，
	 * 		2：回访2，
	 * 		3：回访3
	 */
	public String export(){
		queueService.exportData(queueForm, response);
		return null;
	}
	
	/**
	 * excel批量导入
	 * @return
	 */
	public String importTaskTel()
	{
		log.info("tid:"+queueForm.getTid()+", kind:"+queueForm.getKind()+", uploadExcel file:"+uploadExcel);
		queueService.batchImportData(uploadExcel, queueForm.getTid(), queueForm.getKind());	
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
    //excel文件行数 
    private int totalRowNum;
    //操作消耗时间
    private double opTime;
    
    private String responseMessage;
    
    private int tid;

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
	public double getOpTime() {
		return opTime;
	}
	public void setOpTime(double opTime) {
		this.opTime = opTime;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	
}
