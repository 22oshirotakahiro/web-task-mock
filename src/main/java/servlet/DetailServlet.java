package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.ProductService;
import util.Utility;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
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
			Product product = ps.getByProductId(productId);
			
			if (product == null) {
				request.setAttribute("msg", "商品情報がありません");
				request.getRequestDispatcher("detail.jsp").forward(request, response);
				
			} else {
				request.setAttribute("product", product);
				request.getRequestDispatcher("detail.jsp").forward(request, response);
				
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
