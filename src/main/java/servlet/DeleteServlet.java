package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ProductService;
import util.Utility;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productIdString = request.getParameter("productId");
		
		if (Utility.isNullOrEmpty(productIdString)) {
			request.setAttribute("msg", "商品情報がありません");
			request.getRequestDispatcher("detail.jsp").forward(request, response);
			
		} else {
			int productId = Integer.parseInt(productIdString);
			ProductService ps = new ProductService();
			int deletableNumber = 0;
			
			try {
				deletableNumber = ps.delete(productId);
				
			} catch (Exception e) {
				request.setAttribute("msg", "削除に失敗しました");
				request.getRequestDispatcher("menu.jsp").forward(request, response);
				
			}
			
			if (deletableNumber == 0) {
				request.setAttribute("msg", "削除に失敗しました");
				request.getRequestDispatcher("menu.jsp").forward(request, response);
				
			} else {
				request.setAttribute("msg", "削除に成功しました");
				HttpSession session = request.getSession(true);
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
