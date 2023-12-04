package by.iba.lab7pb.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "LoginRequiredFilter", urlPatterns = "/GroupListServlet")
public class LoginRequiredFilter implements Filter {
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        if (session.getAttribute("name")!=null) {
            chain.doFilter(request, response);
        }
        else {

//	httpResp.sendRedirect(httpReq.getContextPath() + "/LoginServlet");
            session.invalidate();
            request.getRequestDispatcher("LoginServlet").forward(request,
                    response);
        }

        }


        public void init (FilterConfig config) throws ServletException {
        }
    }
