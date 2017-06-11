package com.yincongyang.utils.io;

import java.io.File;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

/**
 * 文件工具类
 * @author CodmerYin
 *
 */
public class FileUtils {
	private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	private final static String PREFIX_0 = "阿巴西普Ⅲ期-";
	private final static String PREFIX_1 = "阿巴西普III期";
	
	private final static String FILE_EXTENSION = "pdf";
	
	public static int count = 0;
	
	public static void batchFileRename(String path){
		 
		
		File folder = new File(path);
		
		String dirPath = folder.getAbsolutePath();//目录路径
		
		if(folder.isDirectory()){
			File[] files = folder.listFiles();//获取此目录下的文件列表
			for (File file : files) { 
				//判断子文件夹是否是目录
				if(file.isDirectory()){
					batchFileRename(file.getAbsolutePath());
				}else{
					//获取文件扩展名称
					String file_extension = Files.getFileExtension(file.getName());
					//判断文件后缀是否相同
					if(StringUtils.equals(file_extension, FILE_EXTENSION)){
						String fileName = file.getName();
						//检测文件前缀是否已经相同
						if (!fileName.startsWith(PREFIX_0) && !fileName.startsWith(PREFIX_1)){
							count++;
							logger.info("原文件名为：{}",fileName);
							File destFile = new File(dirPath + "\\" + PREFIX_0 + fileName);
							file.renameTo(destFile);
							logger.info("修改后文件名为：{}",destFile.getName());
						}
					}
				}
            } 
		}else{
			logger.error("根路径不是目录！");
		};
		
	}
	
	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
		FileUtils.batchFileRename("C:\\Users\\CodmerYin\\Desktop\\阿巴西普Ⅲ期");
		long endtime = System.currentTimeMillis();
		logger.info("修改文件数量为：{}",FileUtils.count);
		logger.info("共耗时：{}毫秒",new Long(endtime - starttime));
	}
}
