package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编码过滤器
 * @author 陈起廷
 * @version 2019年5月3日
 */
@WebFilter("/*")
public class EncodingSetFilter implements Filter {
	@Override
	public void destroy() {
	  System.out.println(Calendar.getInstance().getTime()+"过滤器已执行结束");
	}
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	       request.setCharacterEncoding("utf-8");
	       response.setCharacterEncoding("utf-8");
	       response.setContentType("text/html; charset=UTF-8");
	       HttpServletRequest req = (HttpServletRequest) request;
           HttpServletResponse res=(HttpServletResponse) response;
		   chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println(Calendar.getInstance().getTime()+"过滤器初始化");
	}

}
