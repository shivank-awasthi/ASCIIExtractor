package asciiextractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;



public class DailyASCIIExtractor extends HttpServlet
{
	String everything;
	protected void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		String arr[] ={"http://www.geeksforgeeks.org",
			"http://www.msit.in",
				"http://www.reddit.com",
				"http://www.indianexpress.com",
				"http://www.indianexpress.com/technology",
				"https://www.facebook.com/directory/pages/",
				"https://www.facebook.com/places/",
				"https://www.facebook.com/directory/celebrities/",
				"https://www.youtube.com/?gl=IN",
				"http://www.ipu.ac.in/",
				"https://en.wikipedia.org/wiki/Portal:Current_events",
				"https://news.ycombinator.com/"};
		String msgBody = "Dear Admin,\n";
		try
		{
		for(int i = 0;i<arr.length;i++)
		{
			everything = arr[i];
		if(everything.contains("http")||everything.contains("https"))
		{
	URL url = new URL(""+everything+"");
	URLConnection uc = url.openConnection();
	uc.setConnectTimeout(10000);
    BufferedReader in = new BufferedReader(new InputStreamReader(
            uc.getInputStream(), "UTF-8"));
    String inputLine;
    StringBuilder a = new StringBuilder();
    while ((inputLine = in.readLine()) != null)
        a.append(inputLine);
    in.close();
      String body = a.toString();
	//String body = "abc";
      
      res.setContentType("text/plain");
		PrintWriter wr = res.getWriter();
      //wr.println(body);
			char carr[] = body.toCharArray();
			int totalCount = 0;
			for(int j = 0;j<carr.length;j++)
			{
				totalCount = totalCount + (int)carr[j];
			}
			wr.println("the total ascii value of "+everything+"  is: "+totalCount);
			msgBody = msgBody+"the total ascii value of "+everything+"  is: "+totalCount+"\n";
	}
		
	}
		Properties pro = new Properties();
		Session session = Session.getDefaultInstance(pro,null);
		
		try{
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("efficientcrawler@gmail.com","Efficient Crawler Team"));
			String address = "a.shivank96@gmail.com";
			String name = "shivank";
			//String address1 = "nareshkumar5184@gmail.com";
			//String name1 = "nareshkumar";
			//String address2 = "devvratcooldevil2@gmail.com";
			//String name2 = "devvrat";
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address,name));
			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address1,name1));
			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address2,name2));
			msg.setSubject("Seed ASCII Analysis");
			msg.setText(msgBody);
			Transport.send(msg);
		}catch(MessagingException e){
			e.printStackTrace();
		}
		
      
	}
	catch(SocketTimeoutException e)
	{
		Properties pro1 = new Properties();
		Session session1 = Session.getDefaultInstance(pro1,null);
		
		try{
			
			Message msg = new MimeMessage(session1);
			msg.setFrom(new InternetAddress("efficientcrawler@gmail.com","Efficient Crawler Team"));
			String address = "shivankawasthi.cse.msit@gmail.com";
			String name = "shivank";
			//String address1 = "nareshkumar5184@gmail.com";
			//String name1 = "nareshkumar";
			//String address2 = "devvratcooldevil2@gmail.com";
			//String name2 = "devvrat";
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address,name));
			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address1,name1));
			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address2,name2));
			msg.setSubject("Seed ASCII Analysis");
			msg.setText(msgBody);
			Transport.send(msg);
		}catch(MessagingException e1){
			e1.printStackTrace();
		}
		
		
	}
}
}
