package models;

import java.util.Date;

/**
 * Model for financial entries used in the backend exclusively
 */
public class FinancialEntry {
    private Date createdAt;
    private String createdDateString;
    private int valueCents;
    private String valueString;
    private String createdBy;
    private String reason;
    private boolean isNegative; // If this entry has a negative or a positive value
    private String createdByUsername;
    private String entryId;


    public FinancialEntry(Date createdAt, int valueCents, String createdBy) {
        this.createdAt = createdAt;
        this.valueCents = valueCents;
    }

    public FinancialEntry(String createdDateString, String valueString, String createdBy, String reason, boolean isNegative,
                          String createdByUsername, String entryId) {
        this.createdDateString = createdDateString;
        this.valueString = valueString;
        this.createdBy = createdBy;
        this.reason = reason;
        this.isNegative = isNegative;
        this.createdByUsername = createdByUsername;
        this.entryId = entryId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getValueCents() {
        return valueCents;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getReason() {
        return this.reason;
    }

    public String getCreatedDateString() {
        return createdDateString;
    }

    public String getValueString() {
        return valueString;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public String getEntryId() {
        return entryId;
    }
}
