package com.app.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user=new User();
		user.setUserName(rs.getString("userName"));
		user.setUserEmail(rs.getString("userEmail"));
		user.setUserPwd(rs.getString("userPwd"));
		return user;
	}

}
