package com.charles.common.constants.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtil {
	
	ChannelSftp sftp = null;
	
	private String host = "";
	
	private int port = 0;
	
	private String username = "";
	
	private String password = "";
	
	/**
	 * 构造函数
	 * @param host
	 * @param post
	 * @param username
	 * @param password
	 */
	public SftpUtil(String host, int post, String username, String password) {
		this.host = host;
		this.port = post;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * 链接sftp服务器
	 * @throws Exception
	 */
	public void connect() throws Exception {
		JSch jsch = new JSch();
		Session sftpSession = jsch.getSession(this.username, this.host, this.port);
		sftpSession.setPassword(password);
		Properties sftpConfig = new Properties();
		sftpConfig.put("StrictHostKeyChecking", "no");
		sftpSession.setConfig(sftpConfig);
		sftpSession.connect(20000);
		
		Channel channel = sftpSession.openChannel("sftp");
		channel.connect();
		this.sftp = (ChannelSftp)channel;
	}
	
	/**
	 * 关闭连接
	 */
	public void disconnect() {
		if(this.sftp != null) {
			if(this.sftp.isConnected()) {
				this.sftp.disconnect();
			} else if(this.sftp.isClosed()) {
				
			}
		}
	}
	
	/**
	 * 上传单个文件
	 * @param directory		上传的目录
	 * @param uploadFile	要上传的文件
	 * @throws Exception
	 */
	public void upload(String directory, String uploadFile) throws Exception {
		cd(directory);
		File file = new File(uploadFile);
		this.sftp.put(new FileInputStream(file), file.getName());
	}
	
	/**
	 * 上传目录下全部文件
	 * @param directory		上传目录
	 * @throws Exception
	 */
	public void uploadByDirectory(String directory) throws Exception {
		String uploadFile = "";
		List<String> uploadFileList = this.listFiles(directory);
		Iterator<String> it = uploadFileList.iterator();
		
		while(it.hasNext()) {
			uploadFile = it.next().toString();
			this.upload(directory, uploadFile);
		}
	}
	
	/**
	 * 下载单个文件
	 * @param directory			下载目录
	 * @param downloadFile		下载的文件
	 * @param saveDirectory		存在本地的路径
	 * @throws Exception
	 */
	public void download(String directory, String downloadFile, String saveDirectory) throws Exception {
		String saveFile = saveDirectory + File.separator + downloadFile;
		cd(directory);
		File file = new File(saveFile);
		this.sftp.get(downloadFile, new FileOutputStream(file));
	}
	
	/**
	 * 下载目录下全部文件
	 * @param directory			下载目录
	 * @param saveDirectory		存在本地的路径
	 * @throws Exception
	 */
	public void downloadByDirectory(String directory, String saveDirectory) throws Exception {
		String downloadFile = "";
		List<String> downloadFileList = this.listFiles(directory);
		Iterator<String> it = downloadFileList.iterator();
		
		while(it.hasNext()) {
			downloadFile = it.next().toString();
			if(downloadFile.toString().indexOf(".") < 0) {
				continue;
			}
			this.download(directory, downloadFile, saveDirectory);
		}
	}
	
	/**
	 * 删除文件
	 * @param directory
	 * @param deleteFile
	 * @throws Exception
	 */
	public void delete(String directory, String deleteFile) throws Exception {
		cd(directory);
		this.sftp.rm(deleteFile);
	}
	
	/**
	 * 列出目录下的文件
	 * @param directory		要列出的目录
	 * @return	list		文件名列表
	 * @throws Exception
	 */
	private List<String> listFiles(String directory) throws Exception {
		List<String> fileNameList = new ArrayList<String>();
		
		Vector fileList = this.sftp.ls(directory);
		
		Iterator it = fileList.iterator();
		
		while(it.hasNext()) {
			String fileName = ((LsEntry)it.next()).getFilename();
			if(".".equals(fileName) || "..".equals(fileName)) {
				continue;
			}
			fileNameList.add(fileName);
		}
		return fileNameList;
	}

	/**
	 * 更新文件名
	 * @param directory		文件所在目录
	 * @param oldFileName	原文件名
	 * @param newFileName	新文件名
	 * @throws Exception
	 */
	public void rename(String directory, String oldFileName, String newFileName) throws Exception {
		cd(directory);
		this.sftp.rename(oldFileName, newFileName);
	}
	
	public void cd(String directory) throws Exception {
		if(directory != null && directory.trim().equals("")) {
			this.sftp.cd(directory);
		}
	}
	
	public InputStream get(String directory) throws Exception {
		InputStream stream = this.sftp.get(directory);
		return stream;
	}
}
