package com.zingpoll;



import java.io.IOException; 
import java.util.ArrayList; 
import java.util.Date; 
import java.util.List; 
import java.util.Properties; 
 
import javax.mail.Folder; 
import javax.mail.Message; 
import javax.mail.MessagingException; 
import javax.mail.Multipart; 
import javax.mail.NoSuchProviderException; 
import javax.mail.Part; 
import javax.mail.Session; 
import javax.mail.Store; 
import javax.mail.internet.MimeMessage; 
import javax.mail.search.SearchTerm; 
 
public class GMailHelper { 
 
	private static String imapHost = "imap.gmail.com"; 
	private static String imapPort = "993"; 
 
	private String userName = null; 
	private String password = null; 
 
	/** 
	 * Constructor 
	 *  
	 * @param userName 
	 * @param password 
	 */ 
	public GMailHelper(String userName, String password) { 
 
		this.userName = userName; 
		this.password = password; 
	} 
 
	/** 
	 * Set IMAP session 
	 */ 
	public Session setIMAPSession() { 
 
		// IMAP settings 
		Properties properties = new Properties(); 
 
		// server setting 
		properties.put("mail.imap.host", imapHost); 
		properties.put("mail.imap.port", imapPort); 
 
		// SSL setting 
		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
		properties.setProperty("mail.imap.socketFactory.fallback", "false"); 
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf(imapPort)); 
 
		return Session.getInstance(properties); 
	} 
 
	/** 
	 * Searches for e-mail messages containing the specified keyword in Subject 
	 * field. 
	 *  
	 * @param no 
	 *            of seconds to wait before checking for mails 
	 * @param folderName 
	 *            (e.g. INBOX) 
	 * @param keyword 
	 *            Search with this Keyword in subject line 
	 * @param Date 
	 *            object after which emails to be searched. Null for any date 
	 * @throws InterruptedException 
	 */ 
	public List<Message> searchEmail(int noOfSecToWait, String folderName, final String keyword, Date aDate) 
			throws InterruptedException { 
 
		Session session = setIMAPSession(); 
 
		List<Message> returnList = new ArrayList<>(); 
 
		try { 
 
			Thread.sleep(noOfSecToWait * 1000); 
 
			System.out.println("Connects to Message Store"); 
			Store store = session.getStore("imap"); 
			store.connect(userName, password); 
 
			System.out.println("Opens folder : " + folderName); 
			Folder folderInbox = store.getFolder(folderName); 
			folderInbox.open(Folder.READ_ONLY); 
 
			System.out.println("Creates search condition for Folder : " + folderName 
					+ ". Searching message with Subject line : " + keyword); 
			SearchTerm searchCondition = new SearchTerm() { 
 
				private static final long serialVersionUID = 1L; 
 
				@Override 
				public boolean match(Message message) { 
					try { 
						if (message.getSubject().contains(keyword) 
								&& (null == aDate || message.getSentDate().after(aDate))) { 
							return true; 
						} 
					} catch (MessagingException ex) { 
						System.out.println(ex.getMessage()); 
						ex.printStackTrace(); 
					} 
					return false; 
				} 
			}; 
 
			System.out.println("Performs search through the folder : " + folderName); 
			Message[] foundMessages = folderInbox.search(searchCondition); 
 
			System.out.println("Number of messages found : " + foundMessages.length); 
			for (int i = 0; i < foundMessages.length; i++) { 
 
				Message message = foundMessages[i]; 
 
				/* assuming you retrieved 'message' from your folder object */ 
				Message copyOfMessage = new MimeMessage((MimeMessage) message); 
 
				returnList.add(copyOfMessage); 
			} 
 
			System.out.println("Disconnecting..."); 
			folderInbox.close(false); 
			store.close(); 
 
		} catch (NoSuchProviderException ex) { 
			System.out.println("No provider found.\n" + ex.getMessage()); 
			ex.printStackTrace(); 
		} catch (MessagingException ex) { 
			System.out.println("Could not connect to the message store.\n" + ex.getMessage()); 
			ex.printStackTrace(); 
		} 
 
		return returnList; 
	} 
 
	/** 
	 * Return the primary text content of the message part. 
	 */ 
	public String getText(boolean textIsHtml, Part p) throws MessagingException, IOException { 
 
		if (p.isMimeType("text/*")) { 
			String s = (String) p.getContent(); 
			textIsHtml = p.isMimeType("text/html"); 
			return s; 
		} 
 
		if (p.isMimeType("multipart/alternative")) { 
 
			System.out.println("refer html text over plain text"); 
			// prefer html text over plain text 
			Multipart mp = (Multipart) p.getContent(); 
			String text = null; 
			for (int i = 0; i < mp.getCount(); i++) { 
				Part bp = mp.getBodyPart(i); 
				if (bp.isMimeType("text/plain")) { 
					if (text == null) 
						text = getText(textIsHtml, bp); 
					continue; 
				} else if (bp.isMimeType("text/html")) { 
					String s = getText(textIsHtml, bp); 
					if (s != null) 
						return s; 
				} else { 
					return getText(textIsHtml, bp); 
				} 
			} 
			return text; 
		} else if (p.isMimeType("multipart/*")) { 
			Multipart mp = (Multipart) p.getContent(); 
			for (int i = 0; i < mp.getCount(); i++) { 
				String s = getText(textIsHtml, mp.getBodyPart(i)); 
				if (s != null) 
					return s; 
			} 
		} 
 
		return null; 
	} 
} 