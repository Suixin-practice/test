package biz.impl;

import java.util.Map;
import java.util.Set;

import Entity.User;
import Util.FileUtil;
import biz.UserBiz;

public class UserBizImplV1 implements UserBiz{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3374627706677666717L;

	@Override
	public boolean add(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean del(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User user) {
		Set<User> userSet = FileUtil.ReadUser();
		if(null == userSet||userSet.size()==0) return null;
		for(User temp:userSet) {
			return temp;
		}
		return null;
	}
	
}
