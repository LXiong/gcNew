package cn.voicet.gc.dao;

import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;

public interface QueueDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.QueueDaoImpl";
	void queryQueueList(DotSession ds, QueueForm queueForm);
	void saveQueue(QueueForm queueForm);
	void deleteQueueByTid(QueueForm queueForm);
}
