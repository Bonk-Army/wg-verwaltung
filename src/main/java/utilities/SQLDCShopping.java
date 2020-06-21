package utilities;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

/**
 * Provides SQL accessor methods for everything that accesses the shopping table
 */
public class SQLDCShopping extends SQLDatabaseConnection {
    /**
     * Add an article request to the shopping list
     *
     * @param article     The article
     * @param amount      The amount
     * @param createdBy   The user that created the request
     * @param requestedBy The user that requests the article
     * @param dateDue     The date when the article should have been purchased
     * @param wgId        The wgId of the creating user
     * @return If it was successful
     */
    public static boolean addArticleRequest(String article, String amount, String createdBy, String requestedBy, Date dateDue, String wgId) {
        try {
            Date dateCreated = new Date();
            Timestamp createdStamp = new Timestamp(dateCreated.getTime());
            Timestamp dueStamp = new Timestamp(dateDue.getTime());

            ResultSet rs = executeQuery(("INSERT INTO shopping (article, amount, createdBy, requestedBy, wgId, dateDue, dateCreated, isDone) "
                    + "VALUES ('" + article + "', '" + amount + "', " + Integer.valueOf(createdBy) + ", " + Integer.valueOf(requestedBy)
                    + ", " + Integer.valueOf(wgId) + ", '" + dueStamp + "', '" + createdStamp + "', 0)"));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Set an article request to done
     *
     * @param requestId The request Id of the request that is done
     * @return If it was successful
     */
    public static boolean setArticleRequestDone(String requestId) {
        try {
            executeQuery(("UPDATE shopping SET isDone = 1 WHERE uniqueID=" + Integer.valueOf(requestId)));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns all open article requests for the given wg
     *
     * @param wgId The wg Id of the wg
     * @return A List of Maps which contain an article each
     */
    public static List<Map<String, String>> getActiveArticleRequests(String wgId) {
        List<Map<String, String>> requestList = new ArrayList<Map<String, String>>();

        try {
            ResultSet rs = executeQuery(("SELECT article, amount, createdBy, requestedBy, dateDue, dateCreated, uniqueID FROM shopping WHERE wgId="
                    + Integer.valueOf(wgId) + " AND isDone = 0"));

            while (rs.next()) {
                Map<String, String> currentArticle = new HashMap<String, String>();

                currentArticle.put("article", rs.getString(1));
                currentArticle.put("amount", rs.getString(2));
                currentArticle.put("dateDue", DateFormatter.dateToString(rs.getDate(5)));
                currentArticle.put("dateCreated", DateFormatter.dateToString(rs.getDate(6)));
                currentArticle.put("requestId", String.valueOf(rs.getInt(7)));

                String createdById = String.valueOf(rs.getInt(3));
                String requestedById = String.valueOf(rs.getInt(4));
                String createdBy = SQLDCLogin.getUsername(createdById);
                String requestedBy = SQLDCLogin.getUsername(requestedById);

                currentArticle.put("requestedBy", SQLDCUtility.getNameString(requestedBy));
                currentArticle.put("createdBy", SQLDCUtility.getNameString(createdBy));

                // Color the requests based on their priority
                Date currentDate = new Date();
                Date dateDue = rs.getDate(5);
                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DATE, 1);
                Date tomorrow = c.getTime();
                currentArticle.put("doneMessage", "Nein");
                currentArticle.put("buttonHideStatus", "");
                if (currentDate.after(dateDue)) {
                    currentArticle.put("colorClass", "tooLate");
                } else if (tomorrow.after(dateDue)) {
                    currentArticle.put("colorClass", "late");
                } else {
                    currentArticle.put("colorClass", "");
                }

                requestList.add(currentArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestList;
    }

}
