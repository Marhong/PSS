/**
 * 
 */
package cn.javaee.model;

/**
 * @see    RemindingState类，表示事宜处于"进入提醒时间的待完成事宜"状态
 * @author wangbin
 * @time 2017年11月15日 下午12:52:12
 */
public class RemindingState extends State{
	public RemindingState(int state){
		super(state);
		if(state == 2){
			this.setMeaning("进入等待时间的待完成事宜");
		}
	}
}
