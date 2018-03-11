/**
 * 
 */
package cn.javaee.model;

/** 
 * @see	   Customer类，表示普通用户
 * @author wangbin
 * @time 2017年11月15日 下午1:26:10
 */
public class Customer extends User{
	/**
	 * schedule Schedule类，用于存储该用户的所有日程
	 */
	private Schedule schedule;
	
	/**
	 * 默认的构造函数
	 */
	public Customer(){
		
	}
	/**
	 * 
	 * @param cutomerId  用户ID
	 */
	public Customer(String cutomerId){
		schedule = new Schedule(cutomerId);
		this.setUserId(cutomerId);
	}
	/**
	 * 
	 * @param userName  用户名
	 * @param password  用户密码
	 */
	public Customer(String userName,String password){
		super(userName,password);
	}
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	/* 
	 * @see  判断两个普通用户是否相同
	 * @author wangbin
	 * @time 2017年11月15日 下午1:27:10
	 * @reutrn 若两者相同则返回true,否则返回false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj != null){
			if(obj instanceof Customer){
				if((this.getUserName().equals(((Customer) obj).getUserName()) && this.getPassword().equals(((Customer) obj).getPassword()))){
					return true;
				}
			}
		}
		return false;
	}
}
