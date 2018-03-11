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

/**
 * 
 * @see    删除一条事宜
 * @author wangbin
 * @time 2017年11月18日 下午2:45:58
 */
@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItem() {
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
		String id =request.getParameter("id");
		//获取所有的用户
		CustomerList customerList = new CustomerList();
		if(username != null){
			//通过用户的用户名获取当前用户
			Customer customer = customerList.getCustomerByName(username);
			//将想要删除的事宜从该用户的事宜列表中删去
			//若删除成功,则返回"success"
			if(customer.getSchedule().deleteScheduleItem(id)){
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
