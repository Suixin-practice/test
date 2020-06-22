package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;

import Entity.BookInfo;
import Entity.User;

/**
 * 文件工具类
 * @author 40623
 *
 */
public class FileUtil {
	//为了操作方便，把图书信息以对象流的方式存放在文件中
	private static final String BookInfoFilePath = "BookInfo.dat";//相对路径
	private static final String UserFilePath = "UserIfon.dat";
	
	
	public static void SaveUser(Set<User> userSet) {
		SaveObject(userSet, UserFilePath);
	}
	
	public  static Set<User> ReadUser(){
		return (Set<User>)ReadObject(UserFilePath);
	}
	/**
	 * 将传入的BookInfo的map信息存放在对应的文件中   读写数据结构对应
	 * @param bookIfMap
	 */
	public static void SaveBookInfoMap(Map<String, BookInfo> bookIfMap) {
		SaveObject(bookIfMap, BookInfoFilePath);
	}
	public static Map<String, BookInfo> readBookInfoMap(){
		Object obj = ReadObject(BookInfoFilePath);
		if(null==obj) return null;
		return (Map<String, BookInfo>)obj;
	}
	
	
	
	/**
	 * 通用的保存文件的方法
	 * @param object 要写入的序列化对象
	 * @param filePath 文件路径
	 */
	public static void SaveObject(Object object,String filePath) {
		try(
				FileOutputStream fout = new FileOutputStream(filePath,false);
				ObjectOutputStream out = new ObjectOutputStream(fout);
				) {
			out.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 从文件中读取对象
	 * @param filePath
	 * @return
	 */
	public static Object ReadObject(String filePath) {
		try (
				FileInputStream fin = new FileInputStream(filePath);
				ObjectInputStream in = new ObjectInputStream(fin);
				){
			return in.readObject();
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
