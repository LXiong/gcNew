package cn.voicet.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings("unchecked")
public class DotRoleMenu {
	
	public String provider;	// 
	public String customer;	// 
	public String fax;		//
	public String tel;		// 
	public String url;		// 
	public String mail;		//
	public String version;	// 
	public String product;	//
	public List<RoleInfo> roleList = new ArrayList<RoleInfo>();
	public List<ListTab> tabList  = new ArrayList<ListTab>();
	//
	public List GetList(String name){
		for(int i=0;i<tabList.size();i++){
			ListTab t = tabList.get(i);
			if(t.name.equals(name)){
				return t.itemList;
			}
		}
		return null;
	}

	/**  */
	public class ListTab{
		
		public String name;
		List<ListItem> itemList;
		
		public ListTab(){
			itemList = new ArrayList<ListItem>();
		}
		
		public void loadItem(Iterator iter){
			ListItem item = new ListItem();
			Element element=(Element)iter.next();
			item.id = Integer.valueOf(element.attributeValue("id"));
			item.str = element.attributeValue("name");
			itemList.add(item);
			if(iter.hasNext()) {
				loadItem(iter);
			}
		}
	}
	
	public class ListItem{
		public int id;
		public String str;
	}
	
	public class RoleInfo {
		public String describle;
		public String roleID;
		public List<GrpInfo> grpInfoList = new ArrayList<GrpInfo>();
		
		public void LoadGrpInfo(Iterator iter) {
			GrpInfo grp = new GrpInfo();
			Element element=(Element)iter.next();
			grp.grpName = element.attributeValue("name");
			grpInfoList.add(grp);
			Iterator iex = element.elementIterator("fun");
			if(iex.hasNext()) {
				grp.loadFunInfo(iex);
			}
			if(iter.hasNext()) {
				LoadGrpInfo(iter);
			}
		}
	}
	
	/**
	 * @see 
	 */
	public class GrpInfo {
		
		public String grpName;
		public List<FunInfo> funInfoList = new ArrayList<FunInfo>();
		
		public String getGrpName() {
			return grpName;
		}
		
		public void loadFunInfo(Iterator iter) {
			FunInfo fun = new FunInfo();
			Element element=(Element)iter.next();
			fun.funName  =element.attributeValue("name");
			fun.funAction  =element.attributeValue("action");
			funInfoList.add(fun);
			if(iter.hasNext()) {
				loadFunInfo(iter);
			}
		}
	}
	
	/** fun锟节碉拷锟节诧拷锟斤拷FunInfo */
	public class FunInfo {
		public String funName;
		public String funAction;
	}
	
	public void loadListInfo(Iterator iter) {
		ListTab tab = new ListTab();
		Element element=(Element)iter.next();
		tab.name = element.attributeValue("name");
		tabList.add(tab);
		Iterator iex = element.elementIterator("item");
		if (iex.hasNext()) {
			tab.loadItem(iex);
		}
		if (iter.hasNext()) {
			loadListInfo(iter);
		}
	}
	/** */
	public void loadRoleInfo(Iterator iter) {
		RoleInfo roleInfo = new RoleInfo();
		Element element=(Element)iter.next();
		roleInfo.roleID = element.attributeValue("roleid");
		roleInfo.describle = element.attributeValue("dscrible");
		roleList.add(roleInfo);
		
		Iterator iex = element.elementIterator("grp");
		if(iex.hasNext()) {
			roleInfo.LoadGrpInfo(iex);
		}
		if(iter.hasNext()) {
			loadRoleInfo(iter);
		}
		
	}
	
	/** */
	public RoleInfo GetRoleByRoleID(int iRoleID) {
		for (int i=0;i<roleList.size();i++) {
			if(roleList.get(i).roleID.equals(String.valueOf(iRoleID)))
				return roleList.get(i);
		}
		return null;
	}
	
	public boolean loadInfoFromXML(String xmlFileName) {
		 Document document = null; 
		 SAXReader reader = new SAXReader(); 
		 roleList.clear();
		 try {
			 document = reader.read(new File(xmlFileName));
			 Element rootElement = document.getRootElement();
			 if(rootElement.getName()!="dot2014") return false;
			 product = rootElement.attributeValue("product");
			 provider = rootElement.attributeValue("provider");
			 customer = rootElement.attributeValue("customer");
			 fax = rootElement.attributeValue("fax");
			 tel = rootElement.attributeValue("tel");
			 url = rootElement.attributeValue("url");
			 mail = rootElement.attributeValue("mail");
			 version = rootElement.attributeValue("version");
			 Iterator iter = rootElement.elementIterator("role");
			 if(iter.hasNext()) {
				 loadRoleInfo(iter);
			 }
			 iter = rootElement.elementIterator("list");
			 if(iter.hasNext())
			 {
				 loadListInfo(iter);
			 }
			 return true;
		 } catch(Exception e) {
			 return false;
		 }
	}

	/** */
	public String getMenuInfoByRoleID(int roleID, String account) {
		String menuInfo = "";
		RoleInfo Role=GetRoleByRoleID(roleID);
		List<GrpInfo> grpList=Role.grpInfoList;
		List<FunInfo> funList;
		for(int i=0; i<grpList.size(); i++){
			menuInfo += "<li class='level1'><a href='javascript:void(0)'>";
			menuInfo += grpList.get(i).getGrpName()+"</a>";
			menuInfo += "<ul id='ChildMenu"+i+"' class='level2'>";
			funList = grpList.get(i).funInfoList;
			for(int j=0; funList!=null && j<funList.size(); j++) {
				menuInfo += "<li><a ";
				menuInfo += "href='"+funList.get(j).funAction+"' target='mainFrame'>";
				menuInfo += funList.get(j).funName+"</a>";
			}
			menuInfo += "</ul></li>";
		}
		return menuInfo;
	}
	
}
