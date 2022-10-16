package demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Add_Customer_Stocks")
public class Add_Customer_Stocks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Add_Customer_Stocks() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Company[] com = new Company[9999];
		DbItems dbI = new DbItems();
		String[] comp_name = request.getParameterValues("check");
		for (int i = 0; i < comp_name.length; i++) {
			com[i] = new Company();
			com[i] = dbI.getSelect(comp_name[i]);
		}
//		request.setAttribute("comDetails", com);
		HttpSession session = request.getSession();
		session.setAttribute("compDetails", com);
		request.getRequestDispatcher("addShare.jsp").forward(request, response);
	}

}
