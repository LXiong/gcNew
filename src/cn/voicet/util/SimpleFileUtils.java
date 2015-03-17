package cn.voicet.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件工具
 * @author Applexiang
 * 
 */
public class SimpleFileUtils {

	/**
	 * 下载Excel文件
	 * @param path	下载文件路径
	 * @param response
	 */
	public static void downloadExcelFile(String path, HttpServletResponse response)
	{
		try {
			// path是指欲下载的文件的路径。  
	        File file = new File(path);  
	        // 取得文件名。  
	        String filename = file.getName();
	        // 以流的形式下载文件。  
	        InputStream fis = new BufferedInputStream(new FileInputStream(path));  
	        byte[] buffer = new byte[fis.available()];  
	        fis.read(buffer);
	        fis.close();
	        // 重置response
	        response.reset();
	        // 设置response的Header
	        response.addHeader("Content-Disposition", "attachment;filename="+ new String(filename.getBytes()));
	        response.addHeader("Content-Length", "" + file.length());
	        response.setContentType("application/vnd.ms-excel;charset=gb2312");
	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
	        //delete file
	        if(file.exists())
	        {
	        	file.delete();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
