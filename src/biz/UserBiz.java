package biz;

import Entity.User;

public interface UserBiz extends Biz<User>{
	public User login(User user);
}
