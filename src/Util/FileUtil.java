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
 * �ļ�������
 * @author 40623
 *
 */
public class FileUtil {
	//Ϊ�˲������㣬��ͼ����Ϣ�Զ������ķ�ʽ������ļ���
	private static final String BookInfoFilePath = "BookInfo.dat";//���·��
	private static final String UserFilePath = "UserIfon.dat";
	
	
	public static void SaveUser(Set<User> userSet) {
		SaveObject(userSet, UserFilePath);
	}
	
	public  static Set<User> ReadUser(){
		return (Set<User>)ReadObject(UserFilePath);
	}
	/**
	 * �������BookInfo��map��Ϣ����ڶ�Ӧ���ļ���   ��д���ݽṹ��Ӧ
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
	 * ͨ�õı����ļ��ķ���
	 * @param object Ҫд������л�����
	 * @param filePath �ļ�·��
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
	 * ���ļ��ж�ȡ����
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
