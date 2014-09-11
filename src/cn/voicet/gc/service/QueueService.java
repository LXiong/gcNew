package cn.voicet.gc.service;

import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;

public interface QueueService {
	public final static String SERVICE_NAME = "cn.voicet.gc.service.impl.QueueServiceImpl";
	void queryQueueList(DotSession ds, QueueForm queueForm);
	void saveQueue(QueueForm queueForm);
	void deleteQueueByTid(QueueForm queueForm);
}
