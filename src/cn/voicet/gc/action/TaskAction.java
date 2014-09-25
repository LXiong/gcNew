package cn.voicet.gc.action;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.TaskDao;
import cn.voicet.gc.form.TaskForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("taskAction")
@Scope(value="prototype")
@SuppressWarnings({"serial","unchecked","unused"})
public class TaskAction extends BaseAction implements ModelDriven<TaskForm>{
	private static Logger log = Logger.getLogger(TaskAction.class);
	@Resource(name=TaskDao.SERVICE_NAME)
	private TaskDao taskDao;
	private TaskForm taskForm = new TaskForm();
	
	public TaskForm getModel() {
		return taskForm;
	}
	
	/**
	 * 任务列表
	 */
	public String list()
	{
		DotSession ds = DotSession.getVTSession(request);
		taskDao.queryTaskList(ds);
		return "show_task";
	}
	
	public String getAcdSelectByTid() throws IOException
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("tid:"+taskForm.getTid());
		taskDao.queryAcdSelectedByTid(ds, taskForm);
		String html="";
		boolean bHaveLine=false;
		Map map;
		for (int i = 0; i < ds.list2.size(); i++) 
		{
			map = (Map) ds.list2.get(i);
			if(map.get("grpid").equals("0"))
			{
				if(bHaveLine)
				{
						html+="</div>";
					html+="</div>";
				}
				bHaveLine=false;
				html+="<div class='lab_ipt_item' style='font-size:16px; font-weight:bold'>";
				html+="<span class='lab120'>"+map.get("name")+"：</span>";
				html+="<input type='hidden' name='cts' value=';"+map.get("name")+"='/>";
				html+="<div class='ipt-box-hei'>";
				html+="<label>"+map.get("info")+"</label>";
				html+="</div>";
				html+="</div>";
			}
			else
			{
				if(!bHaveLine)
				{
					html+="<div class='lab_ipt_item'>";
					html+="<span class='lab120'></span>";
					html+="<div class='ipt-box-hei'>";
				}
				html+="<span style='display:inline-block'><input type='checkbox' id='group"+map.get("id")+"' value='"+map.get("grpid")+",' name='cts' #CHK#/><label for='group"+map.get("id")+"'>"+map.get("name")+"</label></span>&nbsp;&nbsp;";
				if(map.get("its").equals("1"))
				{
					html = html.replace("#CHK#", "checked='checked'");
				}
				else
				{
					html = html.replace("#CHK#", "");
				}
				bHaveLine= true;
			}
		}
		//
		if(bHaveLine)
		{
			html+="</div>";
			html+="</div>";
		}
		JSONObject json = new JSONObject();
		json.put("html", html);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json.toString());
		response.getWriter().flush();
		return null;
	}
	
	/**
	 * 保存或更新任务
	 * @return
	 * @throws IOException 
	 */
	public String saveTask() throws IOException{
		log.info("tid:"+taskForm.getTid()+", tname:"+taskForm.getTname()+", kind:"+taskForm.getKind()+", state:"+taskForm.getState()+", taskinfo:"+taskForm.getTaskinfo());
		String ret = taskDao.saveTask(taskForm);
		//task update
		if(ret.equals("ok"))
		{
			ret = "updateok";
			log.info("update task ["+taskForm.getTname()+"] success");
		}
		else if(ret.equals("err"))
		{
			ret = "updateerr";
			log.info("update task ["+taskForm.getTname()+"] fail");
		}
		else
		{
			ret = "insertok";
			log.info("add task ["+taskForm.getTname()+"] success");
		}
		log.info("rets:"+ret);
		response.getWriter().print(ret);
		return null;
	}
	
	/**
	 * 删除任务
	 * @return
	 * @throws IOException 
	 */
	public String deleteTask() throws IOException
	{
		log.info("tid:"+taskForm.getTid()+"tname:"+taskForm.getTname());
		String ret = taskDao.deleteTaskByTid(taskForm);
		if(ret.equals("ok"))
		{
			log.info("delete task ["+taskForm.getTname()+"] success");	
		}
		else if(ret.equals("err_haveteldata"))
		{
			log.info("delete task ["+taskForm.getTname()+"] fail");	
		}
		else
		{
			log.info("delete task error");
		}
		response.getWriter().print(ret);
		return null;
	}
	
	/**
	 * 为任务设置业务组
	 * @return
	 */
	public String setAcd()
	{
		log.info("tid:"+taskForm.getTid());
		taskDao.setAcdByTid(taskForm);
		return list();
	}
	
	/**
	 * 号码管理
	 * @return
	 */
	public String telmanage()
	{
		DotSession ds = DotSession.getVTSession(request);
		log.info("tid:"+taskForm.getTid()+", telnum:"+taskForm.getTelnum()+", ddstate:"+taskForm.getDdstate()+", callret:"+taskForm.getCallret()+", start:"+taskForm.getStart());
		taskDao.queryTelByTid(ds, taskForm);
		return "show_telmanage";
	}
	
	/**
	 * 清空任务中的所有号码
	 * @return
	 */
	public String emptyTaskTel()
	{
		log.info("tid:"+taskForm.getTid()+", kind:"+taskForm.getKind()+", tname:"+taskForm.getTname());
		taskDao.emptyTaskTel(taskForm);
		log.info("empty ["+taskForm.getTname()+"] success");
		return null;
	}
	
	/**
	 * 删除任务中的号码
	 * @return
	 */
	public String deleteTaskTel()
	{
		log.info("tid:"+taskForm.getTid()+"ttid:"+taskForm.getTtid());
		taskDao.deleteTaskTelByTtid(taskForm);
		return null;
	}
	
	/**
	 * 重呼
	 * @return
	 */
	public String recall()
	{
		log.info("tid:"+taskForm.getTid()+", ttid:"+taskForm.getTtid()+", tname:"+taskForm.getTname()+", kind:"+taskForm.getKind());
		taskDao.recallTel(taskForm);
		log.info("recall complete");
		return null;
	}
	
	/**
	 * 添加一个电话号码
	 */
	public String saveTaskTel()
	{
		log.info("tid:"+taskForm.getTid()+"ttid:"+taskForm.getTtid()+"telnum:"+taskForm.getTelnum());
		taskDao.saveTaskTel(taskForm);
		log.info("add telnum "+taskForm.getTelnum()+" complete");
		taskForm.setTelnum(null);
		return null;
	}
	
	/**
	 * 黑名单过滤
	 * @throws IOException 
	 */
	public String blackFilter() throws IOException
	{
		int rn = taskDao.blackFilter(taskForm);
		response.getWriter().print(rn);
		return null;
	}
	
	/**
	 * 导出队列数据
	 * kind 0：标准，
	 * 		1：回访1，
	 * 		2：回访2，
	 * 		3：回访3
	 */
	public String export()
	{
		log.info("tid:"+taskForm.getTid()+", kind:"+taskForm.getKind());
		taskDao.exportData(taskForm, response);
		return null;
	}
	
	/**
	 * excel批量导入
	 * @return
	 * @throws IOException 
	 */
	public String importTaskTel() throws IOException
	{
		log.info("tid:"+taskForm.getTid()+", kind:"+taskForm.getKind()+", uploadExcel file:"+uploadExcel);
		taskDao.batchImportData(uploadExcel, taskForm.getTid(), taskForm.getKind());	
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
