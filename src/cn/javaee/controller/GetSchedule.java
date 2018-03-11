package cn.javaee.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.javaee.model.Customer;
import cn.javaee.model.CustomerList;
import net.sf.json.JSONArray;

/**
 * 
 * @see   获取用户的所有事宜
 * @author wangbin
 * @time 2017年11月18日 下午2:49:29
 */
@WebServlet("/GetSchedule")
public class GetSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSchedule() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("username")){
					//获取当前用户的用户名
					String temp = cookies[i].getValue();
					String username = URLDecoder.decode(temp, "UTF-8");
					CustomerList customerList = new CustomerList();
					//通过用户名获取当前用户
					Customer customer = customerList.getCustomerByName(username);
					if(customer != null){
						//将当前用户的所有事宜转换成JSON数据
						JSONArray schedule = JSONArray.fromObject(customer.getSchedule().getSchedule());
						response.setCharacterEncoding("UTF-8");
						response.getWriter().print(schedule);
					}
				}
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

}
