package Database;

import java.beans.Statement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class details
 */
@WebServlet("/details")
public class details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("text/HTML");
		PrintWriter out = response.getWriter();
		try {
			// load the driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Connect to the database
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			
			// Creating statement object
			Statement statement = (Statement) connect.createStatement();
			String query = "select * from Books";
			
			//Process Query
			ResultSet rs =((java.sql.Statement) statement).executeQuery(query);
			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<td>Book Name</td>");
			out.println("<td>Author</td>");
			out.println("<td>Publisher</td>");
			out.println("<td>Edition</td>");
			out.println("<td>Price</td>");
			out.println("<td>Category</td>");
			out.println("</tr>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getString(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getInt(5)+"</td>");
				out.println("<td>"+rs.getString(6)+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			connect.close();
		}
		catch(Exception e){
			out.println(e);
		}
	}

}
