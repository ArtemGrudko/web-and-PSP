package by.iba.lab7pb.servlet;


import by.iba.lab7pb.dao.UserDao;
import by.iba.lab7pb.model.User;
import by.iba.lab7pb.service.Dao;
import by.iba.lab7pb.service.DaoImpl;
import by.iba.lab7pb.util.HashPassword;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        Dao daoUser = new DaoImpl();
        User user = daoUser.isValidUser(name, HashPassword.getHash(password));
        if(user!=null) {

            request.getSession().setAttribute("name", name);

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) { Cookie cookie = c;
                    System.out.println(cookie.getName() + cookie.getValue());
                    if (name.equals(cookie.getName())) {
                        request. getSession().setAttribute("lastdate",
                                cookie.getValue());
                    }
                }
            }


            Cookie userCookie = new Cookie("user", String.valueOf(user.getId()));
            userCookie.setMaxAge(60 * 60 * 24 * 365); //хранить куки год
            response.addCookie(userCookie);
            if(user.isType()){
                response.sendRedirect(request.getContextPath() + "/GroupListServlet");
            }else {
                response.sendRedirect(request.getContextPath()+"/ClientServlet");
            }

// НЕТ ПАРАМЕТРОВ - всегда использует метод get request.getRequestDispatcher("/GroupServlet")
//.forward(request, response);

        } else {
            request.setAttribute("errorMessage", "Invalid Login and password!!");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                    .forward(request, response);
        }

    }

   /* public boolean validateUser(String user, String password) {
        return user.equalsIgnoreCase("admin") && password.equals("admin");
    }*/

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        System.out.println("service");
    }
}