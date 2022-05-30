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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			request.setAttribute("msg", "登録に失敗しました");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			
		} else {
			try {
				int productId = Integer.parseInt(producuIdString);
				int price = Integer.parseInt(priceString);
				int categoryId = Integer.parseInt(categoryIdString);
				
				Product product = new Product(productId, productName, price, categoryId);
				ProductService ps = new ProductService();
				
				if (ps.insert(product) == 0) {
					request.setAttribute("msg", "登録に失敗しました");
					request.getRequestDispatcher("insert.jsp").forward(request, response);
					
				} else {
					HttpSession session = request.getSession(true);
					session.setAttribute("result", ps.getAll());
					request.setAttribute("msg", "登録に成功しました");
					request.getRequestDispatcher("menu.jsp").forward(request, response);
					
				}
				
			} catch (Exception e) {
				request.setAttribute("msg", "商品IDが重複しています");
				request.getRequestDispatcher("insert.jsp").forward(request, response);
				
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
