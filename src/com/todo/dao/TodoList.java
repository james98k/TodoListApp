package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}
	public int getSize() {
		int count = 0;
		for(TodoItem item : list) {
			count++;
		}
		return count;
	}

	public void addItem(TodoItem t) {
		list.add(t); 
		System.out.println("Todo Item Added");
	}
	public void addItemPre(TodoItem t) {
		list.add(t); 
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
		for (TodoItem myitem : list) {
			System.out.println("["+myitem.getTitle() +"] "+ myitem.getDesc()+" - "+ myitem.getDueDate()+" " + myitem.getCurrent_date());
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
	public void getIndexItem(int index) {
		int count  = 0;
		for(TodoItem item : list) {
			if(count == index) {
				System.out.println(item.toString());
			}
			
		}
	}
	public String toString(TodoItem myitem) {
//		return "TodoList [list=" + list + "]";
		return "["+myitem.getTitle() +"] "+ myitem.getDesc()+" - "+ myitem.getDueDate()+" " + myitem.getCurrent_date();
	}
	public TodoItem getIndex(int value) {
		int count = 0;
		for(TodoItem item : list) {
			if(count == value) {
				return item;
			}
			count++;
		}
		return null;
		
	}
	
}
