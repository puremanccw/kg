package com.charles.common.constants.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	
	/**
	 * 压缩文件
	 * @param out			压缩后的zip文件
	 * @param path			文件路径
	 * @param srcFiles		源文件
	 */
	public static void ZipFiles(ZipOutputStream out, String path, File... srcFiles) {
		path = path.replace("\\*", "/");
		if(!path.endsWith("/")) {
			path += "/";
		}
		byte[] buf = new byte[1024];
		try{
			for(int i = 0; i < srcFiles.length; i++) {
				if(srcFiles[i].isDirectory()) {
					File[] file = srcFiles[i].listFiles();
					String srcPath = srcFiles[i].getName();
					srcPath = srcPath.replace("\\*", "/");
					if(!srcPath.endsWith("/")) {
						srcPath += "/";
					}
					out.putNextEntry(new ZipEntry(srcPath));
					ZipFiles(out, path + srcPath, file);
				} else {
					FileInputStream in = new FileInputStream(srcFiles[i]);
					System.out.println(path + srcFiles[i].getName());
					out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
					int len;
					while((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					out.closeEntry();
					in.close();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解压文件到指定目录
	 * @param zipFile
	 * @param descDir
	 * @throws  
	 * @throws Exception 
	 */
	
	public static void unZipFiles(String zipFilePath, String descDir) throws Exception {
		File pathFile = new File(descDir);
		if(!pathFile.exists()) {
			pathFile.mkdirs();
		}
		try{
			ZipInputStream Zin=new ZipInputStream(new FileInputStream(zipFilePath));
			BufferedInputStream Bin=new BufferedInputStream(Zin);  
			File Fout=null;  
            ZipEntry entry; 
            while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
                Fout=new File(pathFile, entry.getName());  
                if(!Fout.exists()){  
                    (new File(Fout.getParent())).mkdirs();  
                }  
                FileOutputStream out=new FileOutputStream(Fout);  
                BufferedOutputStream Bout=new BufferedOutputStream(out);  
                int b;  
                while((b=Bin.read())!=-1){  
                    Bout.write(b);  
                }  
                Bout.close();  
                out.close();  
                System.out.println(Fout+"解压成功");      
            }  
            Bin.close();  
            Zin.close();  
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}
