package ru.tsystems.tsproject.sbb.filter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filter redirects all unauthorized passengers lo login page. If user is authorized, it redirects it to default page
 * @author  Nikita Efremov
 * @since   1.0
 */
public class LoginPassengerFilter implements Filter {
    private static final Logger log = Logger.getLogger(LoginPassengerFilter.class);
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
        String docNumber = (String)httpSession.getAttribute("passDoc");
        try {
            if ((user != null) && (docNumber != null)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendRedirect("/ui/passengerLogin.jsp");
            }
        } catch (Exception e) {
            log.log(Level.ERROR, "Filter error: " + e);
        }
    }
}