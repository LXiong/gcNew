package cn.voicet.gc.dao;

import java.util.Map;

import cn.voicet.gc.form.HuiFangForm;

public interface HuiFangDao{
	public final static String SERVICE_NAME = "cn.voicet.gc.dao.impl.HuiFangDaoImpl";
	Map<String,Object> getHuiFang1Info();
	void saveHuiFangOneInfo(String[] hf);
	
	Map<String, Object> getHuiFang2Info();
	void saveHuiFangTwoInfo(String[] hf);
	
	Map<String, Object> getHuiFang3Info();
	void saveHuiFangThreeInfo(String[] hf);
	
	//
	Map<String, Object> getHuiFangInfo(int flag,HuiFangForm huiFangForm);
	void saveHuiFangInfo(int flag, HuiFangForm huiFangForm);
	int getHuiFangType(HuiFangForm huiFangForm);
}
