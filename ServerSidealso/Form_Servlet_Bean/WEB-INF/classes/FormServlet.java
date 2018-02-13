import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FormServlet extends HttpServlet {
    //Service method
    
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Quiz</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Page 1</h2>");
        out.println("<form action = 'FormServlet' method = 'post'>");
        out.println("<p>Question 1: How many children do you have?</p>"
                + "<input type='text' name='ans'/><br />");
        out.println("<input type='hidden' name='page' value='p1'/>");
        out.println("<input type =  'submit' value = 'Submit Query' name = 'button'/><br /></p>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        //create a session object to store all the answers
        HttpSession session = request.getSession();
         
        //extracting page number from request
        String page = request.getParameter("page");
        
        
        
        if(page.equals("p1")){
        
        String ans = request.getParameter("ans"); // we got the answer and now it should be stored in the session object
        int ans1 = Integer.parseInt(ans);
        session.setAttribute("answer1", ans1); //stored as (key,value) pair
        
        
        
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Quiz</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Page 2</h2>");
        out.println("<form action = 'FormServlet' method = 'post'>");
        for(int i=0; i<ans1; i++){
        out.println("<p>Please input the name of child "+(i+1)+"</p>");
        out.println("<input type='text' name='ans'/><br />");
                }
        out.println("<input type='hidden' name='page' value='p3'/>");
        out.println("<input type =  'submit' value = 'Submit Query' name = 'button'/>&nbsp &nbsp</p>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        }

        
        
        
        //Result page
        else if(page.equals("p3")){
        
        String[] ans3 = request.getParameterValues("ans");
        

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Quiz</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Your children name(s) is/are :</h2>");
        for(int i=0;i< ans3.length;i++)
       {
           out.println("<li>"+ans3[i]+"</li>");
       }
        out.println("</body>");
        out.println("</html>");
        }
    }
}
