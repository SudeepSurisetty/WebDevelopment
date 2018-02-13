import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PetitionFormServlet extends HttpServlet {
    //Service method
    
   public void doGet(HttpServletRequest request, HttpServletResponse response) 
   throws ServletException, IOException {
        
    }
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
 
        out.print("<html><body>");
        out.print("<h1> Form details entered by you</h1>");
        out.println("<table border=\"1\" cellpadding = \"5\"" + " cellspacing = \"5\">");
        out.println("<tr> <th>Parameter Name</th>" + "<th>Parameter Value</th></tr>");
                
        Enumeration e = request.getParameterNames();
        
    	out.println("<body bgcolor=#E5FCB7>");
                
         while(e.hasMoreElements())
    		{
            String paramName=(String) e.nextElement();
            out.print("<tr><td>" + paramName + "</td><td>");
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() == 0)
                    out.println("<b>Null</b>");
                else
                    out.println(paramValue);
            } else {
                out.println("<ul>");
                for (int i = 0; i < paramValues.length; i++) {
                    out.println("<li>" + paramValues[i] + "</li>");
                }
                out.println("</ul>");
            }
            out.print("</td></tr>");
        }
        out.println("</table></body></html>");
    
    }
}

