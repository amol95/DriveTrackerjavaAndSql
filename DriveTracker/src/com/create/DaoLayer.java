package com.create;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DaoLayer {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "root");
		return con;
	}
	
	public static Map<String, String> saveData(Createdrive c) throws ClassNotFoundException, SQLException {

		Connection con = getConnection();
		
		PreparedStatement ps = con.prepareStatement("insert into cddata(cname,exp,ctc,jc,bond,position,ec,followup) values(?,?,?,?,?,?,?,?)");
		
		String cname = c.getCompanyname();
		int exp= c.getExp();
		int ctc=c.getCtc();
		int jc= c.getJoining();
		int bond= c.getBond();
		int position= c.getPosition();
		int ec = c.getEducation();
		Date followup = c.getFollowup();
				
		ps.setObject(1, cname);
		ps.setObject(2, exp);
		ps.setObject(3, ctc);
		ps.setObject(4, jc);
		ps.setObject(5, bond);
		ps.setObject(6, position);
		ps.setObject(7, ec);
		ps.setObject(8, followup);
		
		int status = ps.executeUpdate();
		
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
	
	public static ArrayList<Createdrive> getUserDataFromDB() throws ClassNotFoundException, SQLException 
	{

		Connection con = getConnection();

		PreparedStatement ps = con.prepareStatement("select * from cddata;");

		ResultSet rs = ps.executeQuery();

		ArrayList<Createdrive> list = new ArrayList<>();
		

		while (rs.next()) {
			Createdrive c = new Createdrive();
			c.setid(rs.getInt(1));
			c.setCompanyname(rs.getString(2));
			c.setExp(rs.getInt(3));
			c.setCtc(rs.getInt(4));
			c.setJoining(rs.getInt(5));
			c.setBond(rs.getInt(6));
			c.setPosition(rs.getInt(7));
			c.setEducation(rs.getInt(8));
			c.setFollowup(rs.getDate(9));
			
			list.add(c);
		}
		return list;
	}
	
	/*
	 * public static Map<String, String> deleteEmpData(Createdrive c) throws
	 * ClassNotFoundException, SQLException {
	 * 
	 * Connection con = getConnection();
	 * 
	 * PreparedStatement ps = con.prepareStatement("delete from cddata where id=?");
	 * 
	 * ps.setObject(1, c.getId());
	 * 
	 * int status = ps.executeUpdate();
	 * 
	 * Map<String, String> mp = new HashMap<>();
	 * 
	 * if (status == 1) { mp.put("msg", "done"); } else { mp.put("msg", "sorry"); }
	 * 
	 * return mp;
	 * 
	 * }
	 * 
	 */	
	public static Map<String, String> updateData(Createdrive c) {

		Map<String, String> map=new HashMap<String, String>();
		
		try {
			Connection con = getConnection();

			PreparedStatement ps = con.prepareStatement("update cddata set cname=?,exp=?,ctc=?,joining=?,bond=?,position=?,education=?,followup=? where id=?");

			ps.setString(1, c.getCompanyname());
			ps.setInt(2,c.getExp());
			ps.setInt(3, c.getCtc());
			ps.setInt(4, c.getJoining());
			ps.setInt(5, c.getBond());
			ps.setInt(6, c.getPosition());
			ps.setInt(7, c.getEducation());
			ps.setDate(8, c.getFollowup());
			
			int status = ps.executeUpdate();

			if (status == 1)
			{
				map.put("msg", "done");
			}
			else
			{
				map.put("msg", "sorry");
			}

		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return map;
	}
	
}