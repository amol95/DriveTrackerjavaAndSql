package com.create;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	int c;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestData = request.getReader().readLine();
		 c= Integer.parseInt(requestData);
		 
			try {
				deleteInDB();
			} catch (ClassNotFoundException | SQLException e) {
			
				e.printStackTrace();
			}
		
		 System.out.println(c);
	}
	
	void deleteInDB() throws ClassNotFoundException, SQLException
	{
		Connection con = DaoLayer.getConnection();

		PreparedStatement ps = con.prepareStatement("delete from cddata where id=?");

		ps.setObject(1, c);

		int status = ps.executeUpdate();
		System.out.println("Status="+status);
	}

}
