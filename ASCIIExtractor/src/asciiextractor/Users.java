package asciiextractor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;


public class Users extends HttpServlet
{
	String t;
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		String email = req.getParameter("email");
		 Entity result = new Entity("subscribers",email);
		 Key k = result.getKey();
		 try {
			Entity test = datastore.get(k);
		 t = (String)test.getProperty(email);
		
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(t!=null)
			{
			out.println("This id is already registered!");
			}
			else
			{
				
			
		result.setProperty("emailId",email);
		datastore.put(result);
			}
	}
}
