package models;

/**
 * Model for shopping entries used in the backend exclusively
 */
public class ShoppingEntry {
    private String article;
    private String amount;
    private String dueDateString;
    private String createdDateString;
    private String createdBy;
    private String requestedBy;
    private String entryId;
    private boolean isDone;

    public ShoppingEntry(String article, String amount, String dueDateString, String createdDateString, String createdBy, String requestedBy, String entryId, boolean isDone) {
        this.article = article;
        this.amount = amount;
        this.dueDateString = dueDateString;
        this.createdDateString = createdDateString;
        this.createdBy = createdBy;
        this.requestedBy = requestedBy;
        this.entryId = entryId;
        this.isDone = isDone;
    }

    public String getArticle() {
        return article;
    }

    public String getAmount() {
        return amount;
    }

    public String getDueDateString() {
        return dueDateString;
    }

    public String getCreatedDateString() {
        return createdDateString;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public String getEntryId() {
        return entryId;
    }

    public boolean isDone() {
        return isDone;
    }
}
