/**
 * 
 */
package cn.javaee.model;

/**
 * @see    CompletedState类，表示事宜处于"已经完成"状态
 * @author wangbin
 * @time 2017年11月15日 下午12:52:55
 */
public class CompletedState extends State {
	public CompletedState(int state){
		super(state);
		if(state == 3){
			this.setMeaning("已经完成的事宜");
		}
	}
}
