/**
 * 
 */
package cn.javaee.model;

/**
 * @see   State类,表示事宜的状态
 * @author wangbin
 * @time 2017年11月15日 下午12:48:19
 */
public class State {
	private int code;
	private String meaning;
	
	public State(int state){
		this.code = state;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	/**
	 * @see 判断两个State是否相同
	 * @author wangbin
	 * @time 2017年11月15日 下午12:49:04
	 * @return 若两者code和meaning都相同返回true,否则返回false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (code != other.code)
			return false;
		if (meaning == null) {
			if (other.meaning != null)
				return false;
		} else if (!meaning.equals(other.meaning))
			return false;
		return true;
	}
	
}
