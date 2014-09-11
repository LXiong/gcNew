package cn.voicet.gc.service;

import cn.voicet.gc.web.form.SystemLogForm;
import cn.voicet.util.DotSession;

public interface SystemLogService {
	public final static String SERVICE_NAME = "cn.voicet.gc.service.impl.SystemLogServiceImpl";
	int findLogTotalPage(DotSession ds, SystemLogForm systemLogForm);
	void findLogInfoList(DotSession ds, SystemLogForm systemLogForm);
}
