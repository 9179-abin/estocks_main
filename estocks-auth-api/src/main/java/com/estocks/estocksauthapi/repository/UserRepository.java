package com.estocks.estocksauthapi.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private List<User> userList;

	public UserRepository() {
		super();
		List<User> userList = new ArrayList<User>();
		userList.add(new User("estox_admin", "estox_@d", "ADMIN"));
		userList.add(new User("estox_user", "estox_u$", "USER"));
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public User findByUsername(String username) {
		for (User user: this.userList) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

}
