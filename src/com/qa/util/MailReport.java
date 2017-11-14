package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.openqa.selenium.io.Zip;
import org.testng.ITestResult;

public class MailReport 
{

	
	String username = "example@gmail.com";
    String password = "xyz";
    
    
	
	
	//for failed test case
	
	public void sendEmail(ITestResult failedTestCaseName) {
        String mailSubject = "Failed Test Case || "+new SimpleDateFormat("dd-MM-yyyy hh:mm").format(new Date());
        	String mailBody = "Test Case is Failed--\n\n\n"+failedTestCaseName.getName();
        		String mailAttachment = "Screenshots//Fail//" + failedTestCaseName.getName() + ".png";
 
        		Properties props = new Properties();
    	    	props.put("mail.smtp.auth", true);
    	    		props.put("mail.smtp.starttls.enable", true);
    	    			props.put("mail.smtp.host", "smtp.gmail.com");
    	    				props.put("mail.transport.protocol", "smtp");
    	    					props.put("mail.smtp.port", "587");
        
        
	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });

        try {
            MimeMessage msg = new MimeMessage(session);
            	msg.setFrom(new InternetAddress("thinkbeyondautomation@gmail.com"));
            		msg.addRecipient(Message.RecipientType.TO, new InternetAddress("thinkbeyondautomation@gmail.com"));
            				msg.addRecipient(Message.RecipientType.CC, new InternetAddress("thinkbeyondautomation@gmail.com"));
            
            msg.setSubject(mailSubject);
            

            MimeBodyPart messagePart = new MimeBodyPart();
            	messagePart.setText(mailBody);

            FileDataSource fileDataSource = new FileDataSource(mailAttachment);
            	MimeBodyPart attachmentPart = new MimeBodyPart();
            		attachmentPart.setDataHandler(new DataHandler(fileDataSource));
            			attachmentPart.setFileName(fileDataSource.getName());

            Multipart multipart = new MimeMultipart();
            	multipart.addBodyPart(messagePart);
            		multipart.addBodyPart(attachmentPart);
            			msg.setContent(multipart);
            				Transport.send(msg);
        } 
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
	
	//Consolidate report
	
	public void sendEmailReport() {
		
		Zip fail = new Zip();
		 
		 try {
			 fail.zip(new File(".//Screenshots//Fail"), new File(".//Screenshots//FailScreenshots.zip"));
			} 
		 catch (IOException e) {
	            e.printStackTrace();
	        }
		
        String mailSubject = "Automation report || "+new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        	String mailBody = "Attached report--\n\n\n";
        		String mailAttachment = ".//ConsoleReport//ConsoleReport.doc";
        			String mailAttachmentScreenshots = ".//Screenshots//FailScreenshots.zip";
 
        Properties props = new Properties();
	    	props.put("mail.smtp.auth", true);
	    		props.put("mail.smtp.starttls.enable", true);
	    			props.put("mail.smtp.host", "smtp.gmail.com");
	    				props.put("mail.transport.protocol", "smtp");
	    					props.put("mail.smtp.port", "587");
        
        
	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });

        try {
            MimeMessage msg = new MimeMessage(session);
            	msg.setFrom(new InternetAddress("example@gmail.com"));
            		msg.addRecipient(Message.RecipientType.TO, new InternetAddress("example@gmail.com"));
            						msg.addRecipient(Message.RecipientType.CC, new InternetAddress("example@gmail.com"));
            
            msg.setSubject(mailSubject);
            	msg.setSentDate(new Date());

            MimeBodyPart messagePart = new MimeBodyPart();
            	messagePart.setText(mailBody);

            FileDataSource fileDataSourceAdmin = new FileDataSource(mailAttachment);
            	FileDataSource fileDataSourceScreenshots = new FileDataSource(mailAttachmentScreenshots);
            			
            			
            				
            	MimeBodyPart attachmentPartScreenshots = new MimeBodyPart();
            		attachmentPartScreenshots.setDataHandler(new DataHandler(fileDataSourceScreenshots));
            			attachmentPartScreenshots.setFileName(fileDataSourceScreenshots.getName());	
            				
            				
            	MimeBodyPart attachmentPartAdmin = new MimeBodyPart();
            		attachmentPartAdmin.setDataHandler(new DataHandler(fileDataSourceAdmin));
            			attachmentPartAdmin.setFileName(fileDataSourceAdmin.getName());
            
            Multipart multipart = new MimeMultipart();
            	multipart.addBodyPart(messagePart);
            		multipart.addBodyPart(attachmentPartAdmin);
            			multipart.addBodyPart(attachmentPartScreenshots);
            				msg.setContent(multipart);
            					Transport.send(msg);
            						File file = new File(mailAttachmentScreenshots);
            							file.delete();
            								
            								
        } 
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }

	
}
