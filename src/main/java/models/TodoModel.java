package models;

import java.util.Date;

/**
 * Model for To-Do entry
 */
public class TodoModel {
    String task;
    String userId;
    String wgId;
    Date dateCreated;
    Date dateDue;
    Boolean isDone;

    public TodoModel(String task, String userId, String wgId, Date dateCreated, Date dateDue, Boolean isDone) {
        this.task = task;
        this.userId = userId;
        this.wgId = wgId;
        this.dateCreated = dateCreated;
        this.dateDue = dateDue;
        this.isDone = isDone;
    }
}
