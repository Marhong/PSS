/**
 * 
 */
package cn.javaee.mytag;


import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @see    获取名为rememberName的Cookie的值
 * @author wangbin
 * @time 2017年11月18日 上午11:35:57
 */
public class FourthTag extends SimpleTagSupport {
	public void doTag() throws javax.servlet.jsp.JspException, java.io.IOException {
		PageContext ctx = (PageContext) getJspContext();

		HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
		JspWriter out = getJspContext().getOut();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("rememberName")){
					out.print(URLDecoder.decode(cookies[i].getValue(), "UTF-8"));
				}

			}
		}

		;
	}
}
