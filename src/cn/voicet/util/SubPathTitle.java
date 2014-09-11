package cn.voicet.util;

/**
 * @see ����·��������
 */
public class SubPathTitle {
	
	private final int MAXSUBITEMNUM = 16;
	private SubPathItem[] subPathItem;
	private int curPos;
	private String ybhflag;
	
	public SubPathTitle(){
		curPos = 0;
		subPathItem = new SubPathItem[MAXSUBITEMNUM];
		for (int i=0; i<MAXSUBITEMNUM; i++) {
			subPathItem[i] = new SubPathItem();
		}
	}
	
	/** ��ʼ�� */
	public void initPath(){
		curPos=0;
	}
	
	private class SubPathItem {
		public String sName;
		public String sExt;
		public String stag;//
		
		public SubPathItem() {
			sName = "";
			sExt = "";
		}
	}
	
	public String getCurKey(){
		if(curPos>0){
			return subPathItem[curPos-1].stag;
		}
		return "";
	}
	
	public void setRoot(String sName, String sExt,String stag) {
		setCurInfo(sName, sExt,stag);
		curPos = 1;
	}
	
	public boolean hasRoot() {
		return curPos>0;
	}
	
	/**
	 * 
	 * @param sName �������
	 * @param sExt	��ǰ����
	 * @param stag	�����
	 */
	private void setNextInfo(String sName, String sExt,String stag) {
		if (curPos < MAXSUBITEMNUM) {
			setCurInfo(sName, sExt,stag);
			curPos++;
		}
	}
	
	/**
	 * ֱ�ӽ���������޸ĵ�ǰ����
	 * @param sListStr eg:����ʡ;32;32;�Ͼ���;3208;32;
	 */
	public void setFullPath(String sListStr)
	{
		initPath();
		String navArr[]=sListStr.split(";");
		for(int i=0;i<navArr.length/3;i++)
		{
			setNextInfo(navArr[i*3],navArr[i*3+1],navArr[i*3+2]);
		}
	}
	
	public void setInfoByEx(String sName,String sEx,String stag) {
		boolean bFound=false;
		for (int i=0; i<curPos; i++) {
			if (subPathItem[i].sExt.equals(sEx)) {
				subPathItem[i].sName=sName;
				curPos = i + 1;
				bFound=true;
			}
		}
		if(!bFound) {
			setNextInfo(sName,sEx,stag);
		}
	}

	/** ҳ�淵�� */
	public void rollBack() {
		if(curPos>1)
			curPos--;
	}
	
	public void setCurInfo(String sName, String sExt,String stag) {
		subPathItem[curPos].sName = sName;
		subPathItem[curPos].sExt = sExt;
		subPathItem[curPos].stag=stag;
	}

	/** ����·�� */
	public String getHtmlString() {
		String navPath = "";
		for (int i = 0; i < curPos; i++) {
			if(ybhflag.equals("check"))
			{
			navPath += "<a href='ybhCheckAction_viewNavYbh.action?viewBM="
					+ subPathItem[i].sExt + "&oname=" + subPathItem[i].sName + "'";
			}
			else if(ybhflag.equals("manage"))
			{
				navPath += "<a href='ybhManageAction_viewNavYbh.action?viewBM="
					+ subPathItem[i].sExt + "&oname=" + subPathItem[i].sName + "'";
			}
			else if(ybhflag.equals("yeardata"))
			{
				navPath += "<a href='yearDataAction_viewYbh.action?viewBM="
					+ subPathItem[i].sExt + "&oname=" + subPathItem[i].sName + "'";
			}
			else
			{
				navPath += "<a href='ybhManageAction_viewNavYbh.action?viewBM="
					+ subPathItem[i].sExt + "&oname=" + subPathItem[i].sName + "'";
			}
			navPath += " style='cursor:pointer; color:#fff' target='mainFrame' class='toolbar_eixt'>";
			navPath += subPathItem[i].sName;
			navPath += "</a>";
			if (i < curPos - 1) {
				navPath += ">>";
			}
		}
		return navPath;
	}

	public void setCurPos(int iPos) {
		if (iPos > 0 && iPos < MAXSUBITEMNUM && iPos <= curPos) {
			curPos = iPos;
		}
	}

	public String getYbhflag() {
		return ybhflag;
	}
	public void setYbhflag(String ybhflag) {
		this.ybhflag = ybhflag;
	}
	
}
