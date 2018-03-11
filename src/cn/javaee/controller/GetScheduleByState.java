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
 * @see    获取某种状态的所有事宜
 * @author wangbin
 * @time 2017年11月18日 下午2:58:32
 */
@WebServlet("/GetScheduleByState")
public class GetScheduleByState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetScheduleByState() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	   request.setCharacterEncoding("UTF-8");
	   //获取用户选择的状态类型
		String state = request.getParameter("inf");
		String color = null;
		//因为ScheduleItem这个类用户表示事宜的状态是用颜色表示的
		//所以要将用户选择的状态转为对应的颜色
		if(state.equals("尚未进入提醒时间的带完成事宜")){
			color = "yellow";
		}else if(state.equals("进入提醒时间的带完成事宜")){
			color = "red";
		}else if(state.equals("已完成的事宜")){
			color = "green";
		}else{
			color = "gray";
		}
		
		Cookie[] cookies = request.getCookies();
		//用户存放获取的事宜
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
						//筛选出所有状态与用户选择的状态相同的事宜
						for(ScheduleItem item: customer.getSchedule().getSchedule()){
							if(item.getColor().equals(color)){
								items.add(item);
								
							}
						}
						if(items.size()>0){
							//将获取的所有事宜，以json格式传回客户端
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
