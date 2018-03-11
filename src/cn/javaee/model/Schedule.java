/**
 * 
 */
package cn.javaee.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



/**
 * @see    Schedule类，用于管理用户的所有事宜(ScheduleItem)
 * @author wangbin
 * @time 2017年11月15日 下午1:06:34
 */
public class Schedule {
	/**
	 * customerId 该日程安排所属的用户ID
	 * schedule	  该日程安排所包含的所有事宜(ScheduleItem)
	 */
	private String customerId;
	private ArrayList<ScheduleItem> schedule;
	
	public Schedule(){
		
	}
	/**
	 * 将schedule_item表中的所有信息转为ScheduleItem，添加到schedule中
	 * @param customerId  用户ID
	 */
	public Schedule(String customerId){
		Connection conn=null;
		schedule = new ArrayList<ScheduleItem>();
		try {
			conn = AccessJDBCUtil.getAccessDBConnection();
			Statement stmt = conn.createStatement();
			String queryString = "select * from schedule_item where customerId = "+customerId;
			ResultSet rs = stmt.executeQuery(queryString);
				while (rs.next()) {
					ScheduleItem item = new ScheduleItem();
					item.setId(rs.getString("id"));
					item.setCustomerId(rs.getString("customerId"));
					item.setTitle(rs.getString("content"));
					//数据表中的time选项对应一个事宜的开始时间
					String start = rs.getString("time");
					//数据表中的end选项对应一个事宜的结束时间
					String end = rs.getString("end");
					String remindTime = rs.getString("remindTime");
					item.setEnd(end);
					item.setRemindTime(remindTime);
					//因为数据表中存放时间的格式是时间戳,但是客户端显示的时间是:2017-11-12 14:25:25
					//所以需要将时间戳转为客户端想要的时间格式
					SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
					//数据表中存放的时间戳长度是10为，单位是秒
					//format(timestamp)函数中的时间戳单位是毫秒
					//所以要给从数据表中获取的时间加上3个0
					String startTimeString = format.format(Long.parseLong(start+"000"));
					String endTimeString = format.format(Long.parseLong(end+"000"));
					item.setStart(startTimeString);
					item.setEnd(endTimeString);
					//数据表中是用state项来表示一个事宜的状态
					//ScheduleItem是用color来表示一个事宜的状态
					//所以要将state值转为对应的颜色
					int state = rs.getInt("state");
					String color=null;
					if(state == 1){
						color = "yellow";
					}else if(state == 2){
						color = "red";
					}else if(state == 3){
						color = "green";
					}else{
						color = "gray";
					}
					item.setColor(color);
					schedule.add(item);
					
		}
				//更新返回的数据
				//就是判断哪些事宜已经进入了提醒时间，修改那些进入提醒时间的事宜状态
				//但是并没有修改数据库中这些事宜的值
				updateSchedule(schedule);
				//修改数据库中这些事宜的值
				updateData(schedule);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public ArrayList<ScheduleItem> getSchedule() {
		return schedule;
	}
	public void setSchedule(ArrayList<ScheduleItem> schedule) {
		this.schedule = schedule;
	}
	/**
	 * 
	 * @see    通过ScheduleItem的id来获取ScheduleItem
 	 * @author wangbin
	 * @time 2017年11月15日 下午1:13:30
	 * @param itemId ScheduleItem的id
	 * @return  若存在则返回该ScheduleItem,否则返回空
	 */
	public ScheduleItem getScheduleItem(String itemId){
		//遍历该Schedule中的所有ScheduleItem
		//若存在一项的id与itemId相同则返回该项
		for(ScheduleItem item: schedule){
			if(item.getId().equals(itemId)){
				return item;
			}
		}
		return null;
	}
	/**
	 * 
	 * @see	   添加一条事宜
	 * @author wangbin
	 * @time 2017年11月15日 下午1:15:28
	 * @param item 待添加的事宜
	 */
	public boolean addScheduleItem(ScheduleItem item){
		//若该项事宜不为空，则正常添加
		String id = item.getId();
		String cutomerId = item.getCustomerId();
		String title = item.getTitle();
		String start = item.getStart();
		String end = item.getEnd();
		String remindTime = item.getRemindTime();
		Connection conn=null;
		try {
			conn = AccessJDBCUtil.getAccessDBConnection();
			Statement stmt = conn.createStatement();
			String sql = "insert into schedule_item (id,customerId,content,time,remindTime,state,end) values('" + id + "','"
					+ cutomerId + "','" + title+"','"+start +"','"+remindTime+"',1,'"+end+"');";
			int result = stmt.executeUpdate(sql);
			if (result != -1) {
				schedule.add(item);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @see    删除一条事宜
	 * @author wangbin
	 * @time 2017年11月15日 下午1:20:12
	 * @param itemId 待删除事宜的ID
	 * @return 若成功删除则返回true,否则返回false;
	 */
	public boolean deleteScheduleItem(String itemId){
		Connection conn=null;
		try {
			conn = AccessJDBCUtil.getAccessDBConnection();
			Statement stmt = conn.createStatement();
			String sql = "delete from schedule_item  where id = "+itemId;
			int result = stmt.executeUpdate(sql);
			if(result != -1){
				return true;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @see    修改某项事宜
	 * @author wangbin
	 * @time 2017年11月15日 下午1:25:03
	 * @param itemId  待修改事宜的id
	 * @return 若修改成功则返回true,否则返回false;
	 */
	public boolean modifyScheduleItem(String itemId,String title,String start,String end,String remindTime){
		Connection conn=null;
		try {
			conn = AccessJDBCUtil.getAccessDBConnection();
			Statement stmt = conn.createStatement();
			String sql = "update schedule_item set content = '"+title+"',time='"+start+"',end='"+end+"',remindTime='"+remindTime+"' where id = "+itemId;
			stmt.executeUpdate(sql);
			return true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @see  完成一条事宜
	 * @author wangbin
	 * @time 2017年11月18日 下午3:27:20
	 * @param itemId  被完成的事宜的ID
	 * @return
	 */
	public boolean changeItemState(String itemId){
		Connection conn=null;
		try {
			conn = AccessJDBCUtil.getAccessDBConnection();
			Statement stmt = conn.createStatement();
			//将该事宜的state值改为3,即意味着完成了一条事宜
			String sql = "update schedule_item set state = 3  where id = "+itemId;
			int result = stmt.executeUpdate(sql);
			if(result != -1){
				return true;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @see    更新事宜的状态
	 * @author wangbin
	 * @time 2017年11月18日 下午3:28:27
	 * @param schedule
	 */
	public void updateSchedule(ArrayList<ScheduleItem> schedule){
		//遍历所有的事宜，看哪些事宜需要修改
			for(ScheduleItem item: schedule){
			
				long now  = Long.parseLong(String.valueOf(System.currentTimeMillis()));
				String start = item.getStart();
				String end = item.getEnd();
				String remindTime =item.getRemindTime();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        Date startDate = null;
		        Date endDate = null;
				try {
					startDate = simpleDateFormat.parse(start);
					endDate = simpleDateFormat.parse(end);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        long startTimeStamp = startDate.getTime();
		        long endTimeStamp = endDate.getTime();
				long dueRemindTime = startTimeStamp-Long.parseLong(remindTime);
	
				if(now>dueRemindTime && now<endTimeStamp && !item.getColor().equals("green") && !item.getColor().equals("gray")){
					//若当前时间大于用户设置的事宜开始时间减去提醒时间
					//则意味着该事宜已经进入了提醒状态
					item.setColor("red");
					
				}else if(now >endTimeStamp && !item.getColor().equals("green")){
					//若当前时间大于用户设置的事宜的结束时间，且用户没有完成该事宜
					//则意味着该事宜进入了"未完成"状态
						item.setColor("gray");
						
				}
		
				
			}
		} 
	/**
	 * 
	 * @see   更新数据表中需要修改的事宜
	 * 该方法同上，只是这个方法是修改数据表中的数据
	 * @author wangbin
	 * @time 2017年11月18日 下午3:31:01
	 * @param schedule
	 */
	public void updateData(ArrayList<ScheduleItem> schedule){
		Connection conn=null;
		try {
			conn = AccessJDBCUtil.getAccessDBConnection();
			Statement stmt = conn.createStatement();
			for(ScheduleItem item: schedule){
			long now  = Long.parseLong(String.valueOf(System.currentTimeMillis()));
			String start = item.getStart();
			String end = item.getEnd();
			String remindTime =item.getRemindTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date startDate = null;
	        Date endDate = null;
			try {
				startDate = simpleDateFormat.parse(start);
				endDate = simpleDateFormat.parse(end);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        long startTimeStamp = startDate.getTime();
	        long endTimeStamp = endDate.getTime();
			long dueRemindTime = startTimeStamp-Long.parseLong(remindTime);
			String sql = null;
			if(now>dueRemindTime && now<endTimeStamp && !item.getColor().equals("green") && !item.getColor().equals("gray")){
				sql = "update schedule_item set state = 2 where id = "+item.getId();
				stmt.executeUpdate(sql);
			}else if(now >endTimeStamp && !item.getColor().equals("green")){
				sql = "update schedule_item set state = 4 where id = "+item.getId();
				stmt.executeUpdate(sql);
			}
			
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	} 

	/* 
	 * @see    判断两个Schedule是否相同
	 * @author wangbin
	 * @time   2017年11月15日 下午1:09:34
	 * @return 若两者相同则返回true,否则返回false;
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj != null){
			if(obj instanceof Schedule){
				if(this.getCustomerId().equals(((Schedule) obj).getCustomerId()) && this.getSchedule().equals(((Schedule) obj).getSchedule())){
					return true;
				}
			}
		}
		return false;
	}
}
