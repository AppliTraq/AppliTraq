//package com.appliboard.appliboard.services;
//
//import com.appliboard.appliboard.models.JobApplication;
//import com.appliboard.appliboard.models.Reminder;
//import com.appliboard.appliboard.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.MailException;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Arrays;
//
//@Service("mailService")
//public class EmailService {
//
//    @Autowired
//    public JavaMailSender emailSender;
//
//    @Value("${spring.mail.from}") // email that is always sending (ex: no reply emails)
//    private String from;
//
//    public void prepareAndSend(User user, String reminderSelect, JobApplication jobApp) { // job application added
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom(from);
//        System.out.println(msg.getFrom());
//        msg.setTo(user.getEmail()); // changes from 'to' to post so the post can be sent instead
//        System.out.println(Arrays.toString(msg.getTo()));
//        // gives reminder options to the user
////        switch(reminderSelect) {
////            case "email":
//                String subject = "Remember to follow up with " ;
//        System.out.println(subject);
//                msg.setSubject(subject);
//        System.out.println(msg.getSubject());
//
//                String body = jobApp.getTitle();
//        System.out.println(body);
//                msg.setText(body);
//        System.out.println(msg.getText());
//                break;
//
//            case "appPortal":
//                subject = "Remember to check the application portal for " + jobApp.getCompany(); // check status on app, etc.
//                msg.setSubject(subject);
//
//                body = "INFO OF APP" + jobApp.getDescription();
//                msg.setText(body);
//                break;
//
//            case "research":
//                subject = "Remember to research on the following company: " + jobApp.getCompany();
//                msg.setSubject(subject);
//
//                body = "RESEARCH INFO" + jobApp.getDescription();
//                msg.setText(body);
//                break;
//        }
//
//
//        try{
//            emailSender.send(msg);
//        }
//        catch (MailException ex) {
//            // simply log it and go on...
//            System.err.println(ex.getMessage());
//        }
//    }
//
//}
