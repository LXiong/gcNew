package cn.voicet.gc.dao;

import cn.voicet.gc.form.CallRecordForm;
import cn.voicet.util.DotSession;

public interface CallRecordDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.CallRecordDaoImpl";
	void queryCallRecordList(DotSession ds, CallRecordForm callRecordForm);
	void queryAllCallRecordList(DotSession ds, CallRecordForm callRecordForm);
}
