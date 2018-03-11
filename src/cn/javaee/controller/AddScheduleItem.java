package cn.javaee.controller;

import java.io.BufferedReader;
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
import cn.javaee.model.ScheduleItem;
import net.sf.json.JSONArray;

/**
 * 
 * @see    用于添加一条事宜
 * @author wangbin
 * @time 2017年11月18日 下午2:39:04
 */
@WebServlet("/AddScheduleItem")
public class AddScheduleItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddScheduleItem() {
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
		String id = String.valueOf(System.currentTimeMillis());
		String title = request.getParameter("content");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String remindTime = request.getParameter("remindTime");
		String color = "yellow";
		//将用户设置的事宜转换成一个相应的ScheduleItem类
		ScheduleItem newScheduleItem = new ScheduleItem(id,title,start,end,color,remindTime);
		CustomerList customerList = new CustomerList();
		if(username != null){
			//通过用户名获取当前用户
			Customer customer = customerList.getCustomerByName(username);
			String customerId = customer.getUserId();
			newScheduleItem.setCustomerId(customerId);
			//如果成功添加了事宜，则返回success
			if(customer.getSchedule().addScheduleItem(newScheduleItem)){
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
	/**
	 * 
	 * @see    获取当前登录的用户的用户名
	 * @author wangbin
	 * @time 2017年11月18日 下午2:39:48
	 * @param request
	 * @param response
	 * @return
	 */
	public String getUserName(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		String username = null;
		if(cookies != null){
			//遍历所有的Cookie,如果用户已经登录，则返回该用户的用户名
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
