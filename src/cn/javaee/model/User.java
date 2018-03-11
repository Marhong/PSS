/**
 * 
 */
package cn.javaee.model;

/**
 * @see    User类,表示用户
 * @author wangbin
 * @time 2017年11月15日 下午12:41:06
 */
public class User {
	/**
	 * userName 用户名
	 * userId   用户ID
	 * password 用户密码
	 * status   用户类别，0为普通用户，1为管理员
	 */
	private String userName;
	private String userId;
	private String password;
	private int status;
	
	public User(){
		
	}
	public User(String userName,String password){
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @see 判断两个用户是否相同
	 * @author wangbin
	 * @time 2017年11月15日 下午12:45:25
	 * @return 若两个userId相同则返回true,否则返回false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj != null){
			if(obj instanceof User){
				if(this.userId.equals(((User) obj).getUserId())){
					return true;
				}
			}
		}
		return false;
	}
}
