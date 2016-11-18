package com.nyu.util;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class NYUUtils
{

  private static final Random RANDOM = new SecureRandom();
  private static final String fromAddress = "puneindia02092013@gmail.com";
  public static final String messageForNewUser = "XXXXXX";
  private final static String username = "puneindia02092013@gmail.com";
  private final static String password = "arvan02092013";
  /** Length of password. @see #generateRandomPassword() */
  public static final int PASSWORD_LENGTH = 8;
  /**
   * Generate a random String suitable for use as a temporary password.
   *
   * @return String suitable for use as a temporary password
   */
  public static String generateRandomPassword()
  {
      // Pick from some letters that won't be easily mistaken for each
      // other. So, for example, omit o O and 0, 1 l and L.
      String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

      String pw = "";
      for (int i=0; i<PASSWORD_LENGTH; i++)
      {
          int index = (int)(RANDOM.nextDouble()*letters.length());
          pw += letters.substring(index, index+1);
      }
      return pw;
  }
  
  public static void sendMail(String toAddress, String subject, String text){
	  
	  Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(username, password);
              }
        });

      try {
              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress(fromAddress));
              message.setRecipients(Message.RecipientType.TO,
                      InternetAddress.parse(toAddress));
              message.setSubject(subject);
              message.setText(text);

              Transport.send(message);

              System.out.println("Done");

      } catch (MessagingException e) {
          System.out.println("Could not send email");
              throw new RuntimeException(e);
      }
  }
  
}