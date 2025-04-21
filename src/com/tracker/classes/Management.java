package com.tracker.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.tracker.enums.Status;
import com.tracker.json.ManagementJson;

public class Management {
	
	private Scanner scanner = new Scanner(System.in);
	private ManagementJson managementJson = new ManagementJson();
	private List<Task> taskList = managementJson.getTasksFromJsonFile();
	private Integer id = taskList.size() != 0 ? (taskList.getLast().getId()+1) : 0;
	private boolean exit = false;
	
	public void input() {
		System.out.print("task-cli ");
		String input = scanner.nextLine();
		
		if(input.equals("exit")) {
			setExit(true);
			return;
		}
		
		int index = input.indexOf(" ");
		
		String operation = "",
			   argument = "";
		
		if(index != -1) {
			operation = input.substring(0, index);
			argument = input.substring(index+1);
		} else {
			operation = input;
		}
		
		if(operation.equals("add")) {
			addTask(argument);
			managementJson.writeFileJson(getTaskList());
		} else if(operation.equals("delete")) {
			
			int idDelete = Integer.parseInt(argument);
			
			deleteTask(idDelete);
			
			managementJson.writeFileJson(getTaskList());
			
		} else if(operation.equals("update")) {
			
			int indexArgument = argument.indexOf(" ");
			
			if(indexArgument != -1) {
				int idUpdate = Integer.parseInt(argument.substring(0, indexArgument));
				String descriptionUpdate = (argument.substring(indexArgument+1));
				updateTask(idUpdate, descriptionUpdate);
			}
			
			managementJson.writeFileJson(getTaskList());
			
		}else if(operation.equals("mark-todo")) {
			updateStatus(Status.TODO, argument);
		} else if(operation.equals("mark-in-progress")) {
			updateStatus(Status.IN_PROGRESS, argument);
		} else if(operation.equals("mark-done")) {
			updateStatus(Status.DONE, argument);
		} else if (operation.equals("list") && argument.equals("done")) {
		    printTaskList(getTaskListByMark(Status.DONE));
		} else if (operation.equals("list") && argument.equals("todo")) {
			printTaskList(getTaskListByMark(Status.TODO));
		} else if (operation.equals("list") && argument.equals("in-progress")) {
			printTaskList(getTaskListByMark(Status.IN_PROGRESS));
		}  else if (operation.equals("list")) {
			printTaskList(getTaskList());
		} else {
			validationOperation(operation);
		}
				
	}
	
	private void addTask(String description) {
		Task task = new Task();
		if(id == 0) {
			task.setId(id);
		} else {
			task.setId(id);
		}
		id++;
		task.setDescription(description);
		task.setStatus(Status.IN_PROGRESS);
		task.setCreatedAt(new Date());
		taskList.add(task);
	}
	
	public void updateTask(int id, String descriptionUpdate) {
		
		Task task = selectTaskFromId(id);
		
		if(task != null) {
			task.setDescription(descriptionUpdate);
			task.setUpdatedAt(new Date());
		} else {
			System.out.println("Task Not Exist!!");
		}
	}
	
	public void updateStatus(Status status, String argument) {
		int idTask = Integer.parseInt(argument);
		Task task = selectTaskFromId(idTask);
		
		if(task != null) {
			task.setStatus(status);
			task.setUpdatedAt(new Date());
			managementJson.writeFileJson(getTaskList());
		} else {
			System.out.println("Task Not Exist!!");
		}
	}
	
	public void deleteTask(int id) {
		Task task = selectTaskFromId(id);
		
		if(task != null) {
			getTaskList().remove(task);
		} else {
			System.out.println("Task Not Exist!!");
		}
	}
	
	public Task selectTaskFromId(int id) {
		for (Task task : taskList) {
			if(task.getId() == id) {
				return task;
			}
		}
		return null;
	}

	public List<Task> getTaskList() {
		return taskList;
	}
	
	public List<Task> getTaskListByMark(Status status) {
		List<Task> tmp = new ArrayList<Task>();
		for (Task task : getTaskList()) {
			if(task.getStatus().equals(status)) {
				tmp.add(task);
			}
		}
		
		return tmp;
	}
	
	public void printTaskList(List<Task> tasks) {
		
		if(tasks.size() != 0) {
			for (Task task : tasks) {
				System.out.println(task);
			}
		} else {
			System.out.println("Tasks Don't Exists");
		}
		
	}
	
	public boolean validationOperation(String operation) {
		
		boolean isValid = false;
		
		if(!operation.equals("add") && !operation.equals("update") && !operation.equals("delete")
			&& !operation.equals("mark-in-progress") && !operation.equals("mark-done")
			&& !operation.equals("mark-todo") && !operation.equals("list")
			&& !operation.equals("list done") && !operation.equals("list todo")
			&& !operation.equals("list in-progress")) {
			System.out.println("Operation Not Valid!!");
			isValid = true;
		}
		return isValid;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
	
}
