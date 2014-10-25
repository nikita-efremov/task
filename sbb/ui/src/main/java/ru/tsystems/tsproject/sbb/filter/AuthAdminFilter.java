package ru.tsystems.tsproject.sbb.filter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filter redirects all unauthorized users lo administrator login page.
 * If user is authorized, it redirects to next filter
 * @author  Nikita Efremov
 * @since   1.0
 */
public class AuthAdminFilter implements Filter {
    private static final Logger log = Logger.getLogger(AuthAdminFilter.class);
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    /**
     * Method filters users to authorized and unauthorized.
     * Authorized will be redirected to the next filter, if it exists, or target servlet.
     * Unauthorized users will be redirected administrator login page
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
