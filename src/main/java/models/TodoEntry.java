package models;

/**
 * Model for todo entries used in the backend exclusively
 */
public class TodoEntry {
    private String task;
    private String dueDateString;
    private String createdDateString;
    private String assignee;
    private String creator;
    private boolean isDone;
    private String entryId;

    public TodoEntry(String task, String dueDateString, String createdDateString, String assignee, String creator, boolean isDone, String entryId) {
        this.task = task;
        this.dueDateString = dueDateString;
        this.createdDateString = createdDateString;
        this.assignee = assignee;
        this.creator = creator;
        this.isDone = isDone;
        this.entryId = entryId;
    }

    public String getTask() {
        return task;
    }

    public String getDueDateString() {
        return dueDateString;
    }

    public String getCreatedDateString() {
        return createdDateString;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getCreator() {
        return creator;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getEntryId() {
        return entryId;
    }
}
