/**
 * 
 */
package cn.javaee.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 * @see    CustomerList类，用于管理所有的普通用户信息
 * @author wangbin
 * @time 2017年11月15日 下午1:30:56
 */
public class CustomerList {
	/**
	 * 用于存储所有的用户信息
	 */
	private ArrayList<Customer> customerList;
    /**
     * 从user表中将所有的用户信息转为Customer类，添加到customerList中
     * 
     */
	public CustomerList(){
		
		Connection conn=null;
		customerList = new ArrayList<Customer>();
		try {
			conn = AccessJDBCUtil.getAccessDBConnection();
			Statement stmt = conn.createStatement();
			String queryString = "select * from user ";
			ResultSet rs = stmt.executeQuery(queryString);
				while (rs.next()) {
					Customer customer = new Customer(rs.getString("userId"));
					customer.setUserId(rs.getString("userId"));
					customer.setUserName(rs.getString("userName"));
					customer.setStatus(rs.getInt("status"));
					customer.setPassword(rs.getString("password"));
					customerList.add(customer);
		
		}
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
	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
	/**
	 * 
	 * @see    通过普通用户名获取普通用户
	 * @author wangbin
	 * @time 2017年11月15日 下午1:35:26
	 * @param name 待获取的用户的名字
	 * @return 若存在名字为name的用户，则返回该用户，否则返回空
	 */
	public Customer getCustomerByName(String name){
		
		for(Customer customer: customerList){
			if(customer.getUserName().equals(name)){
				return customer;
			}
		}
		return null;
	}
	/**
	 * 
	 * @see	   通过普通用户的用户id获取普通用户
	 * @author wangbin
	 * @time 2017年11月15日 下午1:37:31
	 * @param id 待获取的用户的ID
	 * @return 若存在ID为id的用户，则返回该用户，否则返回空
	 */
	public Customer getCustomerById(String id){
		for(Customer customer: customerList){
			if(customer.getUserId().equals(id)){
				return customer;
			}
		}
		return null;
	}
	/**
	 * 
	 * @see   添加一个普通用户
	 * @author wangbin
	 * @time 2017年11月15日 下午1:39:20
	 * @param customer 待添加的用户
	 */
	public boolean addCustomer(Customer customer) throws ClassNotFoundException{
		String userName = customer.getUserName();
		String password = customer.getPassword();
		String userId = String.valueOf(System.currentTimeMillis());
		int status = 0;
		Connection conn=null;
		try {
			conn = AccessJDBCUtil.getAccessDBConnection();
			Statement stmt = conn.createStatement();
			String sql = "insert into user (userName,password,userId,status) values('" + userName + "','"
					+ password + "','" + userId+"',"+status + ");";
			int result = stmt.executeUpdate(sql);
			if (result != -1) {
				customerList.add(customer);
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
	 * @see    删除一个用户
	 * @author wangbin
	 * @time 2017年11月15日 下午1:40:58
	 * @param id 待删除用户的ID
	 * @return 若存在该用户且成功删除，则返回true,否则返回false;
	 */
	public boolean deleteCustomer(String id){
		Customer customer = getCustomerById(id);
		if(customer != null){
			customerList.remove(customer);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @see    修改一个普通用户的信息
	 * @author wangbin
	 * @time 2017年11月15日 下午1:43:07
	 * @param id 待修改用户的ID
	 * @return  若存在该用户且修改信息成功，则返回true,否则返回false
	 */
	public boolean modifyCustomer(String id){
		for(Customer customer: customerList){
			if(customer.getUserId().equals(id)){
				//进行相关修改操作
				return true;
			}
		}
		return false;
	}
	/* 
	 * @see    判断两个CustomerList是否相同
	 * @author wangbin
	 * @time   2017年11月15日 下午1:31:56
	 * @return 若两者相同，则返回true,否则返回false
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj != null){
			if(obj instanceof CustomerList){
				if(this.getCustomerList().equals(((CustomerList) obj).getCustomerList())){
					return true;
				}
			}
		}
		return false;
	}
}
