package Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateBook
 */
@WebServlet("/updateBook")
public class updateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		PrintWriter out = response.getWriter();
		response.setContentType("text/HTML");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			PreparedStatement ps = connect.prepareStatement("update books set bookName=?, author=?, publisher=?, edition=?, price=?, category=? where bookName=?");
			ps.setString(1,request.getParameter("bookName"));
			ps.setString(2,request.getParameter("author"));
			ps.setString(3,request.getParameter("publisher"));
			ps.setString(4,request.getParameter("edition"));
			ps.setInt(5,Integer.parseInt(request.getParameter("price")));
			ps.setString(6,request.getParameter("category"));
			ps.setString(7,request.getParameter("bookName"));
			int result = ps.executeUpdate();
			if(result==1) {
				out.println("<h1>Successfully Updated</h1>");
			}
			connect.close();
		}
		catch(Exception E) {
			out.println(E);
		}
		
	}

	}


