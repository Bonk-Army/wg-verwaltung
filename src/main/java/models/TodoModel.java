package models;

import utilities.SQLDCLogin;

import java.util.Date;

/**
 * Model for To-Do entry
 */
public class TodoModel {
    private String task;
    private String userId;
    private String wgId;
    private Date dateCreated;
    private Date dateDue;
    private Boolean isDone;
    private Boolean isActive;
    private String createdBy;
    private String uniqueID;
    private String assigneeUsername;
    private String creatorUsername;

    public TodoModel(String task, String userId, String wgId, Date dateCreated, Date dateDue, Boolean isDone, Boolean isActive, String createdBy, String uniqueID) {
        this.task = task;
        this.userId = userId;
        this.wgId = wgId;
        this.dateCreated = dateCreated;
        this.dateDue = dateDue;
        this.isDone = isDone;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.uniqueID = uniqueID;
        this.assigneeUsername = SQLDCLogin.getUsername(userId);
        this.creatorUsername = SQLDCLogin.getUsername(createdBy);
    }

    public String getTask() {
        return task;
    }

    public String getUserId() {
        return userId;
    }

    public String getWgId() {
        return wgId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateDue() {
        return dateDue;
    }

    public Boolean getDone() {
        return isDone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getAssigneeUsername() {
        return assigneeUsername;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }
}
