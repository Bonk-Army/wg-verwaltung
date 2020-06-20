package models;

import java.util.Date;

/**
 * Model for financial entries used in the backend exclusively
 */
public class FinancialEntry {
    private Date createdAt;
    private int valueCents;
    private String createdBy;

    public FinancialEntry(Date createdAt, int valueCents, String createdBy) {
        this.createdAt = createdAt;
        this.valueCents = valueCents;
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
}
