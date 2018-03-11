/**
 * 
 */
package cn.javaee.model;

/**
 * @see    ScheduleItem类，表示日程安排中的事宜
 * @author wangbin
 * @time 2017年11月15日 下午12:48:04
 */
public class ScheduleItem {
	/**
	 * id         事宜ID
	 * customerId 该事宜所属的用户ID
	 * content    该事宜的内容
	 * time		    该事宜设置的时间
	 * remindTime 该事宜的提醒时间（如在事宜设置的时间前30分钟或者一小时，该事宜进入提醒时间状态)
	 * color      该事宜的状态
	 * 未进入提醒时间的待完成事宜用 黄色表示
	 * 进入提醒时间的待完成事宜用     红色表示
	 * 已完成的事宜用			       绿色表示
	 * 未完成的事宜用                         灰色表示   
	 */
	private String id;
	private String customerId;
	private String title;
	private String start;
	private String end;
	private String color;
	private String remindTime;
	public ScheduleItem(){
		
	}
	public ScheduleItem(String id,String title,String start,String end,String color,String remindTime){
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.color = color;
		this.remindTime = remindTime;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "{id:'"+this.id+"',title:'"+this.title+"',start:'"+this.start+"',end:'"+this.end+"',color:'"+this.color+"',remindTime'"+this.remindTime+"'}";
}
	/**
	 * @see 判断两个事宜是否相同
	 * @author wangbin
	 * @time  2017年11月15日 下午13:02:14
	 * @return 若两者相同则返回true,否则返回false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj != null){
			if(obj instanceof ScheduleItem){
				if(this.getId().equals(((ScheduleItem) obj).getId())){
					return true;
				}
			}
		}
		return false;
	}
}
