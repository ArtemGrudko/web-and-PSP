package by.iba.lab7pb.servlet;


import by.iba.lab7pb.dao.PersonDao;
import by.iba.lab7pb.model.Person;
import by.iba.lab7pb.model.Plane;
import by.iba.lab7pb.service.Dao;
import by.iba.lab7pb.service.DaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientServlet", value = "/ClientServlet")
public class ClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dao dao = new DaoImpl();
        Cookie[] cookies = request.getCookies();
        String cookieName = "user";
        Cookie cookie = null;
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }

        dao.insertTicket(Integer.parseInt(request.getParameter("id")), dao.getPersonByUser(Integer.parseInt(cookie.getValue())));
        dao.updatePlanesTickets(Integer.parseInt(request.getParameter("id")), true);
        /*request.getRequestDispatcher("/WEB-INF/views/client.jsp").forward(request, response);*/
        response.sendRedirect(request.getContextPath()+"/ClientServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/client.jsp").forward(request, response);
    }
}

