package cn.voicet.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.report.ReportEnginer;

public class SimpleJxlUtils {
	
	public static void writeExcelFile(List<Map<String, Object>> list, String listName, String templateFile, String destFile)
	{
		try {
			ReportEnginer enginer = new ReportEnginer();
			Map<String, Object> context = new HashMap<String, Object>();
			context.put(listName, list);
			enginer.excute(templateFile, context, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
