package cn.voicet.gc.web.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.service.SystemLogService;
import cn.voicet.gc.web.form.SystemLogForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("systemLogAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class SystemLogAction extends BaseAction implements ModelDriven<SystemLogForm>{
	private static Logger log = Logger.getLogger(SystemLogAction.class);
	@Resource(name=SystemLogService.SERVICE_NAME)
	private SystemLogService systemLogService;
	private SystemLogForm systemLogForm = new SystemLogForm();
	
	public SystemLogForm getModel() {
		return systemLogForm;
	}
	
	public String home(){
		
		DotSession ds = DotSession.getVTSession(request);
		//如果开始日期 或 结束日期为空, 查询结果为当前日期的系统日志
		if (request.getParameter("startdate")=="" || request.getParameter("enddate")==""){
			systemLogForm.setStartdate(systemLogForm.getDate());
			systemLogForm.setEnddate(systemLogForm.getDate());
		}
		//判断, 上一页小于1时, 上一页等于首页
		if(systemLogForm.getCurPage()<1){
			systemLogForm.setCurPage(1);
		}
		int totalP = systemLogService.findLogTotalPage(ds, systemLogForm);
		log.info("totalP:"+totalP);
		systemLogForm.setTotalPage(totalP);
		//判断,下一页大于总页数时, 下一页等于最后一页
		if(systemLogForm.getCurPage()>systemLogForm.getTotalPage()){
			systemLogForm.setCurPage(systemLogForm.getTotalPage());
		}
		systemLogService.findLogInfoList(ds, systemLogForm);
		log.info("ds.list:"+ds.list);
		return "show_log";
	}
	
}
