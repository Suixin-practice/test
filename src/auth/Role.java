package auth;
/**
 * 角色类
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
	private String name;//显示名称,给用户看的
	private String key;//对应权限配置文件中的权限key
	private List<String> permisstions;//权限集合
	private BookInfoBiz bookInfoBiz;
	private UserBiz userBiz;
	
	public Role() {
		setName("默认角色");
		setKey("default");
		createPermisstions(key);
//		bookInfoBiz = new BookInfoBizImplV1();
		//工厂模式
		bookInfoBiz = (BookInfoBiz)BizFactory.getBiz("BookInfoBiz");
		userBiz = (UserBiz)BizFactory.getBiz("userBiz");
	}
	/**
	 * 创建一个给定角色名和权限key的角色对象
	 * 权限key对应一个字符串,
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
		//读取配置文件
		Properties props = new Properties();
		try {
			props.load(Role.class.getResourceAsStream("Role_premisstions.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//通过权限拿到对应的权限的字符串
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
	 * 验证当前角色是否有权限执行传入的操作
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
		throw new NoSuchOptPermissionException(name+"没有操作"+"<userbiz.login>的权限");
	}
	/**
	 * 角色的入库方法,调用真正的图书业务类进行入库
	 * @param isbn
	 * @param inCount
	 * @return
	 */
	public boolean inStore(String isbn, int inCount) {
		//验证当前的角色有没有权限进行入库操作
		if(checkPermisstion("bookInfoBiz.inStore")) {
			
			return bookInfoBiz.inStore(isbn, inCount);
		}else {
			//抛出异常,或打印异常信息
			throw new NoSuchOptPermissionException(name+"没有操作"+"<bookInfoBiz.inStore>的权限");
			
		}
	}
	public boolean outStore(String isbn,int outCount) {
		//验证当前的角色有没有权限进行出库操作
		if(checkPermisstion("bookInfoBiz.outStore")) {
			return bookInfoBiz.outStore(isbn, outCount);
		}else {
			//抛出异常,或打印异常信息
			throw new NoSuchOptPermissionException(name+"没有操作"+"<bookInfoBiz.outStore>的权限");
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
