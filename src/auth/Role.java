package auth;
/**
 * ��ɫ��
 * @author 40623
 */

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Entity.User;
import biz.BizFactory;
import biz.BookInfoBiz;
import biz.UserBiz;
import exception.NoSuchOptPermissionException;

public class Role implements Serializable{
	private static final long serialVersionUID = 409377348183964334L;
	private String name;//��ʾ����,���û�����
	private String key;//��ӦȨ�������ļ��е�Ȩ��key
	private List<String> permisstions;//Ȩ�޼���
	private BookInfoBiz bookInfoBiz;
	private UserBiz userBiz;
	
	public Role() {
		setName("Ĭ�Ͻ�ɫ");
		setKey("default");
		createPermisstions(key);
//		bookInfoBiz = new BookInfoBizImplV1();
		//����ģʽ
		bookInfoBiz = (BookInfoBiz)BizFactory.getBiz("BookInfoBiz");
		userBiz = (UserBiz)BizFactory.getBiz("userBiz");
	}
	/**
	 * ����һ��������ɫ����Ȩ��key�Ľ�ɫ����
	 * Ȩ��key��Ӧһ���ַ���,
	 * @param name
	 * @param key
	 */
	public Role(String name,String key) {
		setName(name);
		setKey(key);
		createPermisstions(key);
		bookInfoBiz = (BookInfoBiz)BizFactory.getBiz("BookInfoBiz");
	}
	
	
	private void createPermisstions(String key) {
		//��ȡ�����ļ�
		Properties props = new Properties();
		try {
			props.load(Role.class.getResourceAsStream("Role_premisstions.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ͨ��Ȩ���õ���Ӧ��Ȩ�޵��ַ���
		String strPermisstion = props.getProperty(key);
		if(null==permisstions) permisstions = new ArrayList<String>();
		permisstions.clear();
//		permisstions.addAll(Arrays.asList(strPermisstion.split(",")));
		String[] perminsstionArray = strPermisstion.split(",");
		for(String permisstion:perminsstionArray) {
			if("".equals(permisstion)) continue;
			permisstions.add(permisstion.trim());
		}
	}

	/**
	 * ��֤��ǰ��ɫ�Ƿ���Ȩ��ִ�д���Ĳ���
	 * @param optName
	 * @return
	 */
	
	private boolean checkPermisstion(String optName) {
		if(null==permisstions || permisstions.size()==0) {
			return false;
		}
		for(String perminsstion :permisstions) {
			if(optName.equals(perminsstion)) {
				return true;
			}
			if(perminsstion.equals("bookInfoBiz.*") &&optName.startsWith("bookInfoBiz")) {
				return true;
			}
			if(perminsstion.equalsIgnoreCase("userbiz.*")&&optName.startsWith("userbiz")) {
				return true;
			}
		}
		return false;
	}
	public User login(User user) {
		if(checkPermisstion("userbiz.login")) {
			return userBiz.login(user);
		}
		throw new NoSuchOptPermissionException(name+"û�в���"+"<userbiz.login>��Ȩ��");
	}
	/**
	 * ��ɫ����ⷽ��,����������ͼ��ҵ����������
	 * @param isbn
	 * @param inCount
	 * @return
	 */
	public boolean inStore(String isbn, int inCount) {
		//��֤��ǰ�Ľ�ɫ��û��Ȩ�޽���������
		if(checkPermisstion("bookInfoBiz.inStore")) {
			
			return bookInfoBiz.inStore(isbn, inCount);
		}else {
			//�׳��쳣,���ӡ�쳣��Ϣ
			throw new NoSuchOptPermissionException(name+"û�в���"+"<bookInfoBiz.inStore>��Ȩ��");
			
		}
	}
	public boolean outStore(String isbn,int outCount) {
		//��֤��ǰ�Ľ�ɫ��û��Ȩ�޽��г������
		if(checkPermisstion("bookInfoBiz.outStore")) {
			return bookInfoBiz.outStore(isbn, outCount);
		}else {
			//�׳��쳣,���ӡ�쳣��Ϣ
			throw new NoSuchOptPermissionException(name+"û�в���"+"<bookInfoBiz.outStore>��Ȩ��");
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<String> getPermisstions() {
		return permisstions;
	}
	public void setPermisstions(List<String> permisstions) {
		this.permisstions = permisstions;
	}
	public BookInfoBiz getBookInfoBiz() {
		return bookInfoBiz;
	}
	public void setBookInfoBiz(BookInfoBiz bookInfoBiz) {
		this.bookInfoBiz = bookInfoBiz;
	}
	
}
