package com.appliboard.appliboard.services;
import com.appliboard.appliboard.models.JobApplication;
import com.appliboard.appliboard.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.sendgrid.*;
import java.io.IOException;

@Service("mailService")
public class EmailService {
    @Value("${sgkey}")
    private String sgkey;

    public void sendEmail(User user, String reminderSelect, JobApplication jobApp) throws IOException {

        Email from = new Email("applitraq@gmail.com");
        String subject = "Applitraq Reminder";
        Email to = new Email(user.getEmail());
        Content content = new Content("text/plain", jobApp.getTitle() + ", " + jobApp.getDescription());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sgkey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}



