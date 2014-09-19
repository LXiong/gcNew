package cn.voicet.gc.dao;

import cn.voicet.gc.web.form.NtsForm;
import cn.voicet.util.DotSession;

public interface NtsDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.NtsDaoImpl";
	void queryNtsList(DotSession ds, NtsForm ntsForm);
	void saveNts(DotSession ds, NtsForm ntsForm);
	void deleteNts(NtsForm ntsForm);
}
