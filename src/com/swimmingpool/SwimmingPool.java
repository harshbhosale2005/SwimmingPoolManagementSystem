package com.swimmingpool;

import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SwimmingPool implements SwimmingpoolInterface{
	
	Scanner sc=new Scanner(System.in);

	@Override
	public void createDatabase() {
		
		String query="create database if not exists swimmingpool_db";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/",
					"root",
					"root"
					);
			Statement st=con.createStatement();
			st.execute(query);
			
			System.out.println("Database Created Successfully! ");
			
			st.close();
			con.close();	
			
			
		}catch(ClassNotFoundException e) {
			
			System.out.println("Class Not Found Exception.");
			e.printStackTrace();
			
		}catch(SQLException e) {
			System.out.println("Error Created.");
		}
		
		
		
		
	}

	@Override
	public void createTable() {
		
		try {
		String query="create table if not exists swimming_pool("
				+ "member_id int primary key auto_increment,"
				+ "member_name varchar(50),"
				+ "age int,"
				+ "gender varchar(10),"
				+ "phone varchar(15),"
				+ "swimming_plan varchar(50),"
				+ "trainer_name varchar(50),"
				+ "session_date date,"
				+ "fees double,"
				+ "attendance varchar(20)"
				+")";
		
		Connection  con=DBConnection.getConnection();
		
		Statement st=con.createStatement();
				
		st.execute(query);
		
		System.out.println("Table Created Successfully!");
				
				
		}catch (SQLException e) {
			
			System.out.println("Error While Creating Table.");
			
			e.printStackTrace();
		}

	
		
	}

	
	
	
	@Override
	public void dropTable() {
		
		
		String sql="drop table if exists swimming_pool";
		
		try {
			
			Connection con=DBConnection.getConnection();
			
			if(con==null) {
				return;
			}
			
			Statement st=con.createStatement()
;
			st.executeUpdate(sql); 
			
			System.out.println("Table Dropped Successfully!");
			
			st.close();
			con.close();
			
		}catch(SQLException e) { 
			
			System.out.println("Error Droping Table.");
			
			e.printStackTrace();
		}	
		
	}

	
	
	
	@Override
	public void dropDatabase() {
		
		
		String delete="drop database if exists swimmingpool_db";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/",
					"root",
					"root");
			
			Statement st=con.createStatement();
			
			st.executeUpdate(delete);
			
			System.out.println("Database Dropped Successfully !");
			
			st.close();
			con.close();
			
			
		}catch(ClassNotFoundException e) {
			
			System.out.println(" Driver Class Not Found");
			
			e.printStackTrace();
			
		}catch(SQLException e) {
			
			System.out.println("error Occured While Dropping Table!");
			
			e.printStackTrace();
			
		}	
		
		
	}

	@Override
	public void insertMember() {
		
		String query="insert into swimming_pool "
				+ "(member_name, age, gender, phone, swimming_plan,trainer_name, session_date, fees, attendance) "
				+"values (?,?,?,?,?,?,?,?,?)";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DBConnection.getConnection();	
			
			if(con==null) {
				return;
			}
			
			PreparedStatement ps=con.prepareStatement(query);
			
			System.out.print("Enter How Many Members Do you want to Insert: ");
			int num=sc.nextInt();
			sc.nextLine();
			
			for(int i=0;i<num;i++) {
				
				System.out.println(" ********* Enter Details of Member "+(i+1)+" ********** ");
				
				
				System.out.print("Enter Member Name: ");
				String name=sc.nextLine();
				
				System.out.print("Enter Your Age: ");
				int age=sc.nextInt();
				sc.nextLine();
				
				System.out.print("Enter Gender (M/F): ");
				String gender = sc.nextLine();
				
				System.out.print("Enter PhoneNo: ");
				String phone = sc.nextLine();
				
				System.out.print("Enter swimming Plan: ");
				String plan=sc.nextLine();
				
				System.out.print("Enter Trainer Name: ");
				String trainer=sc.nextLine();
				
				System.out.print("Enter Session Date (yyyy-mm-dd): ");
				String date=sc.nextLine();
				
				System.out.print("Enter Fees: ");
				double fee=sc.nextDouble();
				sc.nextLine();
				
				System.out.print("Enter Attendance: ");
				String attendance=sc.nextLine();
				
				
				ps.setString(1, name);
				ps.setInt(2, age);
				ps.setString(3, gender);
				ps.setString(4, phone);
				ps.setString(5, plan);
				ps.setString(6, trainer);
				ps.setDate(7, Date.valueOf(date));
				ps.setDouble(8, fee);
				ps.setString(9, attendance);
				
				
				int res=ps.executeUpdate();
				
				if(res>0) {
					System.out.println("Member inserted Successfully!");
				}else {
					System.out.println(" Member Not Inserted.");
				}
				
				
			}
			
			ps.close();
			con.close();
				
			
		}catch(ClassNotFoundException e) {
			
			System.out.println(" Driver Class Not Found");
			
			e.printStackTrace();
			
		}catch(SQLException e) {
			
			System.out.println("error Occured While Inserting Member into Table!");
			
			e.printStackTrace();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
			
	}

	@Override
	public void displayMember() {
		
		String query="select * from swimming_pool";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DBConnection.getConnection();
			
			if(con==null) {
				return;
			}
			
			PreparedStatement ps=con.prepareStatement(query);
			
			ResultSet rs=ps.executeQuery();
			
			
			System.out.println("====================DETAILS OF ALL MEMBERS====================");
			
			while(rs.next()) {
				
				System.out.println("Member ID: "+ rs.getInt("member_id"));
				
				System.out.println("Member Name: "+rs.getString("member_name"));
				
				System.out.println("Member Age: "+rs.getInt("age"));
				
				System.out.println("Member Gender: "+rs.getString("gender"));
				
				System.out.println("Member's PhoneNo: "+rs.getString("phone"));
				
				System.out.println("Swimming Plan: "+rs.getString("swimming_plan"));
				
				System.out.println("Trainer Name: "+rs.getString("trainer_name"));
				
				System.out.println("Session Date: "+rs.getDate("session_date"));
				
				System.out.println("Fees: "+rs.getDouble("fees"));
				
				System.out.println("Attendance: "+rs.getString("attendance"));
				
				
				System.out.println("-----------------------------------");
			}
			
			rs.close();
			ps.close();
			con.close();
			
			
		}catch(ClassNotFoundException e) {
			
			System.out.println(" Driver Class Not Found");
			
			e.printStackTrace();
			
		}catch(SQLException e) {
			
			System.out.println("error Occured While Displaying Members. ");
			
			e.printStackTrace();
			
		}
		
		
		
	}

	@Override
	public void searchMember(){
		
		String search="select * from swimming_pool where member_id=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		
			Connection con=DBConnection.getConnection();
			
			if(con==null) {
				return;
				}
			
			System.out.print("Enter Member Id: ");
			int id=sc.nextInt();
			sc.nextLine();
			
			PreparedStatement ps=con.prepareStatement(search);
			
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			
			
			System.out.println("====================DETAILS OF  MEMBER====================");
			
			
			if(rs.next()) {
				
				System.out.println("Member ID: "+ rs.getInt("member_id"));
				
				System.out.println("Member Name: "+rs.getString("member_name"));
				
				System.out.println("Member Age: "+rs.getInt("age"));
				
				System.out.println("Member Gender: "+rs.getString("gender"));
				
				System.out.println("Member's PhoneNo: "+rs.getString("phone"));
				
				System.out.println("Swimming Plan: "+rs.getString("swimming_plan"));
				
				System.out.println("Trainer Name: "+rs.getString("trainer_name"));
				
				System.out.println("Session Date: "+rs.getDate("session_date"));
				
				System.out.println("Fees: "+rs.getDouble("fees"));
				
				System.out.println("Attendance: "+rs.getString("attendance"));
				
				
				System.out.println("-----------------------------------");
			}else {
				
				System.out.println("Member Not Found. ");
				
			}
			
			rs.close();
			ps.close();
			con.close();
			
		
		} catch (ClassNotFoundException e) {
			
			System.out.println(" Driver Class Not Found");
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println("error Occured While Searching Member. ");
			
			e.printStackTrace();
		}	
		
	}

	@Override
	public void updateMember() {
		
		String update="update swimming_pool set "
				+ "member_name=?,"
				+ "age=?,"
				+ "gender=?,"
				+ "phone=?,"
				+ "swimming_plan=?,"
				+ "trainer_name=?,"
				+ "session_date=?,"
				+ "fees=?,"
				+ "attendance=?"
				+ "where member_id=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DBConnection.getConnection();

            if (con==null) {
                return;
            }
            
            
PreparedStatement ps=con.prepareStatement(update);
            
            System.out.print("Enter Member Id to Update: ");
            int id=sc.nextInt();
            sc.nextLine();
			
			System.out.print("Enter New Member Name : ");
			String name=sc.nextLine();
			
			System.out.print("Enter Your New Age: ");
			int age=sc.nextInt();
			sc.nextLine();
			
			System.out.print("Enter  New Gender (M/F): ");
			String gender=sc.next();
			
			System.out.print("Enter New PhoneNo: ");
			String phone=sc.next();
			sc.nextLine();
			
			System.out.print("Enter New swimming Plan: ");
			String plan=sc.nextLine();
			
			System.out.print("Enter New Trainer Name: ");
			String trainer=sc.nextLine();
			
			System.out.print("Enter New Session Date (yyyy-mm-dd): ");
			String date=sc.nextLine();
			
			System.out.print("Enter New Fees: ");
			double fee=sc.nextDouble();
			sc.nextLine();
			
			System.out.print("Enter New Attendance: ");
			String atten=sc.nextLine();
			
			
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, gender);
			ps.setString(4, phone);
			ps.setString(5, plan);
			ps.setString(6, trainer);
			ps.setDate(7, Date.valueOf(date));
			ps.setDouble(8, fee);
			ps.setString(9, atten);
			ps.setInt(10, id);
			
			int res=ps.executeUpdate();
			
			System.out.println(res+" Rows affected");
			
			ps.close();
			con.close();		
			
		}catch (ClassNotFoundException e) {
			
			System.out.println(" Driver Class Not Found");
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			System.out.println("error Occured While Updating Member. ");
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void deletMember() {
		
		String delete="delete from swimming_pool where member_id=?";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DBConnection.getConnection();
			
			if(con==null) {
				
				return;
			}
			
			System.out.print("Enter Member ID To Delete: ");
			int id=sc.nextInt();
			sc.nextLine();
			
			PreparedStatement ps=con.prepareStatement(delete);
			
			ps.setInt(1, id);
			
			int res=ps.executeUpdate();
			
			System.out.println(res +" rows affected! ");
			
			ps.close();
			con.close();
			
			
		}catch (ClassNotFoundException e) {
			
			System.out.println(" Driver Class Not Found");
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			System.out.println("error Occured While Searching Member. ");
			
			e.printStackTrace();
		}	
		
		
	}

}
