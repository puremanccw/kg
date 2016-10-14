package com.charles.common.constants.utils;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * <p>文件工具类</p>
 * @author xiongyu
 * 说明：
 * 创建时间：2014年7月14日 下午7:24:06
 *
 */
public class FileUtil {

	/**
	 * 写入文件
	 * @param path 路径(不包含文件名)
	 * @param fileName 文件名
	 * @param data
	 * @return
	 */
	public static File write(String path,String fileName,String data){
		return write(path+fileName,data);
	}
	
	/**
	 * 写入文件
	 * @param path 文件路径(包含文件名)
	 * @param data
	 * @return
	 */
	public static File write(String path,String data){
		boolean isSuccess = false;
		BufferedWriter out = null;
		File file = new File(path);
		try{
			// 如果不存在，则创建
			if(!file.exists()){
				file.createNewFile();
			}
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			out.write(data);
			out.flush();
			isSuccess = true;
		}catch (IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				if(out != null)
					out.close();
			}catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		return isSuccess ? file : null;
	}
	
	/**
	 * 写入文件
	 * @param file
	 * @param fileName
	 * @param data
	 * @return
	 */
	public static File write(File file,String fileName,String data){
		return write(file.getAbsolutePath(),fileName,data);
	}
	
	/**
	 * 写入文件
	 * @param path 文件路径(包含文件名)
	 * @param data
	 * @return
	 */
	public static File write(String path,byte[] data){
		boolean isSuccess = false;
		FileOutputStream out = null;
		FileChannel fc = null;
		File file = null;
		try {
			file = new File(path);
			out = new FileOutputStream(file);
			fc = out.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(data.length);
			bb.put(data);
			bb.flip();
			fc.write(bb);
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if (null != out) {
					out.close();
				}
				if (null != fc) {
					fc.close();
				}
			}catch(IOException ioex){
				ioex.printStackTrace();
			}
		}
		return isSuccess ? file : null;
	}
	
	/**
	 * 获取路径中的文件名
	 * @param path 文件的完整路径
	 * @return 文件名
	 */
	public static String getFileName(String path){
		if(StringUtil.isNotBlank(path)){
			int index = path.lastIndexOf(File.separatorChar);
			if(index != -1)
				return path.substring(index);
			else
				return path;
		}
		return null;
	}
	
	
	/**
	 * 判断目录是否存在，不存在时创建
	 * @param path 文件夹路径
	 */
	public static void exists(String path){
		if(StringUtil.isBlank(path)){
			throw new NullPointerException("路径不能为空！");
		}
		// 不存在时，创建
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	public static void main(String[] args) {
		String path = "E:/opt/soa/ccb/funds/";
		exists(path);
	}
}
