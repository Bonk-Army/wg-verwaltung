package beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.PasswordHasher;
import utilities.RandomStringGenerator;
import utilities.RegexHelper;
import utilities.SQLDCusers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIBean {

    /**
     * Process a login coming from our mobile app
     *
     * @param username The username in the request
     * @param password The password in the request
     * @return A JSON with the answer parameters
     */
    public String performLogin(String username, String password) {
        Map<String, String> answerMap = new HashMap<String, String>();
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
                        answerMap.put("status", "success");
                        answerMap.put("sessionToken", sessionToken);
                        answerMap.put("userId", userId);
                    } else {
                        answerMap.put("status", "failure");
                        answerMap.put("reason", "Serverfehler");
                    }

                } else {
                    answerMap.put("status", "failure");
                    answerMap.put("reason", "Falsches Passwort");
                }
            } else {
                answerMap.put("status", "failure");
                answerMap.put("reason", "Serverfehler");
            }
        } else {
            answerMap.put("status", "failure");
            answerMap.put("reason", "Falscher Benutzername");
        }

        return convertToJSON(answerMap);
    }

    /**
     * Check if the given session for the user is (still) valid
     *
     * @param userId       The userId of the user
     * @param sessionToken The sessionToken transmitted by the app
     * @return A JSON with the answer parameters
     */
    public String checkSessionToken(String userId, String sessionToken) {
        Map<String, String> answerMap = new HashMap<String, String>();

        if (RegexHelper.checkString(userId)) {
            String savedSessionHash = SQLDCusers.getSessionTokenHash(userId);
            String salt = SQLDCusers.getPasswordSaltByID(userId);
            String newSessionHash = PasswordHasher.hashPassword(sessionToken, salt);

            if (newSessionHash != null && newSessionHash.equals(savedSessionHash)) {
                answerMap.put("status", "success");
            } else {
                answerMap.put("status", "failure");
                answerMap.put("reason", "Ungültige Sitzung");
            }

        } else {
            answerMap.put("status", "failure");
            answerMap.put("reason", "Falsche Nutzer-ID");
        }

        return convertToJSON(answerMap);
    }

    /**
     * Convert the given Map to a JSON Object
     *
     * @param mapToConvert The Map<String, String>
     * @return A JSON Object as a String
     */
    private String convertToJSON(Map<String, String> mapToConvert) {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "";

        try {
            json = objectMapper.writeValueAsString(mapToConvert);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }
}
