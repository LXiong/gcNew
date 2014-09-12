package cn.voicet.gc.web.action;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.voicet.gc.service.QueueService;
import cn.voicet.gc.web.form.QueueForm;
import cn.voicet.util.DotSession;

import com.opensymphony.xwork2.ModelDriven;

@Controller("queueAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class QueueAction extends BaseAction implements ModelDriven<QueueForm>{
	private static Logger log = Logger.getLogger(QueueAction.class);
	@Resource(name=QueueService.SERVICE_NAME)
	private QueueService queueService;
	private QueueForm queueForm = new QueueForm();
	
	public QueueForm getModel() {
		return queueForm;
	}
	
	public String home(){
		DotSession ds = DotSession.getVTSession(request);
		queueService.queryQueueList(ds, queueForm);
		return "show_queuelist";
	}
	
	
	public String saveQueue(){
		log.info("tid:"+queueForm.getTid()+", name:"+queueForm.getName()+", ani:"+queueForm.getAni()+" kind:"+queueForm.getKind()+", maxline:"+queueForm.getMaxline()+", overflowto:"+queueForm.getOverflowto());
		queueService.saveQueue(queueForm);
		log.info("---------- save queue success ----------");
		return home();
	}
	
	/**
	 * 删除任务队列
	 * @return
	 */
	public String deleteQueue(){
		log.info("tid:"+queueForm.getTid());
		queueService.deleteQueueByTid(queueForm);
		log.info("---------- delete queue success ----------");
		return home();
	}
	
	/**
	 * 导出队列数据
	 * kind 0：标准，
	 * 		1：回访1，
	 * 		2：回访2，
	 * 		3：回访3
	 */
	public String export(){
		queueService.exportData(queueForm, response);
		return null;
	}
	
}
