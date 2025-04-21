package com.tracker.json;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tracker.classes.Task;
import com.tracker.enums.Status;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ManagementJson {
	
	public void writeFileJson(List<Task> listTask) {
		JSONArray jsonArray = new JSONArray();
		
		for (Task task : listTask) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("id", task.getId());
			jsonObject.put("description", task.getDescription());
			jsonObject.put("status", task.getStatus());
			jsonObject.put("createdAt", task.getCreatedAt());
			jsonObject.put("updateAt", task.getUpdatedAt());
			
			jsonArray.put(jsonObject);
		}
		
		try {
			Files.write(Paths.get("output.json"), jsonArray.toString().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Task> getTasksFromJsonFile() {
		List<Task> tasks = new ArrayList<Task>();
		try {
			String filePath = "output.json";
			Path path = Paths.get(filePath);
	        String jsonString;
	        
	        File file = new File(filePath);
	        
            if (file.exists()) {
                jsonString = new String(Files.readAllBytes(path));
            } else {
            	Files.write(path, "[]".getBytes());
                jsonString = "[]";
            }
			
	        JSONArray jsonArray = new JSONArray(jsonString);
	        
	        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
	
	        for (int i = 0; i < jsonArray.length(); i++) {
	        	Task task = new Task();
	        	JSONObject jsonObject = jsonArray.getJSONObject(i);
	            int id = jsonObject.getInt("id");
	            String description = jsonObject.getString("description");
	            Status status = jsonObject.getEnum(Status.class, "status");
	            
	            String createdAtStr = jsonObject.getString("createdAt");
	            String updateAtStr = jsonObject.optString("updateAt");

	            Date createdAt = formatter.parse(createdAtStr);
	            
	            Date updateAt = null;
	            
	            if(!updateAtStr.equals("")) {
	            	updateAt = formatter.parse(updateAtStr);
	            }
	            
	            task.setId(id);
	            task.setDescription(description);
	            task.setStatus(status);
	            task.setCreatedAt(createdAt);
	            task.setUpdatedAt(updateAt);
	            
	            tasks.add(task);
	        }
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		return tasks;
	}
	
}