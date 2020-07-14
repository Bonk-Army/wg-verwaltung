package utilities;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

/*
 Table structure:

        - uniqueID          (int)       (Primary key)
        - isDone            (bool)
        - isActive          (bool)
        - amount            (String)
        - article           (String)
        - dateDue           (Date)
        - requestedBy       (int)       (Foreign key to users.uniqueID)
        - wgId              (int)       (Foreign key to wgs.uniqueID)
        - createdBy         (int)       (Foreign key to users.uniqueID)
        - dateCreated       (Date)
 */

/**
 * Provides SQL accessor methods for everything that accesses the shopping table
 */
public class SQLDCshopping extends SQLDatabaseConnection {
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
            Date dateCreated = DateFormatter.getCurrentDateTime();
            Timestamp createdStamp = new Timestamp(dateCreated.getTime());
            Timestamp dueStamp = new Timestamp(dateDue.getTime());

            ResultSet rs = executeQuery(("INSERT INTO shopping (article, amount, createdBy, requestedBy, wgId, dateDue, dateCreated, isDone, isActive) "
                    + "VALUES ('" + article + "', '" + amount + "', " + Integer.valueOf(createdBy) + ", " + Integer.valueOf(requestedBy)
                    + ", " + Integer.valueOf(wgId) + ", '" + dueStamp + "', '" + createdStamp + "', 0, 1)"));

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
     * Set an article request to inactive
     *
     * @param requestId The requestId of the request
     * @return If it was successful
     */
    public static boolean setArticleRequestInactive(String requestId) {
        try {
            executeQuery(("UPDATE shopping SET isActive = 0 WHERE uniqueID=" + Integer.valueOf(requestId)));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns all recent (open + last seven days) article requests for the given wg.
     * The method first fetches the data from sql and then parses it into a format that can be read directly
     * in the frontend. It returns a list with the active requests and every request is saved into a map with
     * the respective key value pairs.
     * This method also "looks" at the data and gives the required color info into the maps so we don' need to
     * have calculation for this in the frontend.
     *
     * @param wgId The wg Id of the wg
     * @return A List of Maps which contain an article each
     */
    public static List<Map<String, String>> getActiveArticleRequests(String wgId) {
        List<Map<String, String>> requestList = new ArrayList<Map<String, String>>();

        try {
            // Get all required info for each request that is active and is either not done or has been due in the last seven days.
            // Ordered by their status and requests of the same status are ordered by their due date.
            ResultSet rs = executeQuery(("SELECT article, amount, createdBy, requestedBy, dateDue, dateCreated, uniqueID, isDone FROM shopping WHERE wgId="
                    + Integer.valueOf(wgId) + " AND (DATEDIFF(CURRENT_TIMESTAMP, dateDue) <= 7 OR isDone = 0) AND isActive = 1 ORDER BY isDone, dateDue ASC"));

            while (rs.next()) {
                Map<String, String> currentArticle = new HashMap<String, String>();

                currentArticle.put("article", rs.getString(1));
                currentArticle.put("amount", rs.getString(2));
                currentArticle.put("dateDue", DateFormatter.dateToString(rs.getTimestamp(5)));
                currentArticle.put("dateCreated", DateFormatter.dateToString(rs.getTimestamp(6)));
                currentArticle.put("requestId", String.valueOf(rs.getInt(7)));

                String createdById = String.valueOf(rs.getInt(3));
                String requestedById = String.valueOf(rs.getInt(4));
                String createdBy = SQLDCusers.getUsername(createdById);
                String requestedBy = SQLDCusers.getUsername(requestedById);

                boolean isDone = rs.getBoolean(8);

                currentArticle.put("requestedBy", SQLDCusers.getNameString(requestedBy));
                currentArticle.put("createdBy", SQLDCusers.getNameString(createdBy));

                // Color the requests based on their priority
                Date currentDate = DateFormatter.getCurrentDateTime();
                Date dateDue = rs.getTimestamp(5);
                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DATE, 1);
                Date tomorrow = c.getTime();
                currentArticle.put("doneMessage", "Nein");
                currentArticle.put("buttonHideStatus", "");

                String colorClass = "";
                if (currentDate.after(dateDue)) {
                    colorClass = "tooLate";
                } else if (tomorrow.after(dateDue)) {
                    colorClass = "late";
                }

                if (isDone) {
                    colorClass = "done";
                    currentArticle.put("buttonHideStatus", "hidden=\"hidden\"");
                } else {
                    currentArticle.put("buttonHideStatus", "");
                }


                currentArticle.put("colorClass", colorClass);

                requestList.add(currentArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestList;
    }

    /**
     * Get the wgId of a request
     *
     * @param requestId The requestId of the request
     * @return The wgId associated to the request
     */
    public static String getWgIdOfRequest(String requestId) {
        try {
            ResultSet rs = executeQuery(("SELECT wgId FROM shopping WHERE uniqueID=" + Integer.valueOf(requestId)));

            while (rs.next()) {
                return String.valueOf(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
