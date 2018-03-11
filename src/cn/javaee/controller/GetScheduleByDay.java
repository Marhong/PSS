package cn.javaee.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.javaee.model.Customer;
import cn.javaee.model.CustomerList;
import cn.javaee.model.ScheduleItem;
import net.sf.json.JSONArray;

/**
 * 
 * @see    获取某一天的所有事宜
 * @author wangbin
 * @time 2017年11月18日 下午2:50:59
 */
@WebServlet("/GetScheduleByDay")
public class GetScheduleByDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetScheduleByDay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取用户选择的日期
		String month = request.getParameter("inf");
	
			Cookie[] cookies = request.getCookies();
			//用于存放获取的事宜
			ArrayList<ScheduleItem> items = new ArrayList<ScheduleItem>();
			if(cookies != null){
				for(int i=0;i<cookies.length;i++){
					if(cookies[i].getName().equals("username")){
						//获取当前用户的用户名
						String temp = cookies[i].getValue();
						String username = URLDecoder.decode(temp, "UTF-8");
						//通过用户名获取当前用户
						CustomerList customerList = new CustomerList();
						Customer customer = customerList.getCustomerByName(username);
						if(customer != null){
							//遍历该用户的所有事宜
							//筛选出所有日期与用户选择的日期相同的事宜
							for(ScheduleItem item: customer.getSchedule().getSchedule()){
								//因为事宜的开始时间格式是这样的： 2017-11-18 14:54:24
								//获取到的用户选择的日期格式是这样的: 2017-11-18
								//所以可以通过String.contains()方法来判断该事宜是否合格
								if(item.getStart().contains(month)){
									items.add(item);
								}
							}
							if(items.size()>0){
								//将获取到的所有事宜以json格式传回给用户端
								JSONArray schedule = JSONArray.fromObject(items);
								response.setCharacterEncoding("UTF-8");
								response.getWriter().print(schedule);
							}
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
