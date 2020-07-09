package beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.RegexHelper;

import java.util.HashMap;
import java.util.Map;

public class APIBean {

    public String performLogin(String username, String password) {
        Map<String, String> answerMap = new HashMap<String, String>();

        if (RegexHelper.checkString(username)) {
            answerMap.put("status", "success");
        } else {
            answerMap.put("status", "failure");
        }

        ObjectMapper objectMapper = new ObjectMapper();

        String json = "";

        try {
            json = objectMapper.writeValueAsString(answerMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }
}
