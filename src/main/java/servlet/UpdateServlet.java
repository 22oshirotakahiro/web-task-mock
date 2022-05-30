package servlet;

import java.io.IOException;

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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentProductIdString = request.getParameter("currentProductId");
		String producuIdString = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String priceString = request.getParameter("price");
		String categoryIdString = request.getParameter("categoryId");
		String description = request.getParameter("description");
		/* String imagePath = request.getParameter("imagePath"); */
		
		if (Utility.isNullOrEmpty(producuIdString) || Utility.isNullOrEmpty(productName)
				|| Utility.isNullOrEmpty(priceString) || Utility.isNullOrEmpty(categoryIdString)
				/* || Utility.isNullOrEmpty(imagePath) */) {
			
			if (Utility.isNullOrEmpty(producuIdString)) {
				request.setAttribute("CheckProductId", "商品IDは必須です");
				
			}
			if (Utility.isNullOrEmpty(productName)) {
				request.setAttribute("CheckProductName", "商品名は必須です");
				
			}
			if (Utility.isNullOrEmpty(priceString)) {
				request.setAttribute("CheckPrice", "単価は必須です");
				
			}
			if (Utility.isNullOrEmpty(categoryIdString)) {
				request.setAttribute("CheckCategoryId", "カテゴリーは必須です");
				
			}
			/*
			 * if (Utility.isNullOrEmpty(imagePath)) {
			 * request.setAttribute("CheckImagePath", "商品画像は必須です");
			 * 
			 * }
			 */
			
			request.setAttribute("msg", "更新に失敗しました");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			
		} else {
			try {
				int currentProductId = Integer.parseInt(currentProductIdString);
				int productId = Integer.parseInt(producuIdString);
				int price = Integer.parseInt(priceString);
				int categoryId = Integer.parseInt(categoryIdString);
				
				Product product = new Product(productId, productName, price, categoryId, description);
				ProductService ps = new ProductService();
				
				if (productId != currentProductId && ps.getByProductId(productId) != null) {
					request.setAttribute("msg", "商品IDが重複しています");
					request.getRequestDispatcher("updateInput.jsp").forward(request, response);
					
				} else if (ps.update(currentProductId, product) == 0) {
					request.setAttribute("msg", "更新に失敗しました");
					request.getRequestDispatcher("updateInput.jsp").forward(request, response);
					
				} else {
					HttpSession session = request.getSession(true);
					session.setAttribute("result", ps.getAll());
					request.setAttribute("msg", "更新に成功しました");
					request.getRequestDispatcher("menu.jsp").forward(request, response);
					
				}
				
			} catch (Exception e) {
				request.setAttribute("msg", "更新時にエラーが発生しました");
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);
				
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
