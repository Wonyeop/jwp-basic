package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		
		User user = DataBase.findUserById(userId);
		
		if ( user == null || password.compareTo(user.getPassword()) != 0) {
			RequestDispatcher rd = req.getRequestDispatcher("login_failed.jsp");
			rd.forward(req, resp);
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("/");
		}
        
	}
	
}
