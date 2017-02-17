package asciiextractor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


public class ASCIIExtractorServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException , ServletException
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");
		out.println("<div class=\"container\">");
		out.println("<div class=\"row\">");
		out.println("<div class=\"col-md-6 col-md-offset-3\">");
		out.println(" <h1><center>ASCII Extractor</center></h1>");
		out.println(" </div></div>");
		out.println("<div class=\"row\"><div class=\"col-md-4 col-md-offset-3\">");
		out.println(" <form action=\"\" class=\"search-form\">");
		out.println("<div class=\"form-group has-feedback\">");
		out.println();
		out.println();
		out.println("<center><input type=\"text\" class=\"form-control\"  name=\"SearchBox\" id=\"search\" placeholder=\"What you want to find\"></center>");
		out.println("<br>");
		out.println("<center><button type=\"button\" class=\"form-control\" id=\"search\" >Search</button><center>");
		out.println("<span class=\"glyphicon glyphicon-search form-control-feedback\"></span>");
		out.println("   	</div></form></div></div></div>");
		out.println("</body></html>");
		
		String everything = req.getParameter("SearchBox");
		 
		if(everything!=null)
		{
			if(everything.contains("http")||everything.contains("https"))
			{
		URL url = new URL(""+everything+"");
		URLConnection uc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                uc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();
          String body = a.toString();
		//String body = "abc";
          
          resp.setContentType("text/plain");
  		PrintWriter wr = resp.getWriter();
          //wr.println(body);
  			char arr[] = body.toCharArray();
  			String words[] = body.split("\\s+");
  			int totalCount = 0;
  			int wordCount = 0;
  			for(int i = 0;i<arr.length;i++)
  			{
  				totalCount = totalCount + (int)arr[i];
  			}
  			/*for(int i = 0;i<words.length;i++)
  			{*/
  				wordCount = wordCount+words.length;
  			//}
  			wr.println("the total ascii value is: "+totalCount);
  			wr.println("the total word count is: "+wordCount);
		}
			else{
				 resp.setContentType("text/plain");
			  		PrintWriter wr = resp.getWriter();
				char arr[] = everything.toCharArray();
				String words[] = everything.split("\\s+");
	  			int totalCount = 0;
	  			int wordCount = 0;
	  			for(int i = 0;i<arr.length;i++)
	  			{
	  				totalCount = totalCount + (int)arr[i];
	  			}
	  			/*for(int i = 0;i<words.length;i++)
	  			{*/
	  				wordCount = wordCount+words.length;
	  			//}
	  			wr.println("the total ascii value is: "+totalCount);
	  			wr.println("the total word count is: "+wordCount);
			}
		}
          
	}
}
