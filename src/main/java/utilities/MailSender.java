package utilities;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sendgrid.helpers.mail.objects.Personalization;
import config.globalConfig;

/**
 * Used to send emails via SendGrid API
 */
public class MailSender {
    /**
     * Universal class to send emails. Takes a type of mail from the enum below and a map of custom arguments.
     * Mocked for testing without the api key
     *
     * @param receiver The email address of the receiver
     * @param type     The type of mail to be send (Choose from enum!)
     * @param args     The map of custom arguments
     * @return If it was successful
     */
    public static boolean sendEmail(String receiver, Mailtypes type, Map<String, String> args) {
        if (!globalConfig.isTest()) {
            String templateId = type.getTemplateID();

            // Create the email object and set basic params
            Email from = new Email("no-reply@wgverwaltung.azurewebsites.net");
            from.setName("WG-Verwaltung");
            Email to = new Email(receiver);
            Mail mail = new Mail();
            mail.setFrom(from);
            mail.setTemplateId(templateId);

            // Add custom parameters
            Personalization personalization = new Personalization();
            for (String key : args.keySet()) {
                personalization.addDynamicTemplateData(key, args.get(key));
            }
            // Set receiver
            personalization.addTo(to);

            // Add the custom params to the mail
            mail.addPersonalization(personalization);

            SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());

                return true;
            } catch (IOException ex) {
                return false;
            }
        } else {
            String mailString = "SENDMAIL:\nTO: " + receiver + "\nTYPE: " + type + "\nARGS: " + args;
            System.out.printf(mailString);
            return true;
        }
    }

    /**
     * Sends an email for the user to verify his email address. The link-parameter might look like this:
     * verify?uname=Bob&key=ABC123DEF4
     *
     * @param receiver The email address of the user
     * @param username The username of the user (To greet him in the email)
     * @param link     The sublink to verify (So everything after https://wgverwaltung.azurewebsites.net/)
     * @return If it was successful
     */
    public static boolean sendVerificationMail(String receiver, String username, String link) {
        Map<String, String> args = new HashMap<String, String>();
        args.put("username", username);
        args.put("verifyLink", link);

        return sendEmail(receiver, Mailtypes.VERIFY, args);
    }

    /**
     * Sends an email for the user to reset his password. The link-parameter might look like this:
     * reset?uname=Bob&key=ABCDEF123456ABCG2435DSFSDGNB
     *
     * @param receiver The email address of the user
     * @param username The username of the user
     * @param link     The sublink to reset (so everything after https://wgverwaltung.azurewebsites.net/)
     * @return If it was successful
     */
    public static boolean sendResetPasswordMail(String receiver, String username, String link) {
        Map<String, String> args = new HashMap<String, String>();
        args.put("username", username);
        args.put("resetLink", link);

        return sendEmail(receiver, Mailtypes.RESETPW, args);
    }

    /**
     * Send the content of the contact request form to patrick
     *
     * @param name    The entered name
     * @param email   The entered email address
     * @param subject The entered subject
     * @param message The entered message
     * @return If it was successful
     */
    public static boolean sendContactRequestMail(String name, String email, String subject, String message) {
        Map<String, String> args = new HashMap<String, String>();
        args.put("name", name);
        args.put("email", email);
        args.put("subject", subject);
        args.put("message", message);

        return sendEmail("wgverwaltung-contactrequest@mueller-patrick.tech", Mailtypes.CONTACT, args);
    }

    public static boolean sendWgCreationMail(String name, String receiver, String wgname, String inviteCode, String inviteLink) {
        Map<String, String> args = new HashMap<String, String>();

        args.put("username", name);
        args.put("wgname", wgname);
        args.put("invitecode", inviteCode);
        args.put("invitelink", inviteLink);

        return sendEmail(receiver, Mailtypes.WGCREATE, args);
    }

    /**
     * Enum for types of emails we send
     */
    public static enum Mailtypes {
        VERIFY("d-48e403a281cb4e9382351342188b786b"),
        RESETPW("d-d6e6140c08c343fdb1c1136b07c36829"),
        CONTACT("d-0203b477107643089ad24671014480a9"),
        WGCREATE("d-32b313dcbfa44a89984a2ce83df781a7");

        private String templateID;

        private Mailtypes(String templateID) {
            this.templateID = templateID;
        }

        public String getTemplateID() {
            return this.templateID;
        }
    }
}
