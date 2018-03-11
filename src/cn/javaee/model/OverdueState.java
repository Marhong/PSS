/**
 * 
 */
package cn.javaee.model;

/**
 * @see    OverdueState类，表示事宜处于"没有完成"状态
 * @author wangbin
 * @time 2017年11月15日 下午12:53:43
 */
public class OverdueState extends State {
	public OverdueState(int state){
		super(state);
		if(state == 4){
			this.setMeaning("没有完成的事宜");
		}
	}
}
