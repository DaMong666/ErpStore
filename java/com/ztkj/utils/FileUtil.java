package com.ztkj.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * @author yup
 *
 * 2019年7月3日
 */
public class FileUtil {

	/**
	 * 删除文件夹，包含子文件和子目录
	 * @author yup
	 *
	 * @param file
	 * 2019年7月4日
	 */
	public static void deleteFile(File file) {
		if(!file.exists()) {
			return;
		}
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f : files) {
				deleteFile(f);
			}
		}
		file.delete();
	}
	
	/**
	 * 删除文件夹，包含子文件和子目录
	 * @author yup
	 *
	 * @param path
	 * 2019年7月4日
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		deleteFile(file);
	}
	
	/**
	 * 遍历指定文件夹下的所有文件，包含子目录
	 * @author yup
	 *
	 * @param file
	 * @param fList
	 * 2019年7月4日
	 */
	private static void fileList(File file, List<File> fList) {
		if(!file.exists()) {
			return;
		}
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f : files) {
				fileList(f, fList);
			}
		}else {
			fList.add(file);
		}
	}
	
	/**
	 * 遍历指定文件夹下的所有文件，包含子目录
	 * @author yup
	 *
	 * @param file
	 * @return
	 * 2019年7月4日
	 */
	public static List<File> fileList(File file) {
		List<File> fList = new ArrayList<File>();
		fileList(file, fList);
		return fList;
	}
	
	/**
	 * 遍历指定文件夹下的所有文件，包含子目录
	 * @author yup
	 *
	 * @param path
	 * @return
	 * 2019年7月4日
	 */
	public static List<File> fileList(String path) {
		File file = new File(path);
		List<File> fList = new ArrayList<File>();
		fileList(file, fList);
		return fList;
	}
	
	/**
	 * 遍历指定文件夹下的查找指定文件，包含子目录
	 * @author yup
	 *
	 * @param file
	 * @param fList
	 * @param fileName
	 * 2019年7月4日
	 */
	private static void fileList(File file, List<File> fList, String fileName) {
		if(!file.exists()) {
			return;
		}
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f : files) {
				fileList(f, fList, fileName);
			}
		}else {
			if(file.getName().toLowerCase().indexOf(fileName.toLowerCase()) != -1) {
				fList.add(file);
			}
		}
	}
	
	/**
	 * 遍历指定文件夹下的查找指定文件，包含子目录
	 * @author yup
	 *
	 * @param file
	 * @param fileName
	 * @return
	 * 2019年7月4日
	 */
	public static List<File> fileList(File file, String fileName) {
		List<File> fList = new ArrayList<File>();
		fileList(file, fList, fileName);
		return fList;
	}
	
	/**
	 * 遍历指定文件夹下的查找指定文件，包含子目录
	 * @author yup
	 *
	 * @param path
	 * @param fileName
	 * @return
	 * 2019年7月4日
	 */
	public static List<File> fileList(String path, String fileName) {
		File file = new File(path);
		List<File> fList = new ArrayList<File>();
		fileList(file, fList, fileName);
		return fList;
	}
	
	/**
	 * 复制文件
	 * @author yup
	 *
	 * @param srcFile
	 * @param destFile
	 * 2019年7月4日
	 */
	public static void copyFile(File srcFile, File destFile) {
		if(!srcFile.exists()) {
			return;
		}
		InputStream in = null;  //原文件输入流，读取
		OutputStream out = null;  //目标文件输出流，写入
		try {
			 in = new FileInputStream(srcFile);
			 out = new FileOutputStream(destFile);
			 byte[] data = new byte[1024 * 1024];
			 int length = 0;
			 //边读边写
			 while((length = in.read(data, 0, data.length)) != -1) {
				 out.write(data, 0, length);
			 }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 复制文件
	 * @author yup
	 *
	 * @param srcPath
	 * @param destPath
	 * 2019年7月4日
	 */
	public static void copyFile(String srcPath, String destPath) {
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);
		copyFile(srcFile, destFile);
	}
	
	
	/**
	 * 复制文件夹
	 * @author yup
	 *
	 * @param srcFile
	 * @param destFile
	 * 2019年7月4日
	 */
	public static void copyFolder(File srcFile, File destFile) {
		if(!srcFile.exists()) {
			return;
		}
		if(srcFile.isDirectory()) {
			destFile.mkdir();
			File[] files = srcFile.listFiles();  //子文件夹
			for(File f : files) {
				File df = new File(destFile, f.getName());  //根据原文件的文件名，创建目标文件的子文件
				copyFolder(f, df);
			}
		}else {
			copyFile(srcFile, destFile);  //复制文件
		}
		
	}
	
	/**
	 * 复制文件夹
	 * @author yup
	 *
	 * @param srcPath
	 * @param destPath
	 * 2019年7月8日
	 */
	public static void copyFolder(String srcPath, String destPath) {
		File srcFile = new File(srcPath);
		File destFile = new File(destPath);
		copyFolder(srcFile, destFile);
	}
	
	/**
	 * 写入文本
	 * @author yup
	 *
	 * @param file
	 * @param content
	 * @param append
	 * 2019年7月8日
	 */
	private static boolean writeText(File file, String content, boolean append) {
		Writer writer = null;
		try {
			writer = new FileWriter(file, append);
			writer.write(content);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 写入文本
	 * @author yup
	 *
	 * @param file
	 * @param content
	 * 2019年7月8日
	 */
	public static boolean writeText(File file, String content) {
		return writeText(file, content, false);
	}
	
	/**
	 * 写入文本
	 * @author yup
	 *
	 * @param path
	 * @param content
	 * 2019年7月8日
	 */
	public static boolean writeText(String path, String content) {
		File file = new File(path);
		return writeText(file, content, false);
	}
	
	/**
	 * 追加文本
	 * @author yup
	 *
	 * @param file
	 * @param content
	 * @return
	 * 2019年7月8日
	 */
	public static boolean appendText(File file, String content) {
		return writeText(file, content, true);
	}
	
	/**
	 * 追加文本
	 * @author yup
	 *
	 * @param path
	 * @param content
	 * @return
	 * 2019年7月8日
	 */
	public static boolean appendText(String path, String content) {
		File file = new File(path);
		return writeText(file, content, true);
	}
	
	/**
	 * 读取文本
	 * @author yup
	 *
	 * @param file
	 * @return
	 * 2019年7月8日
	 */
	public static String readText(File file) {
		StringBuffer content = new StringBuffer();
		Reader reader = null;
		try {
			reader = new FileReader(file);
			char[] data = new char[1024];
			int length = 0;
			while((length = reader.read(data)) != -1) {
				content.append(new String(data, 0, length));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content.toString();
	}
	
	/**
	 * 读取文本
	 * @author yup
	 *
	 * @param path
	 * @return
	 * 2019年7月8日
	 */
	public static String readText(String path) {
		File file = new File(path);
		return readText(file);
	}

	/**
	 * 根据文件名获取文件后缀名
	 * @param fileName
	 * @return
	 */
	public  static String getFileSuffix(String fileName){
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	public static void main(String[] args) {
		File file = new File("E:\\a.txt");
		boolean b =appendText(file, "我是追加的内容\r\n");
		System.out.println("结果：" + b);
		
//		String content = readText(file);
//		System.out.println(content);
	}
}
