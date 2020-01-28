package com.create;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside doPost editServlet.");
		String requestdata = request.getReader().readLine();
		System.out.println("Updated Data = "+requestdata);
		Createdrive c = (Createdrive) ObjMap.getObjectFromJSON(requestdata, Createdrive.class);
		try {
			saveData1(c);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Map<String, String> saveData1(Createdrive c) throws ClassNotFoundException, SQLException {
		System.out.println("in edit query method");

		Connection con = DaoLayer.getConnection();
		
		PreparedStatement ps = con.prepareStatement("update cddata set cname=?, exp=?,ctc=?,jc=?, bond=?,position=?, ec=?,followup=? where id=?;");
		
		int id= c.getId();
		String cname = c.getCompanyname();
		int exp= c.getExp();
		int ctc=c.getCtc();
		int jc= c.getJoining();
		int bond= c.getBond();
		int position= c.getPosition();
		int ec = c.getEducation();
		Date followup = c.getFollowup();

        System.out.println("set values");  		
				
		ps.setObject(1, cname);
		ps.setObject(2, exp);
		ps.setObject(3, ctc);
		ps.setObject(4, jc);
		ps.setObject(5, bond);
		ps.setObject(6, position);
		ps.setObject(7, ec);
		ps.setObject(8, followup);
		ps.setObject(9,id );
		
		
		
		System.out.println("cname="+cname);
		System.out.println("exp="+exp);
		System.out.println("ctc="+ctc);
		System.out.println("jc="+jc);
		System.out.println("bond="+bond);
		System.out.println("position="+position);
		System.out.println("ec="+ec);
		System.out.println("followup="+followup);
		System.out.println("id="+id);
		
		
		
		System.out.println("before"); 
		int status=1;
		try
		{
			
		
		status = ps.executeUpdate();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		System.out.println("Status="+status);
		
		Map<String, String> map = new HashMap<>();

		if (status == 1) 
		{
			map.put("msg", "done");
		}
		else
		{
			map.put("msg", "Sorry.Eroor occur.");
		}
		return map;
}

}