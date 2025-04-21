package com.tracker.classes;

import java.util.Date;
import java.util.Objects;

import com.tracker.enums.Status;

public class Task {
	
	private int id;
	private String description;
	private Status status;
	private Date createdAt;
	private Date updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(createdAt, description, id, status, updatedAt);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(description, other.description)
				&& id == other.id && status == other.status && Objects.equals(updatedAt, other.updatedAt);
	}
	

}
