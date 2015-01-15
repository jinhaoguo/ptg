package org.gdut.ptg.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * 重写Spring Mvc Servlet，处理输入URL没有requestMapping处理的情况。<br>
 * <pre>
 * 让它直接跳至其url对应的jsp。
 * 跳转规则分为两种：
 * 1.输入地址符合下列规则的情况的处理方法。
 *  /{1}/{2}/{3}/{4}.do
 *  对应的jsp为 /{1}/{2}/{3}{4}.jsp ,注意这里需要将{4}首字母修改为大写。
 * 	 如/gdut/system/appRole/add.do 其对应的jsp路径则为:
 *   /gdut/system/appRoleAdd.jsp，
 *   /gdut/system/appRoleGrant.do,其对应的jsp路径则为
 *   /gdut/system/appRoleGrant.jsp
 * 2.输入的地址不符合上面的规则，那么就把ht直接换成jsp。
 *   例如：
 *   /gdut/system.do -->/gdut/system.jsp
 *   /gdut.do -->/gdut.jsp
 *   这些jsp均放在/WEB-INF/view目录下
 *   
 *   在web.xml配置如下：
 *   &lt;servlet>
 *       &lt;servlet-name>action&lt;/servlet-name>
 *        &lt;servlet-class>org.gdut.ptg.core.web.servlet.SpringMvcServlet&lt;/servlet-class>
 *       &lt;init-param>
 *			&lt;param-name>contextConfigLocation&lt;/param-name>
 *			&lt;param-value>classpath:conf/app-action.xml&lt;/param-value>
 *		&lt;/init-param>
 *       &lt;load-on-startup>2&lt;/load-on-startup>
 *   &lt;/servlet>
 *  </pre>
 *   
 */
public class SpringMvcServlet extends DispatcherServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		String requestURI=request.getRequestURI();
		logger.debug("not foud handle mapping for url: " + requestURI);
		//处理RequestURI
		String contextPath=request.getContextPath();
		
//		requestURI=requestURI.replace(".do", "");
		int cxtIndex=requestURI.indexOf(contextPath);
		if(cxtIndex!=-1)
		{
			requestURI=requestURI.substring(cxtIndex+contextPath.length());
		}
		String[]paths=requestURI.split("[/]");
		String jspPath=null;
		if(paths!=null && paths.length==5){
			jspPath="/"+paths[1] + "/" + paths[2] + "/" +paths[3] +  makeFirstLetterUpperCase(paths[4]) + ".jsp";
		}else{
			jspPath=requestURI + ".jsp";
		}
		logger.debug("requestURI:" + request.getRequestURI() + " and forward to /WEB-INF/view"+jspPath );
		request.getRequestDispatcher("/WEB-INF/view"+jspPath).forward(request, response);
	}
	
	/**
	 * 把字符串的第一个字母转为大写
	 * 
	 * @param newStr
	 * @return
	 */
	private String makeFirstLetterUpperCase(String newStr) {
		if (newStr.length() == 0)
			return newStr;
		char[] oneChar = new char[1];
		oneChar[0] = newStr.charAt(0);
		String firstChar = new String(oneChar);
		return (firstChar.toUpperCase() + newStr.substring(1));
	}
}
