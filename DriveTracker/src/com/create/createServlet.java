package com.create;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class create
 */
@WebServlet("/createServlet")
public class createServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Inside doPost method.");
		
		BufferedReader bf= request.getReader();
		String requestdata =bf.readLine();
		System.out.println("Angular Data = "+requestdata);
		
		Createdrive c = (Createdrive) ObjMap.getObjectFromJSON(requestdata, Createdrive.class);
		
		Map<String, String> map = null;
		try
		{
			map = DaoLayer.saveData(c);
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
				
		
        String responsedata = ObjMap.getJSONFromObject(map);
        
        PrintWriter pw=	response.getWriter();
		
	    pw.write(responsedata);
		
		response.flushBuffer();
	}

}