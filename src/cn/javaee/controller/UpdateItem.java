package cn.javaee.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.javaee.model.Customer;
import cn.javaee.model.CustomerList;
import cn.javaee.model.Schedule;
import cn.javaee.model.ScheduleItem;

/**
 * 
 * @see    修改事宜
 * @author wangbin
 * @time 2017年11月18日 下午3:13:15
 */
@WebServlet("/UpdateItem")
public class UpdateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取当前登录的用户的用户名
		String username = getUserName(request,response);
		//用户用户修改后的事宜的信息
		String id = request.getParameter("id");
		String title = request.getParameter("content");
		String start = request.getParameter("start");
		long newstart = Long.parseLong(start)-60*60*24;
		String end = request.getParameter("end");
		long newend = Long.parseLong(end)-60*60*24;
		String remindTime = request.getParameter("remindTime");
		
		CustomerList customerList = new CustomerList();
		if(username != null){
			//通过用户名获取用户
			Customer customer = customerList.getCustomerByName(username);
			//修改该用户想要修改的事宜
			Schedule schedule = customer.getSchedule();
			if(schedule.modifyScheduleItem(id, title, String.valueOf(newstart), String.valueOf(newend), remindTime)){
				response.getWriter().print("success");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public String getUserName(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		String username = null;
		if(cookies != null){
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("username")){
					String temp = cookies[i].getValue();
					
					try {
						username = URLDecoder.decode(temp, "UTF-8");
						return username;
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		return username;
	}
}
