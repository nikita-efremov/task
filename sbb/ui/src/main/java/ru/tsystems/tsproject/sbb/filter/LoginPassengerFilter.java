package ru.tsystems.tsproject.sbb.filter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filter redirects all unauthorized passengers lo passenger login page.
 * If passenger is authorized, it redirects to next filter
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

    /**
     * Method filters users to authorized and unauthorized.
     * Authorized will be redirected to the next filter, if it exists, or target servlet.
     * Unauthorized users will be redirected passenger login page
     * @param servletRequest
     *        Servlet request
     * @param servletResponse
     *        Servlet response
     * @param filterChain
     *        Filters collection
     */
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