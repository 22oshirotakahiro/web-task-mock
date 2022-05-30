package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.ProductService;
import service.UserService;
import util.Utility;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginId = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		String loginError = "ログインできませんでした";
		String loginErrorId = "IDは必須です";
		String loginErrorPass = "PASSは必須です";
		
		if (Utility.isNullOrEmpty(loginId)) {
			request.setAttribute("loginError", loginError);
			request.setAttribute("loginErrorId", loginErrorId);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else if (Utility.isNullOrEmpty(pass)) {
			request.setAttribute("loginError", loginError);
			request.setAttribute("loginErrorPass", loginErrorPass);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else {
			UserService us = new UserService();
			User user = us.login(loginId, pass);
			
			if (user == null) {
				loginError = "IDとPASSが違います";
				request.setAttribute("loginError", loginError);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				
			} else {
				HttpSession session = request.getSession(true);
				session.setAttribute("loginUser", user);
				ProductService ps = new ProductService();
				session.setAttribute("result", ps.getAll());
				request.getRequestDispatcher("menu.jsp").forward(request, response);
				
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
