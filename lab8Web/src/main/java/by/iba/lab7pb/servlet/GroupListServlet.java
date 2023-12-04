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

@WebServlet(name = "GroupListServlet", value = "/GroupListServlet")
public class GroupListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Dao dao = new DaoImpl();

        String num = request.getParameter("num");
        String company = request.getParameter("comp");
        String cityFrom = request.getParameter("cityFrom");
        String cityTo = request.getParameter("cityTo");
        String date = request.getParameter("date");
        Integer tickets = Integer.valueOf(request.getParameter("tickets"));
        dao.insertPlane(new Plane(null, num, company, cityFrom, cityTo, date, tickets, tickets));
        response.sendRedirect(request.getContextPath()+"/GroupListServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Dao daoPerson = new DaoImpl();
        request.setAttribute("group", daoPerson.getPersons());
        request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
    }
}

