package asciiextractor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class DominantColorPicker extends HttpServlet 
{
	DatastoreService datastore;
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		 datastore = DatastoreServiceFactory.getDatastoreService();
		Entity result = new Entity("intVal", "btechcounter");
		result.setProperty("btech", "110");
		datastore.put(result);
		Key ek = result.getKey();
		
		try {
			Entity firstUser = datastore.get(ek);
		
			//out.println("Username:"+firstUser.getProperty("username"));
			//out.println("Password:"+firstUser.getProperty("password"));
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
