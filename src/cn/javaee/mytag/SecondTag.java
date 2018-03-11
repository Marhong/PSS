/**
 * 
 */
package cn.javaee.mytag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @see     判断用户是否登录
 * @author wangbin
 * @time 2017年11月18日 上午10:41:09
 */
public class SecondTag extends SimpleTagSupport {
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		boolean exist = false;
	   PageContext ctx = (PageContext) getJspContext();
	 
	   HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
	   HttpServletResponse response = (HttpServletResponse) ctx.getResponse();
	   Cookie[] cookies = request.getCookies();
	   if(cookies != null){
		   for(int i=0;i<cookies.length;i++){
			   //若用户已经登录则会有一个名为username的Cookie
			   if(cookies[i].getName().equals("username")){
				   exist = true;
			   }
			  
		   }
	   }
	   
	   if(!exist){
		   //若用户没有登录，则只能访问登录页面
		   response.sendRedirect("login.jsp");
	   }
	
	}
}
