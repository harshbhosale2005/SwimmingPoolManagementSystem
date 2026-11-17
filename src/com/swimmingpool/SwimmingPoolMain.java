package com.swimmingpool;

import java.util.Scanner;

public class SwimmingPoolMain {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		SwimmingpoolInterface swmpI=new SwimmingPool();
		
		int ch;
		
		do {
			System.out.println("====================================");
			System.out.println("SWIMMING POOL MANAGEMENT SYSTEM");
			System.out.println("====================================");
			
			System.out.println("1. Create Database");
			System.out.println("2. Create Table");
			System.out.println("3. Insert Member");
			System.out.println("4. Display All Members");
			System.out.println("5. Search Member");
			System.out.println("6. Update Member");
			System.out.println("7. Delete Member");
			System.out.println("8. Exit");
			System.out.println("9. Drop Table");
			System.out.println("10. Drop Database");
			
			System.out.println("=======================================");
			System.out.print("Enter Your Choice:");
			ch=sc.nextInt();
			
			switch(ch) {
			
			case 1:
				swmpI.createDatabase();
				break;
				
			case 2:
				swmpI.createTable();
				break;
				
			case 3:
				swmpI.insertMember();
				break;
				
			case 4:
				swmpI.displayMember();
				break;
				
			case 5:
				swmpI.searchMember();
				break;
				
			case 6:
				swmpI.updateMember();
				break;
				
			case 7:
				swmpI.deletMember();
				break;
				
			case 8:
				System.out.println("Thank You !");
				break;
				
			case 9:
				swmpI.dropTable();
				break;
				
			case 10:
				swmpI.dropDatabase();
				break;
				
			default:
				System.out.println("Invalid Input ! Try Again.");	
				
			}
			
		}while(ch != 8);
		
		sc.close();
		
	}

}
