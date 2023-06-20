package com.practice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HandlingController 
{
	@Autowired
	MainDao dao;
	@RequestMapping("/viewlogin")
	public String doLogin()
	{
		return "LoginForm";
	}
	@RequestMapping("/Signup")
	public String doRegister()
	{
		return "RegisterForm";
	}
	@RequestMapping(value="/savereg",method=RequestMethod.POST)
	public String getSignUp(@ModelAttribute("rm")RegisterModel rm,HttpSession session)
	{
		int s=dao.getSignUp(rm);
		session.setAttribute("sesreg", "success");
		return "redirect:/dummy";
    }
	@RequestMapping("dummy")
	public String getIndex()
	{
		return "LoginForm";
	}
	
	@RequestMapping(value="/savelogin", method=RequestMethod.POST)
	public String getLogin(@ModelAttribute("log") LoginModel log, HttpSession session)
	{
		List<RegisterModel> list = dao.getLogin(log);
		if(list!=null)
		{
			session.setAttribute("seslog1", log.getEmail());
			return "LoginForm";
		}
		else
		{
			session.setAttribute("seslog", "failed");
			return "dashboard";
		}
		//return null;
	}
	
	@RequestMapping("/viewdummyrecord")
	public String getDashboard()
	{
		return "dashboard";
	}

}
