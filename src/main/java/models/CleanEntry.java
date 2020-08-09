package models;

/**
 * Model for clean entries used in the backend exclusively
 */
public class CleanEntry {
    private String task;
    private String mondayAssignee;
    private String tuesdayAssignee;
    private String wednesdayAssignee;
    private String thursdayAssignee;
    private String fridayAssignee;
    private String saturdayAssignee;
    private String sundayAssignee;
    private String entryId;

    public CleanEntry(String task, String mondayAssignee, String tuesdayAssignee, String wednesdayAssignee, String thursdayAssignee, String fridayAssignee, String saturdayAssignee, String sundayAssignee, String entryId) {
        this.task = task;
        this.mondayAssignee = mondayAssignee;
        this.tuesdayAssignee = tuesdayAssignee;
        this.wednesdayAssignee = wednesdayAssignee;
        this.thursdayAssignee = thursdayAssignee;
        this.fridayAssignee = fridayAssignee;
        this.saturdayAssignee = saturdayAssignee;
        this.sundayAssignee = sundayAssignee;
        this.entryId = entryId;
    }

    public String getTask() {
        return task;
    }

    public String getMondayAssignee() {
        return mondayAssignee;
    }

    public String getTuesdayAssignee() {
        return tuesdayAssignee;
    }

    public String getWednesdayAssignee() {
        return wednesdayAssignee;
    }

    public String getThursdayAssignee() {
        return thursdayAssignee;
    }

    public String getFridayAssignee() {
        return fridayAssignee;
    }

    public String getSaturdayAssignee() {
        return saturdayAssignee;
    }

    public String getSundayAssignee() {
        return sundayAssignee;
    }

    public String getEntryId() {
        return entryId;
    }
}
