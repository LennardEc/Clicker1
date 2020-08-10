package com.example.clicker1;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaEmailAPI extends AsyncTask<Void, Void, Void> {

    private Context context;

    private Session session;
    private String subject, message;

    public JavaEmailAPI(Context context, String userEmail, String payMethod, int amount, String additionalInformation) {
        this.context = context;
        this.subject = "Payout Request from " + userEmail;
        this.message = "Via: " + payMethod + "\n" +
                       "Amount: " + ""+amount + "\n" +
                       "Additional Information: " + additionalInformation;;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Utils.EMAIL_FROM, Utils.PASSWORD_FROM);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(Utils.EMAIL_FROM));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(Utils.EMAIL_TO)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}