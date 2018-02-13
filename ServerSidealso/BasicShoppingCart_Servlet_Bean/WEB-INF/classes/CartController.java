import Bean.Item;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.lang.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartController extends HttpServlet {

    List<Item> itemList;
    static int count = 1;

    @Override
    public void init() {
    
        //This method will only be call for once
        itemList = new ArrayList<>();
        Item book1= new Item();
        book1.setName("Java Servlet Programming");
        book1.setPrice("$29.95");
        book1.setId(count++);
        
        Item book2= new Item();
        book2.setName("Oracle Database Programming");
        book2.setPrice("$48.95");
        book2.setId(count++);
        
        Item book3= new Item();
        book3.setName("System Analysis and Design with UML");
        book3.setPrice("$14.95");
        book3.setId(count++);
        
        Item book4= new Item();
        book4.setName("Object Oriented Software Engineering");
        book4.setPrice("$35.99");
        book4.setId(count++);
        
        Item book5= new Item();
        book5.setName("Java Web Services");
        book5.setPrice("$27.99");
        book5.setId(count++);
        
       //Music items
        Item music1 = new Item();
        music1.setName("I am Going to Tell you a Secret by Maradonna");
        music1.setPrice("$26.99");
        music1.setId(count++);
        
        Item music2 = new Item();
        music2.setName("Baby One More Time by Britney Spears");
        music2.setPrice("$10.95");
        music2.setId(count++);
        
        Item music3 = new Item();
        music3.setName("Justified by Justin Timberlake");
        music3.setPrice("$9.97");
        music3.setId(count++);
        
        Item music4 = new Item();
        music4.setName("Loose by Nelly Furtado");
        music4.setPrice("$13.98");
        music4.setId(count++);
        
        Item music5 = new Item();
        music5.setName("Gold Digger by Kanye West");
        music5.setPrice("$27.99");
        music5.setId(count++);
        
 
 		//Laptop items 
        Item pc1 = new Item();
        pc1.setName("Apple Macbook Pro 13.3 Display");
		pc1.setPrice("$1299.99");
		pc1.setId(count++);
		
		Item pc2 = new Item();
        pc2.setName("Asus Laptop with Centrino 2 Black");
		pc2.setPrice("$949.95");
		pc2.setId(count++);
		
		Item pc3 = new Item();
        pc3.setName("HP Pavilion with Centrino 2");
		pc3.setPrice("$1199.95");
		pc3.setId(count++);
		
		Item pc4 = new Item();
        pc4.setName("Toshiba Satellite Laptop with Centrino 2");
		pc4.setPrice("$899.99");
		pc4.setId(count++);
		
		Item pc5 = new Item();
        pc5.setName("Sony VAIO Laptop with Centrino 2");
		pc5.setPrice("$779.99");
		pc5.setId(count++);
		

        itemList.add(book1);
        itemList.add(book2);
        itemList.add(book3);
        itemList.add(book4);
        itemList.add(book5);
        
        itemList.add(music1);
        itemList.add(music2);
        itemList.add(music3);
        itemList.add(music4);
        itemList.add(music5);
        
        itemList.add(pc1);
        itemList.add(pc2);
        itemList.add(pc3);
        itemList.add(pc4);
        itemList.add(pc5);
        
        

    }





    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        
        //MyItems should be visible so store in the entire session
        ArrayList<Item> myItems = null;
        if(session.getAttribute("myItemSet")!= null){
            myItems = (ArrayList<Item>)session.getAttribute("myItemSet");
        }
        else{
            myItems = new ArrayList<Item>(); //if no items are present then creating new ArrayList 
        }
        
        
        
        if (action.equals("Add")) {
        String[] booksAdded = request.getParameterValues("books"); // we got the answer and now it should be stored in the session object
        /*ArrayList<String> aux = new ArrayList<>();
        for(int i=0; i<booksAdded.length; i++)
        	aux.add(booksAdded[i]);
        	*/
        for(String book : booksAdded)
        	for(Item i : itemList)
        		if(i.getName().equals(book))
        			myItems.add(i);	

        session.setAttribute("myItemSet",myItems);
        

            //RequestDispatcher rd = request.getRequestDispatcher("/myCart.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/music.jsp");
            rd.forward(request, response);
        }
        
        
        
        if (action.equals("Addmusic")) {
        
        
        String[] musicAdded = request.getParameterValues("music"); 
        /*ArrayList<String> aux = new ArrayList<>();
        for(int i=0; i<musicAdded.length; i++)
        	aux.add(musicAdded[i]);*/
        
        /*for(int i=0; i<musicAdded.length; i++)
        	myItems.add(musicAdded[i]);	

        session.setAttribute("myItemSet",myItems);*/
        
        for(String music : musicAdded)
        	for(Item i : itemList)
        		if(i.getName().equals(music))
        			myItems.add(i);	

        session.setAttribute("myItemSet",myItems);
        

            RequestDispatcher rd = request.getRequestDispatcher("/computers.jsp");
            rd.forward(request, response);
        }

        
        else if (action.equals("Addcomputers")) {    
        String[] pcAdded = request.getParameterValues("computers"); 
        //ArrayList<String> aux = new ArrayList<>();
        /*for(int i=0; i<pcAdded.length; i++)
        	aux.add(pcAdded[i]);*/
        /*
        for(int i=0; i<pcAdded.length; i++)
        	myItems.add(pcAdded[i]);	
        	*/
        	
        for(String pc : pcAdded)
        	for(Item i : itemList)
        		if(i.getName().equals(pc))
        			myItems.add(i);	


        session.setAttribute("myItemSet",myItems);
        

            RequestDispatcher rd = request.getRequestDispatcher("/myCart.jsp");
            rd.forward(request, response);
        }
        
        else if(action.equals("remove")) {
            
            String del = request.getParameter("crn");
            int delItem = Integer.parseInt(del);
            
            for(Item i : itemList)
        		if(i.getId()==(delItem))
        			myItems.remove(i);	
        			
        	session.setAttribute("myItemSet", myItems);		
           
            RequestDispatcher rd = request.getRequestDispatcher("/myCart.jsp");
            rd.forward(request, response);
        
        
    }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
