package by.iba.lab7pb.servlet;

import by.iba.lab7pb.service.Dao;
import by.iba.lab7pb.service.DaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dao dao = new DaoImpl();
        dao.deleteBook((Integer.parseInt(req.getParameter("id"))));
        resp.sendRedirect(req.getContextPath()+"/GroupListServlet");
    }
}
