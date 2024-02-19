
package org.jspiders.jdbcApp;

import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.mysql.jdbc.PreparedStatement;

public class MultipleOperation_InOneProgram_By_usingSQL 
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("Welcome to Student Management App");
		Scanner sc=new Scanner(System.in);
		ResultSet rs=null;
		Connection con=null;
		java.sql.PreparedStatement pstmt=null;
		Statement stmt=null;
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manage","root","admin");
		while(true)
		{
			System.out.println("PRESS 1 TO ADD Student Details");
			System.out.println("PRESS 2 TO DELETE Student Details");
			System.out.println("PRESS 3 TO DISPLAY Student List");
			System.out.println("PRESS 4 TO Display Single Student Data");
			System.out.println("PRESS 5 TO Exit App");
			
			int c=sc.nextInt();
                        if(c>=5)
			{
				System.err.println("Enter the correct number");
			}
			if(c==1) 
			{
				//Add data
				String query1="insert into student_manage.student values(?,?,?,?)";
				pstmt=con.prepareStatement(query1);
				System.out.println("Enter id");
				int id=sc.nextInt();
				pstmt.setInt(1, id);
				System.out.println("Enter name");
				String name=sc.next();
				pstmt.setString(2, name);
				System.out.println("Enter phoneNumber");
				long phone=sc.nextLong();
				pstmt.setLong(3, phone);
				//System.out.println("Enter city Name ");
				String city=sc.next();
				pstmt.setString(4, city);
				pstmt.executeUpdate();
				System.out.println("record inserted...........");
				
			}
			else if(c==2) 
			{
				//delete the record
				String qry2="delete from student_manage.student where sid=?";
				pstmt=con.prepareStatement(qry2);
				System.out.println("Enter id");
				int idd=sc.nextInt();
				pstmt.setInt(1, idd);
				pstmt.executeUpdate();
				System.out.println("record Deleted...........");
				
			}
			else if(c==3) 
			{
				//display the record
				stmt=con.createStatement();
				rs=stmt.executeQuery("select * from student");
				while(rs.next()) 
				{
					int id=rs.getInt("sid");
					String name=rs.getString("sname");
					long phone=rs.getLong("sphone");
					String city=rs.getString("scity");
					System.out.println(id+" "+name+" "+phone+" "+city);
				}
				
			}
			else if(c==4)
			{
				//Select the particular record 
				pstmt=con.prepareStatement("select * from student where sid=?");
				System.out.println("Enter id");
				int id=sc.nextInt();
				pstmt.setInt(1, id);
				rs=pstmt.executeQuery();
				if(rs.next()) 
				{
					int idd=rs.getInt("sid");
					String namee=rs.getString("sname");
					long phonee=rs.getLong("sphone");
					String cityy=rs.getString("scity");
					System.out.println(idd+" "+namee+" "+phonee+" "+cityy);
				}
				else 
				{
					System.err.println("Enter correct ID");
				}
			}
			else if(c==5) 
			{
				//exit
				break;
			}
		}
		System.out.println("Thank you using my Application>>>>>");
		System.out.println("see you soon<<<<'.'>>>>");
		pstmt.close();
		con.close();
	}
}
