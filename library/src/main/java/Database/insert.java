package Database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insert
 */
@WebServlet("/insert")
public class insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

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
			out.println("Successfully Connected");
			
			// Creating statement object
			PreparedStatement ps = connect.prepareStatement("insert into books values(?,?,?,?,?,?)");
			ps.setString(1,request.getParameter("bookName"));
			ps.setString(2,request.getParameter("author"));
			ps.setString(3,request.getParameter("publisher"));
			ps.setString(4,request.getParameter("edition"));
			ps.setInt(5,Integer.parseInt(request.getParameter("price")));
			ps.setString(6,request.getParameter("category"));
			ps.executeUpdate();
			out.println("<h1>Successfully Inserted.</h1>");
			ps.close();
			connect.close();
		}
		
		catch(Exception e){
			out.println(e);
		}
		
	}

}
