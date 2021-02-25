package by.academy.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GatekeeperService {
    public RequestDispatcher conductor(HttpServletRequest request, String jspName) {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
             return request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        } else {
            return request.getRequestDispatcher("/WEB-INF/jsp/"+jspName);
        }
    }

    public boolean isExist(HttpServletRequest request){
        HttpSession session = request.getSession();

        return session.getAttribute("user") != null;
    }
}
