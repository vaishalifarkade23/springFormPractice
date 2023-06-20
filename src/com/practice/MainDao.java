package com.practice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

//import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MainDao
{
	//private DriverManagerDataSource;
	private JdbcTemplate jdbctemplet;

	public JdbcTemplate getJdbctemplet() {
		return jdbctemplet;
	}

	public void setJdbctemplet(JdbcTemplate jdbctemplet) {
		this.jdbctemplet = jdbctemplet;
	}

	
	public int getSignUp(RegisterModel rm) {
		// TODO Auto-generated method stub
		String sql="insert into practice (first,last,email,gender,city,country,password) values('"+rm.getFname()+"','"+rm.getLname()+"','"+rm.getEmail()+"','"+rm.getGender()+"','"+rm.getCity()+"','"+rm.getCountry()+"','"+rm.getPassword()+"')";
		return jdbctemplet.update(sql);
	}

	public List<RegisterModel> getLogin(LoginModel log) {
		// TODO Auto-generated method stub
		String sql="select * from practice where email='"+log.getEmail()+"' and password='"+log.getPassword()+"'";
		List<RegisterModel> list=jdbctemplet.query(sql, new RowMapper<RegisterModel>() {

			@Override
			public RegisterModel mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				RegisterModel rm = new RegisterModel();
				log.setEmail(rs.getString("email"));
				log.setPassword("password");
				return rm;
			}
			
		});
		List<RegisterModel> list1=list.size()>0?list:null;
		return list1;
	}
	
}
