package com.charles.common.constants.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtil {
	
	private String host = "";
	
	private int port = 0;
	
	private String username = "";
	
	private String password = "";
	
	public FtpUtil() {
		
	}
	
	public FtpUtil(String host, int port ,String username, String password) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * 向FTP服务器上传文件
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param path			FTP服务器保存目录
	 * @param filePath		本地文件路径
	 * @param encoding		文件字符编码
	 * @return
	 * @throws Exception
	 */
	public static boolean uploadFile(String host, int port, String username, String password, String path,
			String filePath, String encoding) throws Exception {
		File file = new File(filePath);
		String filename = file.getName();
		InputStream is = new FileInputStream(file);
		
		return uploadFile(host, port, username, password, path, filePath, encoding);
	}
	/**
	 * 向ftp服务器上传文件
	 * @param host			
	 * @param port
	 * @param username
	 * @param password
	 * @param path			FTP服务器保存目录
	 * @param filename		上传到FTP服务器上的文件名
	 * @param input			输入流
	 * @param encoding		文件字符编码
	 * @return
	 */
	public static boolean uploadFile(String host, int port, String username, String password, String path,
									String filename, InputStream input, String encoding) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		ftp.setRemoteVerificationEnabled(false);
		try{
			ftp.setConnectTimeout(10*1000);
			ftp.connect(host, port);			//连接ftp服务器
			boolean flag = ftp.login(username, password);		//登录
			if(!flag) {
				throw new IllegalStateException("FTP登录失败，用户名：" + username + "，密码：" + password);
			}
			int reply = ftp.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			//切换目录
			if(StringUtils.isNotBlank(path)) {
				ftp.changeWorkingDirectory(path);
			}
			//设置文件传输类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//设置编码
			ftp.setControlEncoding(encoding);
			ftp.setDataTimeout(30*1000);
			success = ftp.storeFile(filename, input);
			input.close();
			ftp.logout();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(ftp.isConnected()) {
				try {
					input.close();
					ftp.disconnect();
				} catch(Exception e) {
					
				}
			}
		}
		return success;
	}
	
	/**
	 * 下载文件
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param remoteDir
	 * @param localDir
	 * @param encoding	
	 * @param fileFilter		文件名匹配条件，包含该值的文件名都符合条件
	 * @return
	 */
	public static boolean downloadFile(String host, int port, String username, String password, String remoteDir, String localDir,
			String encoding, final String fileFilter) {
		boolean success = false;
		FTPClient ftpClient = new FTPClient();
		try{
			ftpClient.setControlEncoding(encoding);
			ftpClient.setConnectTimeout(10*1000);
			ftpClient.connect(host, port);					//连接FTP服务器
			ftpClient.login(username, password);			//登录
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);	//设置文件传输类型
			int reply = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				return success;
			}
			//由于apache不支持中文语言环境，通过定制类解析中文日期类型  
            ftpClient.enterLocalPassiveMode();   
            ftpClient.configure(new FTPClientConfig("com.zznode.tnms.ra.c11n.nj.resource.ftp.UnixFTPEntryParser"));  
            //切换目录
            if (StringUtils.isNotBlank(remoteDir)) {
            	ftpClient.changeWorkingDirectory(remoteDir);  
			}
			
            ftpClient.setDataTimeout(30*1000);
			FTPFile[] ftpFiles = ftpClient.listFiles(null, new FTPFileFilter(){

				public boolean accept(FTPFile file) {
					if(file.isFile()) {
						return file.getName().contains(fileFilter);
					}
					return false;
				}});
			if(ftpFiles == null || ftpFiles.length == 0) {
				throw new IllegalStateException("没有匹配的文件可下载");
			}
			
			for(FTPFile file : ftpFiles) {
				if(!localDir.endsWith("/")) {
					localDir = localDir + "/";
				}
				File localFile = new File(localDir + file.getName());
				OutputStream os = new FileOutputStream(localFile);
				success = ftpClient.retrieveFile(file.getName(), os);
				os.flush();
				os.close();
			}
			ftpClient.logout();
			
		} catch(Exception e) {
		} finally {
			if(ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch(Exception e) {
					
				}
			}
		}
		return success;
	}
}
