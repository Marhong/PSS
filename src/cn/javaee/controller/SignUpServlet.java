package cn.javaee.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.javaee.model.Customer;
import cn.javaee.model.CustomerList;

/**
 * 
 * @see	  用户注册
 * @author wangbin
 * @time 2017年11月18日 下午3:10:54
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取用户想要注册的用户名和密码
		String userNameSignUp = request.getParameter("userNameSignUp");
		String passwordSignUp = request.getParameter("passwordSignUp");
		if ((userNameSignUp != null) && (passwordSignUp != null)) {
			//判断该用户名是否已经被注册
			CustomerList customerList = new CustomerList();
			Customer customer = customerList.getCustomerByName(userNameSignUp);
			if(customer == null){
				//若该用户名尚未被注册,则添加该用户
				customer =new Customer(userNameSignUp,passwordSignUp);
				customer.setStatus(0);
				customer.setUserId(String.valueOf(System.currentTimeMillis()));
				customer.setSchedule(null);
				//将用户输入的信息转为相应的Customer类
				try {
					customerList.addCustomer(customer);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.getWriter().print("success");
			}else{
				//若已经被注册，则返回一个错误提示
				response.getWriter().print("error");
			}
		} else {
			response.getWriter().print("there is an error!");
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
