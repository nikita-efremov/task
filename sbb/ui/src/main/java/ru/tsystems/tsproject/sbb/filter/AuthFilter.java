package ru.tsystems.tsproject.sbb.filter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 15.10.14
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class AuthFilter implements Filter {
    private static final Logger log = Logger.getLogger(AuthFilter.class);
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();
        String user = (String)httpSession.getAttribute("user");
        try {
            if ((user!= null) && (user.equals("admin"))) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendRedirect("/ui/adminAuthorization.jsp");
            }
        } catch (Exception e) {
            log.log(Level.ERROR, "Filter error: " + e);
        }
    }
}
