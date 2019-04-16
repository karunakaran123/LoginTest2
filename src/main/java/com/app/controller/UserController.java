package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.model.User;
import com.app.model.UserRowMapper;

@Controller
public class UserController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	public String getlogin() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String getLogin() {
		return "index";
	}
	@PostMapping(value="/getUser",produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public String getUser(@RequestBody User user,Model model) {

		 String sql="select * from user where userName=?"; 
		  User userfromdb=new User();
		  try
		  {
		   userfromdb=jdbcTemplate.queryForObject(sql,new UserRowMapper(),user.getUserName()); 
		  } 
		  catch (Exception e) 
		  {
		   model.addAttribute("user",new User()); 
		   return "false"; 
		  }
		  
		  if(user.getUserName().equals(userfromdb.getUserName())&&user.getUserPwd().equals(userfromdb.getUserPwd()))
		  {
		    model.addAttribute("user",new User());
		    return "true";
		  } 
		  else 
		   {
			 model.addAttribute("user",new User()); 
	         return "false"; 
			   
		   }

	}
	@RequestMapping("/register")
	public String getRegPage(@ModelAttribute User user,Model model)
	{
		model.addAttribute("user",new User());
		return "reg";

	}
	@RequestMapping("/saveUser")
	public String saveUser(@ModelAttribute User user,Model model) {
		UserRowMapper rowmapper=new UserRowMapper();
		
		// ===inserting Data from UI===

		String sql="insert into user values(?,?,?)";
		jdbcTemplate.update(sql,user.getUserName(),user.getUserPwd(),user.getUserEmail()); 
		
		String msg="User "+user.getUserName()+"  Registered successfully !";
		model.addAttribute("msg",msg);
		return "reg";


	}

}
