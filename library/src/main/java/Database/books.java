package Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class books
 */
@WebServlet("/books")
public class books extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public books() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/HTML");
		PrintWriter out = response.getWriter();
		try {
			// load the driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Connect to the database
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
			
			// Creating statement object
//			String query = "Select * from books";
			PreparedStatement ps = connect.prepareStatement("Select * from books");
//			PreparedStatement ps = connect.prepareStatement("select * from ?");
//			ps.setString(1,"books");
			
			//Process Query
			ResultSet rs =ps.executeQuery();
			out.println("<table border=1 width=100% text-align=center>");
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


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
