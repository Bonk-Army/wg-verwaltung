package beans;

import utilities.PasswordHasher;
import utilities.RandomStringGenerator;
import utilities.RegexHelper;
import utilities.SQLDCusers;
import org.json.*;

import java.util.*;

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
     * @param userId The userId of the user
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


            } else {
                json.put("status", "failure");
                json.put("reason", "Ungültige Sitzung");
            }

        } else {
            json.put("status", "failure");
            json.put("reason", "Falsche Nutzer-ID");
        }

        return json.toString();
    }
}
