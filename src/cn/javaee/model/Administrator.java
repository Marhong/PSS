/**
 * 
 */
package cn.javaee.model;

import java.util.ArrayList;

/**
 * @see    Administrator类，用于表示管理员
 * @author wangbin
 * @time 2017年11月15日 下午1:30:19
 */
public class Administrator extends User{
	/**
	 * customerList 普通用户列表类
	 */
	private CustomerList customerList;
	

	public Administrator() {
		customerList = new CustomerList();
	}
	public Administrator(String userName,String password){
		super(userName,password);
	}
	public CustomerList getCustomerList() {
		return customerList;
	}

	public void setCustomerList(CustomerList customerList) {
		this.customerList = customerList;
	}
	/* 
	 * @see 判断两个管理员是否相同
	 * @author wangbin
	 * @time 2017年11月15日 下午2:59:19
	 * @return 若两者相同，则返回true,否则返回false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj != null){
			if(obj instanceof Administrator){
				if(this.getCustomerList().equals(((Administrator) obj).getCustomerList())){
					return true;
				}
			}
		}
		return false;
	}
	
}
