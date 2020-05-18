package com.stumgnt.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.stumgnt.dao.UserDao;
import com.stumgnt.dao.impl.UserDaoImpl;
import com.stumgnt.domain.User;
import com.stumgnt.util.CookieUtil;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter("/AutoLoginFilter")
public class AutoLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AutoLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			User user = (User) req.getSession().getAttribute("user");
			if (user != null) {
				chain.doFilter(req, response);
			} else {
				Cookie[] cookies = req.getCookies();
				Cookie cookie = CookieUtil.findCookie(cookies, "autologin");
				if (cookie == null) {
					chain.doFilter(req, response);
				} else {
					String cookieUserName = cookie.getValue();
					UserDao dao = new UserDaoImpl();
					user = dao.findUser(cookieUserName);

					req.getSession().setAttribute("user", user);
					chain.doFilter(req, response);
				}
			}
			// pass the request along the filter chain
//			chain.doFilter(req, response);
		} catch (Exception e) {
			// TODO: handle exception
			chain.doFilter(request, response);
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
