package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "student");
		Menu.displaymenu();
		do {
//			Menu.displaymenu();
			isList = false;
			String choice = sc.next();
			
			switch (choice) {
			case "help":
				Menu.displaymenu();
				break;
			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				break;

			case "exit":
				TodoUtil.saveList(l, "student");
				
				quit = true;
				break;
			case "find":
				TodoUtil.findList(l);
			default:
				System.out.println("##please enter one of the above mentioned command, (press \"help for instructions\")");
				break;
			}
//			System.out.println("\nhint : help");
			if(isList) l.listAll();
		} while (!quit);
	}
}
