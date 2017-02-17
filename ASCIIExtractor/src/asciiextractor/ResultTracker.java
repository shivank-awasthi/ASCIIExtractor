package asciiextractor;

import javax.servlet.http.HttpServlet;

import asciiextractor.DominantColorPicker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class ResultTracker extends HttpServlet
{
	int freq;
	int finalFreq;
	
	public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		URL url = new URL("http://164.100.158.135/ExamResults/ExamResultsmain.htm");
		URLConnection uc = url.openConnection();
	
        BufferedReader in = new BufferedReader(new InputStreamReader(
                uc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();
          String body = a.toString();
          String wordsBody[] = body.split("\\s+");
          freq = 0;
          for(int i = 0;i<wordsBody.length;i++)
          {
        	  wordsBody[i] = wordsBody[i].toLowerCase();
        	  if(wordsBody[i].contains("b.tech")){
        		  freq++;
        	  }
          }
         
          //out.println("total occurence of btech:"+freq);
          //out.println("total words:"+wordsBody.length);
         out.println("you will receive the mail when result is uploaded.");
         DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
         Entity result = new Entity("intVal", "btechcounter");
         Key k = result.getKey();
         try {
			 result = datastore.get(k);
			String i = (String)result.getProperty("btech");
			int j = Integer.parseInt(i);
			finalFreq = j;
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         if(freq!=finalFreq)
         {
     		
        	  k = result.getKey();
        	 try {
				 result = datastore.get(k);
				Integer i = new Integer(freq);
				result.setProperty("btech",i.toString());
				datastore.put(result);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
          if(freq>finalFreq+20)
          {
        	  Properties pro = new Properties();
      		Session session = Session.getDefaultInstance(pro,null);
      		String msgBody = "Dear user,\n Based on the latest scraping of results page of GGSIPU, it seems"+
      		"that result is uploaded. \n Do check at website.\n Regards \n Shivank Awasthi";
      		
      		try{
      			
      			Message msg = new MimeMessage(session);
      			msg.setFrom(new InternetAddress("efficientcrawler@gmail.com","EC Team"));
      			String address = "a.shivank96@gmail.com";
      			String name = "shivank";
      			//String address1 = "nareshkumar5184@gmail.com";
      			//String name1 = "nareshkumar";
      			//String address2 = "devvratcooldevil2@gmail.com";
      			//String name2 = "devvrat";
      			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address,name));
      			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address1,name1));
      			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress(address2,name2));
      			msg.setSubject("Your Result is declared!");
      			msg.setText(msgBody);
      			Transport.send(msg);
      		}catch(MessagingException e){
      			e.printStackTrace();
      		}
      		 k = result.getKey();
        	 try {
				 result = datastore.get(k);
				Integer i = new Integer(freq);
				result.setProperty("btech",i.toString());
				datastore.put(result);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
	}
}
