/**
 * 
 */
package cn.javaee.model;

/**
 * @see   UndueState类，表示事宜处于"尚未进入提醒时间的待完成事宜"状态
 * @author wangbin
 * @time 2017年11月15日 下午12:51:16
 */
public class UndueState extends State{
	public UndueState(int state){
		super(state);
		if(state == 1){
			this.setMeaning("尚未进入提醒时间的待完成事宜");
		}
	}
}
