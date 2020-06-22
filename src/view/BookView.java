package view;

import java.util.Scanner;

import Entity.User;
import auth.Role;
	
public class BookView {
	private Scanner input = null;
	private User loginUser = null;//当前登陆的对象
	private Role role = null;//角色
	
	public BookView() {
		input = new Scanner(System.in);
		showWelcome();
	}
	/**
	 * 欢迎界面
	 */
	public void showWelcome() {
		System.out.println("------------欢迎使用图书管理系统");
		System.out.println("\t\t1,登陆系统\t\t2,注册\t\t3,退出系统");
		System.out.println("请选择:");
		String choice = input.next();
		if("1".equals(choice)) {
			showLoginView();
			switch (this.role.getKey()) {
			case "administrator":
				showMainView_Administrator();
				break;
			case "oparator":
				showMainView_Oparator();
			default:
				break;
			}
		}else if("2".equals(choice)) {
			
		}else {
			System.out.println("系统退出");
		}
	}
	public User showLoginView() {
		User loginUser = new User();
		System.out.println("用户名:");
		loginUser.setUserName(input.next());
		System.out.println("密码");
		loginUser.setPassWord(input.next());
		//验证用户
//		loginUser.getRole().login(loginUser)
		this.loginUser = loginUser.getRole().login(loginUser);
		if(this.loginUser==null) {
			return null;
		}else {
			this.role = this.loginUser.getRole();
			return this.loginUser;
		}
	}
	/**
	 * 管理员界面
	 */
	public void showMainView_Administrator() {
		System.out.println("管理员菜单");
	}
	/**
	 * 操作员界面
	 */
	public void showMainView_Oparator() {
		
	}
}
