package utilities;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;


public class MailSender {
    public static void main(String[] args) throws IOException {
        sendEmail("patrick@mueller-patrick.tech", "Test", "Test2");
    }

    public static boolean sendEmail(String receiver, String subject, String contentString){
        Email from = new Email("no-reply@wgverwaltung.azurewebsites.net");
        Email to = new Email(receiver);
        Content content = new Content("text/plain", contentString);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(SecretsReader.getSendGridKey());
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
    }
}
