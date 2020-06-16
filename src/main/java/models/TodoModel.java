package models;

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

    public TodoModel(String task, String userId, String wgId, Date dateCreated, Date dateDue, Boolean isDone) {
        this.task = task;
        this.userId = userId;
        this.wgId = wgId;
        this.dateCreated = dateCreated;
        this.dateDue = dateDue;
        this.isDone = isDone;
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
}
