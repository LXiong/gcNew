package cn.voicet.gc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.voicet.gc.dao.QueueDao;
import cn.voicet.gc.service.QueueService;
import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;

@Transactional(readOnly=true)
@Service(QueueService.SERVICE_NAME)
public class QueueServiceImpl implements QueueService{
	
	@Resource(name=QueueDao.SERVICE_NAME)
	private QueueDao queueDao;

	public void queryQueueList(DotSession ds, QueueForm queueForm) {
		queueDao.queryQueueList(ds, queueForm);
	}
	public void saveQueue(QueueForm queueForm) {
		queueDao.saveQueue(queueForm);
	}
	public void deleteQueueByTid(QueueForm queueForm) {
		queueDao.deleteQueueByTid(queueForm);
	}
}
