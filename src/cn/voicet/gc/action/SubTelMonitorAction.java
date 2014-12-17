package cn.voicet.gc.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.SubTelDao;
import cn.voicet.gc.form.SubTelForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("subTelMonitorAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class SubTelMonitorAction extends BaseAction implements ModelDriven<SubTelForm>{
	private static Logger log = Logger.getLogger(SubTelMonitorAction.class);
	
	private SubTelForm subTelForm = new SubTelForm();
	
	public SubTelForm getModel() {
		return subTelForm;
	}
	
	/**
	 * 座席分机监控首页
	 * @return
	 */
	public String list()
	{
		return "show_subtel_monitor";
	}
}
