package cn.javaee.controller;

import java.io.IOException;
import java.net.URLEncoder;

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
 * @see    验证用户登录
 * @author wangbin
 * @time 2017年11月18日 下午3:02:31
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("GBK");
        //获取表单提交的三个属性
		//分别是 ”用户名“，”密码“，”记住账号"
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberusername =request.getParameter("rememberusername");
		//将获取的用户名和密码通过 new Customer(String username,String password)构造方法转换为一个Customer类
		Customer customer = new Customer(username,password);
		if(username!= null && password!=null){
			CustomerList customerList = new CustomerList();
			//验证用户提交的用户是否存在
			//通过判断是否能通过用户提交的用户名从用户列表中获取一个非空的Customer来判断该用户是否存在
			Customer curCustomer = customerList.getCustomerByName(username);
			//若不存在，则返回“incorrect"
			if(curCustomer == null){
				response.getWriter().print("incorrect");
			}else{
				//若存在该用户，则验证密码是否正确
				if(curCustomer.equals(customer)){
					//若密码正确，则成功登录
					//添加一个cookie用于存放用户的用户名
					Cookie nameCookie = new Cookie("username",URLEncoder.encode(customer.getUserName(),"UTF-8"));
					nameCookie.setMaxAge(60*60*24);
					response.addCookie(nameCookie);
					//若用户选择“记住账号",则添加一个cookie用于保留用户的选择
					if(rememberusername.equals("true")){
						Cookie myCookie=new Cookie("rememberName",URLEncoder.encode(customer.getUserName(),"UTF-8"));
						myCookie.setMaxAge(60*60*24);
						response.addCookie(myCookie);
					}else{
						//若用户没有选择“记住账号",则删除用于保留用户选择的cookie
						Cookie[] cookies = request.getCookies();
						if(cookies != null && cookies.length>0){
							Cookie cookie = null;
							for(int i=0;i<cookies.length;i++){
								cookie = cookies[i];
								if(cookie.getName().equals("rememberName")){
									cookie.setMaxAge(0);
									response.addCookie(cookie);
									break;
								}
							}
						}
				
					}
					
					if(curCustomer.getStatus() == 0){
						//若该账号是普通用户，则跳转到普通用户页面
						response.getWriter().print("customer_index.jsp");
					}else{
						//若该账号是管理员，则跳转到管理员页面
						response.getWriter().print("admin_index.jsp");
					}
					
				}else{
					response.getWriter().print("incorrect");
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
