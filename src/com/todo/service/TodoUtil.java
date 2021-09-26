package com.todo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

import jdk.internal.jshell.tool.resources.l10n;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, dueDate;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Create item Section\n"
				+ "enter the Category\n");
		category = sc.nextLine().trim();
		
		System.out.println("enter title");
		title = sc.nextLine().trim();
		
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		System.out.println("enter the description");
		desc = sc.nextLine().trim();
		
		System.out.println("enter due date");
		dueDate = sc.nextLine().trim();
		System.out.println(dueDate);
		
		TodoItem t = new TodoItem(category, title, desc, dueDate);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
//		String title = sc.nextLine();
		System.out.println("Enter Item's Indexd to delete");
		int index = sc.nextInt();
	
		
		int count = 0;
		for(TodoItem item : l.getList()) {
			if(index == count) {
				System.out.println(item.toString());
				
				System.out.println("Do you want to delete this item? Y/N");
				String yn = sc.next().trim();
				if(yn.equalsIgnoreCase("y")) {
					l.deleteItem(item);
					System.out.println("index deleted");
				}
				else {
					
					System.out.println("deleted canceled, system return to main");
					return;
				}
				
			}
			count++;
		}
		
//		
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
//		System.out.println("\n"
//				+ "========== Edit Item Section\n"
//				+ "enter the title of the item you want to update\n"
//				+ "\n");
		System.out.println("choose number of item");
		int num = sc.nextInt();
		String tp = sc.nextLine();
		if(num > l.getSize()) {
			System.out.println("there are no number matching your request");
			System.out.println("edit terminated");
			return;
		}
		
		
		System.out.println("enter the new title of the item");
		String new_title = sc.nextLine().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			System.out.println("edit terminated");
			return;
		}
		
		System.out.println("enter category");
		String new_category = sc.nextLine().trim();
		
		System.out.println("enter the new description ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("enter new due date");
		String new_dueDate = sc.nextLine().trim();
		
		int count = 0;
		for (TodoItem item : l.getList()) {
			if(count == num) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_dueDate);
				l.addItem(t);
				System.out.println("Item updated");
			}
			count++;
			
			
		}

	}

	public static void listAll(TodoList l) {
		int count = 0;
		System.out.println("[전체목록] [ ListCount : " + l.getSize() + "]");
		
		for (TodoItem item : l.getList()) {
			System.out.println(count +". [ "+ item.getCategory() +" ] " + item.getTitle() + " " + item.getDesc() + " - "+item.getDueDate()+" - " + item.getCurrent_date());
			count++;
		}	
		
		
		
	}
	
	public static void saveList(TodoList l, String filename) {
		String file_n = filename + ".txt";
		
		try {
			FileWriter file = new FileWriter(file_n);
//			file.write("이게 되니...");
			for(TodoItem item : l.getList()) {
				file.write(item.toSaveString());
			}
			System.out.println("TodoList Data is Safely Stored");
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
	public static void loadList(TodoList l, String filename) {
//		read file
		String file_n = filename + ".txt";
		try {
			BufferedReader bfr = new BufferedReader(new FileReader(file_n));
			String singleLn;
			int count = 0;
			while((singleLn = bfr.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(singleLn, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String dueDate = st.nextToken();
				String date = st.nextToken();
			
				
				TodoItem t = new TodoItem(category, title, desc, date, dueDate);
				
				l.addItemPre(t);
				count++;
			}
			bfr.close();
			System.out.println(count+" Items Added to List\n");
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void findList(TodoList l) {	
		Scanner sc = new Scanner(System.in);
		String s_input = sc.next().trim();
		System.out.println("token to find : " + s_input);
		System.out.print("the index of your item : ");
		
		for(TodoItem item : l.getList()) {			
			
			String s = item.getDesc().toString();
			String title = item.getTitle().toString();
			
			StringTokenizer st_title = new StringTokenizer(title, " ");
			StringTokenizer st = new StringTokenizer(s , " ");
			
			List<Integer> tempList = new ArrayList<Integer>();
			
			
			
			while(st.hasMoreTokens()) {
				String a = st.nextToken();
//				String titleToken = st_title.nextToken();
//				System.out.println("token value : "+ a);
				if(a.contains(s_input)) {
//					System.out.println("token true : " + a);
//					return item value]
					
					System.out.print(" "+l.indexOf(item));
//					System.out.println(item.toFormatString());
					
					if(tempList.contains(l.indexOf(item))){
						continue;
					}
					else {
						tempList.add(l.indexOf(item));
//						System.out.println("value added");
					}
				}				
			}
			while(st_title.hasMoreElements()) {
				String titleToken = st_title.nextToken();
				if(titleToken.contains(s_input)) {
					
					System.out.print(" "+l.indexOf(item));
//					System.out.println(item.toFormatString());
					
					if(tempList.contains(l.indexOf(item))){
						continue;
					}
					else {
						tempList.add(l.indexOf(item));
//						System.out.println("value added");
					}
				}
			}
//			System.out.println("the index of your searching value is ");
//			for(Integer i : tempList) {
//				System.out.print(" " + i );
//			}
//			-> return index;
			
			
		}
		System.out.println("");
//		sc.close();
	}
}
