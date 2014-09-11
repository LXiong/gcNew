package cn.voicet.gc.dao;

import cn.voicet.gc.web.form.SystemLogForm;
import cn.voicet.util.DotSession;

public interface SystemLogDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.SystemLogDaoImpl";
	int findLogTotalPage(DotSession ds, SystemLogForm systemLogForm);
	void findLogInfoList(DotSession ds, SystemLogForm systemLogForm);
}
