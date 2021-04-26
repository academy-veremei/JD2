package by.academy.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String command = request.getParameter("command");

        String locale = session.getAttribute("locale") == null ? "en" : (String) session.getAttribute("locale");
        session.setAttribute("locale", locale);

        Object auth = session.getAttribute("auth");
        boolean isAuth = auth != null && (boolean) auth;

        if (isAuth) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if ("save_new_user".equals(command) ||
                "logination".equals(command) ||
                "tomainpage".equals(command) ||
                "change_locale".equals(command)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("Controller?command=tomainpage");
        }
    }

    @Override
    public void destroy() {

    }
}
