package by.iba.lab7pb.servlet;


import by.iba.lab7pb.dao.UserDao;
import by.iba.lab7pb.model.Person;
import by.iba.lab7pb.model.User;
import by.iba.lab7pb.service.Dao;
import by.iba.lab7pb.service.DaoImpl;
import by.iba.lab7pb.util.HashPassword;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String username = request.getParameter("newLoginName");
        String password = request.getParameter("newPassword");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String mail = request.getParameter("mail");

        Dao daoUser = new DaoImpl();
        User user = new User(username, HashPassword.getHash(password), false, 0);
        if (daoUser.insertUser(user)) {
            daoUser.insertPerson(new Person(name,phone, mail, daoUser.getLastUser()));
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else {

            request.setAttribute("errorRegister", "Выберите другое имя, такой пользователь существет");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp")
                    .forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request

                .getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(request, response);


    }
}
