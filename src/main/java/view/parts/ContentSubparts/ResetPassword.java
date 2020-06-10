package view.parts.ContentSubparts;

import view.parts.ContentPart;

public class ResetPassword extends ContentPart {

    public ResetPassword(String username, String key) {
        generateContent(username, key);
    }

    /**
     * Generate the form and pre-fill the hidden input fields with the username and the password reset key
     *
     * @param username The username to be set
     * @param key      The key to be set
     */
    private void generateContent(String username, String key) {
        CharSequence usernameCharSeq = username;
        CharSequence keyCharSeq = key;
        CharSequence replaceUsernameSeq = "REPLACE_USERNAME";
        CharSequence replaceKeySeq = "REPLACE_KEY";

        this.content = readComponentTemplate("ResetPassword", "html").replace(replaceUsernameSeq, usernameCharSeq).replace(replaceKeySeq, keyCharSeq);
    }
}
