package by.academy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MessageService {
    public void sendSuccessMessage(HttpServletRequest request, String message){
        HttpSession session = request.getSession();
        session.removeAttribute("message");

        String messageHTML = "    <div class=\"row\">" +
                "<div class=\"alert alert-info alert-dismissible fade show\" role=\"alert\">\n" + message + "\n" +
                "<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n" +
                "</div>\n</div>";

        session.setAttribute("message", messageHTML);
    }

    public void sendWarningMessage(HttpServletRequest request, String message) {
        HttpSession session = request.getSession();
        session.removeAttribute("message");

        String messageHTML = "    <div class=\"row\">" +
                "<div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\n" + message + "\n" +
                "<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n" +
                "</div>\n</div>";

        session.setAttribute("message", messageHTML);
    }

    public String getMessage(HttpServletRequest request){
        HttpSession session = request.getSession();
        String message = (String) session.getAttribute("message");
        session.removeAttribute("message");
        return message;
    }
}
