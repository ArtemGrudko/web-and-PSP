package by.iba.lab7pb.servlet;

import by.iba.lab7pb.service.Dao;
import by.iba.lab7pb.service.DaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "bookingServlet", urlPatterns = "/booking")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Dao dao = new DaoImpl();
        dao.insertTicket(Integer.parseInt(request.getParameter("plane")),
                Integer.parseInt(request.getParameter("person")));
        response.sendRedirect(request.getContextPath()+"/ClientServlet");
    }
}
