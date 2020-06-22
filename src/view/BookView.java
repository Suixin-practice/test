package view;

import java.util.Scanner;

import Entity.User;
import auth.Role;
	
public class BookView {
	private Scanner input = null;
	private User loginUser = null;//��ǰ��½�Ķ���
	private Role role = null;//��ɫ
	
	public BookView() {
		input = new Scanner(System.in);
		showWelcome();
	}
	/**
	 * ��ӭ����
	 */
	public void showWelcome() {
		System.out.println("------------��ӭʹ��ͼ�����ϵͳ");
		System.out.println("\t\t1,��½ϵͳ\t\t2,ע��\t\t3,�˳�ϵͳ");
		System.out.println("��ѡ��:");
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
			System.out.println("ϵͳ�˳�");
		}
	}
	public User showLoginView() {
		User loginUser = new User();
		System.out.println("�û���:");
		loginUser.setUserName(input.next());
		System.out.println("����");
		loginUser.setPassWord(input.next());
		//��֤�û�
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
	 * ����Ա����
	 */
	public void showMainView_Administrator() {
		System.out.println("����Ա�˵�");
	}
	/**
	 * ����Ա����
	 */
	public void showMainView_Oparator() {
		
	}
}
