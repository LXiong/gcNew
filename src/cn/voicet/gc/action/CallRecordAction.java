package cn.voicet.gc.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.dao.CallRecordDao;
import cn.voicet.gc.form.CallRecordForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("callRecordAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class CallRecordAction extends BaseAction implements ModelDriven<CallRecordForm>{
	private static Logger log = Logger.getLogger(CallRecordAction.class);
	@Resource(name=CallRecordDao.SERVICE_NAME)
	private CallRecordDao callRecordDao;
	private CallRecordForm callRecordForm = new CallRecordForm();
	
	public CallRecordForm getModel() {
		return callRecordForm;
	}
	
	public String list()
	{
		DotSession ds = DotSession.getVTSession(request);
		callRecordDao.queryCallRecordList(ds, callRecordForm);
		log.info("list size:"+ds.list.size());
		return "show_call";
	}
	
}
