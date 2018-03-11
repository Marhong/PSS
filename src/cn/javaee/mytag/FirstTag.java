/**
 * 
 */
package cn.javaee.mytag;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**  
 * @see    获取当前的年份
 * @author wangbin
 * @time 2017年11月18日 上午10:40:53
 */
public class FirstTag extends SimpleTagSupport {
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspWriter out = getJspContext().getOut();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		out.print(year);
	}
}
