package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import service.ProductService;
import util.Utility;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ProductService ps = new ProductService();
		String keyWord = request.getParameter("keyWord");
		 List<Product> result = (List<Product>) session.getAttribute("result"); 
		
		if (Utility.isNullOrEmpty(keyWord)) {
			session.setAttribute("result", ps.getAll());
			request.getRequestDispatcher("menu.jsp").forward(request, response);
			
		} else {
			result =ps.getLikeKeyWord(keyWord); 
			
			if(result.size() == 0) {
				request.setAttribute("msg", "検索結果がありませんでした");
				request.getRequestDispatcher("menu.jsp").forward(request, response);
				
			} else {
				request.setAttribute("msg", "検索結果");
				session.setAttribute("result", result);
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
