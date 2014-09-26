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
		log.info("form sdt:"+callRecordForm.getSdt()+", edt:"+callRecordForm.getEdt());
		if(null!=callRecordForm.getSdt() || null!=callRecordForm.getEdt())
		{
			ds.cursdt = callRecordForm.getSdt();
			ds.curedt = callRecordForm.getEdt();
		}
		log.info("ds cursdt:"+ds.cursdt+", curedt:"+ds.curedt);
		callRecordDao.queryCallRecordList(ds, callRecordForm);
		return "show_call";
	}
	
}
