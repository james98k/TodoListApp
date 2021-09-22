package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
		
	}

	public void addItem(TodoItem t) {
		list.add(t); 
		System.out.println("Todo Item Added");
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
		System.out.println("Todo Item Deleted");
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
		System.out.println("Todo Item Updated");
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		System.out.println("List sorted by Name");
		Collections.sort(list, new TodoSortByName());
		

	}

	public void listAll() {
		System.out.println("\n"
				+ "inside list_All method\n");
		for (TodoItem myitem : list) {
			System.out.println(myitem.getTitle() + myitem.getDesc());
		}
	}
	
	public void reverseList() {
		System.out.println("List in Reverse Order");
		Collections.reverse(list);
	}

	public void sortByDate() {
		System.out.println("List Sorted by Date");
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
