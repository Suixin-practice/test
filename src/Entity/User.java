package Entity;

import java.io.Serializable;
import java.util.Date;

import auth.Role;

/**
 * 用户对象
 * @author 40623
 *
 */
public class User  implements Serializable{
	private static final long serialVersionUID = 3108159313930825301L;
	private String userName;
	private String passWord;
	private Role role;
	private Date lastedLoginTime;
	
	
	public User() {
		role = new Role();//默认对象
	}
	public User(Role role) {
		if(null==role) {
			role = new Role();
		}
		setRole(role);
	}
	@Override
		public boolean equals(Object obj) {
			if(null==obj) return false;
			if(!(obj instanceof User)) return false;
			User user = (User)obj;
			return userName.equals(user.getUserName())&&passWord.equals(user.getPassWord());
		}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Date getLastedLoginTime() {
		return lastedLoginTime;
	}
	public void setLastedLoginTime(Date lastedLoginTime) {
		this.lastedLoginTime = lastedLoginTime;
	}
	
}
