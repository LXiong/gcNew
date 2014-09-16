package cn.voicet.gc.dao;

import cn.voicet.gc.web.form.SubTelForm;
import cn.voicet.util.DotSession;

public interface SubTelDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.SubTelDaoImpl";
	void querySubTelList(DotSession ds);
	void saveSubTel(DotSession ds, SubTelForm subTelForm);
	void deleteSubTelByTelid(SubTelForm subTelForm);
}
