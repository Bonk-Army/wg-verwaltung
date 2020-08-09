package beans;

import models.*;
import org.json.JSONObject;
import utilities.*;

import java.util.ArrayList;
import java.util.List;

public class APIBean {

    /**
     * Process a login coming from our mobile app
     *
     * @param username The username in the request
     * @param password The password in the request
     * @return A JSON with the answer parameters
     */
    public String performLogin(String username, String password) {
        JSONObject json = new JSONObject();
        List<String> allUserNames = SQLDCusers.getAllUserNames();

        if (RegexHelper.checkString(username) && allUserNames.contains(username)) {
            String salt = SQLDCusers.getPasswordSalt(username);
            String hash = SQLDCusers.getPasswordHash(username);
            String userId = SQLDCusers.getUserId(username);

            if (!salt.isEmpty() && !hash.isEmpty()) {
                String newHash = PasswordHasher.hashPassword(password, salt);

                //Login was either successful or one of the entered params was wrong
                if (newHash != null && newHash.equals(hash)) {
                    // Generate a session token that is used to authenticate the user in the future
                    String sessionToken = new RandomStringGenerator(64).nextString();
                    String sessionTokenHash = PasswordHasher.hashPassword(sessionToken, salt);

                    if (sessionTokenHash != null && SQLDCusers.setSessionTokenHash(userId, sessionTokenHash)) {
                        json.put("status", "success");
                        json.put("sessionToken", sessionToken);
                        json.put("userId", userId);
                    } else {
                        json.put("status", "failure");
                        json.put("reason", "Serverfehler");
                    }

                } else {
                    json.put("status", "failure");
                    json.put("reason", "Falsches Passwort");
                }
            } else {
                json.put("status", "failure");
                json.put("reason", "Serverfehler");
            }
        } else {
            json.put("status", "failure");
            json.put("reason", "Falscher Benutzername");
        }

        return json.toString();
    }

    /**
     * Return all data for the given user for use in the mobile App
     *
     * @param userId       The userId of the user
     * @param sessionToken The session token transmitted by the app
     * @return A JSON Object containing all data accessible to the user
     */
    public String getAllData(String userId, String sessionToken) {
        JSONObject json = new JSONObject();

        if (RegexHelper.checkString(userId)) {
            String savedSessionHash = SQLDCusers.getSessionTokenHash(userId);
            String salt = SQLDCusers.getPasswordSaltByID(userId);
            String newSessionHash = PasswordHasher.hashPassword(sessionToken, salt);

            if (newSessionHash != null && newSessionHash.equals(savedSessionHash)) {
                // User authentication successful, fetch and return data
                json.put("status", "success");

                // General user data
                User thisUser = SQLDCusers.getAllUserData(userId);
                JSONObject userDataJson = new JSONObject();
                userDataJson.put("firstName", thisUser.getFirstName());
                userDataJson.put("lastName", thisUser.getLastName());
                userDataJson.put("email", thisUser.getEmail());
                userDataJson.put("wgId", thisUser.getWgId());
                userDataJson.put("wgName", thisUser.getWgName());

                json.put("userData", userDataJson);

                // Financial data
                List<FinancialEntry> financialEntries = SQLDCfinancial.getEntriesForMobileApp(thisUser.getWgId());
                List<JSONObject> entriesJsonList = new ArrayList<JSONObject>();

                for (FinancialEntry entry : financialEntries) {
                    JSONObject entryJSON = new JSONObject();

                    entryJSON.put("createdDateString", entry.getCreatedDateString());
                    entryJSON.put("valueString", entry.getValueString());
                    entryJSON.put("createdBy", entry.getCreatedBy());
                    entryJSON.put("createdByUsername", entry.getCreatedByUsername());
                    entryJSON.put("reason", entry.getReason());
                    entryJSON.put("isNegative", entry.isNegative());
                    entryJSON.put("entryId", entry.getEntryId());

                    entriesJsonList.add(entryJSON);
                }

                json.put("financialData", entriesJsonList);

                // ToDo data
                List<TodoEntry> todoEntries = SQLDCtodo.getWgEntriesForMobileApp(thisUser.getWgId());
                List<JSONObject> todoJsonList = new ArrayList<JSONObject>();

                for (TodoEntry entry : todoEntries) {
                    JSONObject entryJSON = new JSONObject();

                    entryJSON.put("task", entry.getTask());
                    entryJSON.put("dueDateString", entry.getDueDateString());
                    entryJSON.put("createdDateString", entry.getCreatedDateString());
                    entryJSON.put("assignee", entry.getAssignee());
                    entryJSON.put("creator", entry.getCreator());
                    entryJSON.put("isDone", entry.isDone());
                    entryJSON.put("entryId", entry.getEntryId());

                    todoJsonList.add(entryJSON);
                }

                json.put("todoData", todoJsonList);

                // Clean data
                List<CleanEntry> cleanEntries = SQLDCcleaning.getEntriesForMobileApp(thisUser.getWgId());
                List<JSONObject> cleanJsonList = new ArrayList<>();

                for (CleanEntry entry : cleanEntries) {
                    JSONObject entryJSON = new JSONObject();

                    entryJSON.put("task", entry.getTask());
                    entryJSON.put("entryId", entry.getEntryId());
                    entryJSON.put("mondayAssignee", entry.getMondayAssignee());
                    entryJSON.put("tuesdayAssignee", entry.getTuesdayAssignee());
                    entryJSON.put("wednesdayAssignee", entry.getWednesdayAssignee());
                    entryJSON.put("thursdayAssignee", entry.getThursdayAssignee());
                    entryJSON.put("fridayAssignee", entry.getFridayAssignee());
                    entryJSON.put("saturdayAssignee", entry.getSaturdayAssignee());
                    entryJSON.put("sundayAssignee", entry.getSundayAssignee());

                    cleanJsonList.add(entryJSON);
                }

                json.put("cleanData", cleanJsonList);

                // Shopping data
                List<ShoppingEntry> shoppingEntries = SQLDCshopping.getEntriesForMobileApp(thisUser.getWgId());
                List<JSONObject> shoppingJsonList = new ArrayList<>();

                for (ShoppingEntry entry : shoppingEntries) {
                    JSONObject entryJSON = new JSONObject();

                    entryJSON.put("article", entry.getArticle());
                    entryJSON.put("amount", entry.getAmount());
                    entryJSON.put("createdDateString", entry.getCreatedDateString());
                    entryJSON.put("dueDateString", entry.getDueDateString());
                    entryJSON.put("createdBy", entry.getCreatedBy());
                    entryJSON.put("requestedBy", entry.getRequestedBy());
                    entryJSON.put("isDone", entry.isDone());
                    entryJSON.put("entryId", entry.getEntryId());

                    shoppingJsonList.add(entryJSON);
                }

                json.put("shoppingData", shoppingJsonList);

            } else {
                json.put("status", "failure");
                json.put("reason", "Ungültige Sitzung");
            }

        } else {
            json.put("status", "failure");
            json.put("reason", "Falsche Nutzer-ID");
        }

        System.out.println(json.toString());

        return json.toString();
    }
}
