package com.spring.finalproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.finalproject.dao.OrderDAO;
import com.spring.finalproject.dao.ProductDAO;
import com.spring.finalproject.model.CartInfo;
import com.spring.finalproject.model.CartLineInfo;
import com.spring.finalproject.model.CustomerInfo;
import com.spring.finalproject.model.OrderInfo;
import com.spring.finalproject.model.PaginationResult;
import com.spring.finalproject.model.ProductInfo;
import com.spring.finalproject.pojo.Product;
import com.spring.finalproject.util.Utils;
import com.spring.finalproject.validator.CartInfoValidator;
import com.spring.finalproject.validator.CustomerInfoValidator;
import com.spring.finalproject.view.PdfReportView;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
 
@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class MainController {
 
    @Autowired
    private OrderDAO orderDAO;
 
    @Autowired
    private ProductDAO productDAO;
 
    @Autowired
    private CustomerInfoValidator customerInfoValidator;
    
    //@Autowired
    //private CartInfoValidator cartInfoValidator;
 
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        // For Cart Form.
        // (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
        if (target.getClass() == CartInfo.class) {
        	 //dataBinder.setValidator(cartInfoValidator);
        }
        // For Customer Form.
        // (@ModelAttribute("customerForm") @Validated CustomerInfo
        // customerForm)
        else if (target.getClass() == CustomerInfo.class) {
            dataBinder.setValidator(customerInfoValidator);
        }
 
    }
 
    @RequestMapping("/403")
    public String accessDenied() {
        return "/403";
    }
 
    @RequestMapping("/")
    public String home() {
        return "index";
    }
    
    @RequestMapping("/done")
    public String done(HttpServletRequest request) {
    		// Remove Cart In Session.
        Utils.removeCartInSession(request);
        return "index";
    }
   
    /*
    @RequestMapping(value= "/ajaxservice.htm", method=RequestMethod.POST)
	@ResponseBody
    public String listProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 4;
        final int maxNavigationPage = 10;
        System.out.println("Entered inside Controller");
        String queryString = request.getParameter("product");
		String result = "";
 
        PaginationResult<ProductInfo> result1 = productDAO.queryProducts(page, //
                maxResult, maxNavigationPage, queryString);
 
        for(ProductInfo pf:result1.getList())
        {
        	System.out.println("Entered inside for loop");
        		if(pf.getName().toLowerCase().contains(queryString.toLowerCase()))
        			result += pf.getName()+" , ";
        }
        System.out.println(result);
        //model.addAttribute("paginationProducts", result1);
        //return "productList";
        
        return result;
    }*/
 
    
    // Product List page.
    @RequestMapping({ "/productList" })
    public String listProductHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 4;
        final int maxNavigationPage = 10;
 
        PaginationResult<ProductInfo> result = productDAO.queryProducts(page, //
                maxResult, maxNavigationPage, likeName);
 
        model.addAttribute("paginationProducts", result);
        return "productList";
    }
	
    
	// Products List page with search
	@RequestMapping(path ="/productsList", method=RequestMethod.GET)
    public String getProducts(Model model) throws JsonProcessingException {
    	
    		List<ProductInfo> pl = productDAO.getAllProducts();
    		
    		ObjectMapper om = new ObjectMapper();
    		String res = om.writeValueAsString(pl);
    		System.out.println(res);
    		model.addAttribute("JSONProductdata", res);
    		return "productsList";
    }
    
 
    @RequestMapping({ "/buyProduct" })
    public String listProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
 
        Product product = null;
        if (code != null && code.length() > 0) {
            product = productDAO.findProduct(code);
        }
        if (product != null) {
 
            // Cart info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);
 
            ProductInfo productInfo = new ProductInfo(product);
 
            cartInfo.addProduct(productInfo, 1);
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "code", defaultValue = "") String code) {
        Product product = null;
        if (code != null && code.length() > 0) {
            product = productDAO.findProduct(code);
        }
        if (product != null) {
 
            // Cart Info stored in Session.
            CartInfo cartInfo = Utils.getCartInSession(request);
 
            ProductInfo productInfo = new ProductInfo(product);
 
            cartInfo.removeProduct(productInfo);
 
        }
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    // POST: Update quantity of products in cart.
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("cartForm") @Validated CartInfo cartForm,
            BindingResult result, //
            final RedirectAttributes redirectAttributes
            ) {
        
    		/*
    		// If has Errors.
        if (result.hasErrors()) {
            cartForm.setValid(false);
            // Forward to reenter info.
            return "shoppingCart";
        }*/
        
        
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
 
        // Redirect to shoppingCart page.
        return "redirect:/shoppingCart";
    }
 
    // GET: Show Cart
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);
 
        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }
 
    // GET: Enter customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
 
        CartInfo cartInfo = Utils.getCartInSession(request);
      
        // Cart is empty.
        if (cartInfo.isEmpty()) {
             
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        }
 
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        if (customerInfo == null) {
            customerInfo = new CustomerInfo();
        }
 
        /*Didn't work
        //Trying for XSS filter
        CustomerInfo customerInfo2 = new CustomerInfo();
        customerInfo2.setName(customerInfo.getName());
        customerInfo2.setEmail(customerInfo.getEmail());
        customerInfo2.setPhone(customerInfo.getPhone());
        customerInfo2.setAddress(customerInfo.getAddress());
        
        model.addAttribute("customerForm", customerInfo2);*/
        model.addAttribute("customerForm", customerInfo);	//passing an empty object to POST
        return "shoppingCartCustomer";
    }
 
    // POST: Save customer information.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("customerForm") @Validated CustomerInfo customerForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
  
        // If has Errors.
        if (result.hasErrors()) {
            customerForm.setValid(false);
            // Forward to reenter customer info.
            return "shoppingCartCustomer";
        }
 
        /*
      //Trying for XSS filter
        CustomerInfo customerInfo2 = new CustomerInfo();
        customerInfo2.setName(customerForm.getName());
        customerInfo2.setEmail(customerForm.getEmail());
        customerInfo2.setPhone(customerForm.getPhone());
        customerInfo2.setAddress(customerForm.getAddress());
        customerInfo2.setValid(true);*/
        
        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        //cartInfo.setCustomerInfo(customerInfo2);
        cartInfo.setCustomerInfo(customerForm);
 
        // Redirect to Confirmation page.
        return "redirect:/shoppingCartConfirmation";
    }
 
    // GET: Review Cart to confirm.
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
 
        return "shoppingCartConfirmation";
    }
 
    // POST: Send Cart (Save).
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);
 
        // Cart have no products.
        if (cartInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {
            // Enter customer info.
            return "redirect:/shoppingCartCustomer";
        }
        try {
            orderDAO.saveOrder(cartInfo);
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            return "shoppingCartConfirmation";
        }
        
        /*
        // Remove Cart In Session.
        Utils.removeCartInSession(request);
        */
        
        // Store Last ordered cart to Session.
        Utils.storeLastOrderedCartInSession(request, cartInfo);
 		
       
        
        //Sending details to mail
        try {
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        String email = customerInfo.getEmail();
        String info = "Name: "+customerInfo.getName()+"\n Address:"+customerInfo.getAddress() +"\n Email:"+customerInfo.getEmail()+"\n Mobile Num:"+customerInfo.getPhone();
        String ordInfo = "Order Num: "+cartInfo.getOrderNum()+"\n Quantity: "+ cartInfo.getQuantityTotal()+"\n Amount:"+cartInfo.getAmountTotal();
        sendEmail(email, "Your order details placed on Online Book Shopping Website"+"\n\n\n Customer details :\n" +info+"\n\n\n Order details :\n" +ordInfo);
        }catch(Exception e) {
        		System.out.println("Exception occurred in sending mail" + e.getMessage());
        }
        
        System.out.println("Mail sent");
        // Redirect to successful page.
        return "redirect:/shoppingCartFinalize";
    }
 
    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {
    	//CartInfo cartInfo = Utils.getCartInSession(request);
    	
        
        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
 
        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
 
        return "shoppingCartFinalize";
    }
    
    
    @RequestMapping(value = { "/OrderConfirmation.pdf" }, method = RequestMethod.GET)
    public ModelAndView createReport(HttpServletRequest request, Model model) {
    		CartInfo cartInfo = Utils.getCartInSession(request);
    	
    		System.out.println("Inside confirm order but before removing" + cartInfo.getOrderNum());
    		/* // Remove Cart In Session.
        Utils.removeCartInSession(request);
        
        // Store Last ordered cart to Session.
        Utils.storeLastOrderedCartInSession(request, cartInfo);
        
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        List<CartLineInfo> cartLineInfo = cartInfo.getCartLines();
        
        
        for(CartLineInfo c:cartLineInfo ) {
        	c.getProductInfo();
        	c.getQuantity();
        	c.getAmount();
        }
        */
        
        
        //System.out.println("Inside confirm order but after removing" + cartInfo.getOrderNum());
        ModelAndView mv= new ModelAndView(new PdfReportView());
        mv.addObject("CartInfo",cartInfo);
		//mv.addObject("Customer Info", customerInfo);
		//mv.addObject("CartInfo", cartLineInfo);
		
		return mv;
        
    }
    
    
    
 
    @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("code") String code) throws IOException {
        Product product = null;
        if (code != null) {
            product = this.productDAO.findProduct(code);
        }
        if (product != null && product.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(product.getImage());
        }
        response.getOutputStream().close();
    }
    
    
	public void sendEmail(String useremail, String message) {

		
		//Creating an email from which you want to send to the user 
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com","springmvc")); 
		email.setSSLOnConnect(true);
		
		try {email.setFrom("no-reply@msis.neu.edu"); //This user email doesnot exist
		
			email.setMsg(message);
		
			email.addTo(useremail);
		
			email.send();
		}catch (EmailException e) {
			// TODO Auto-generated catch block
			System.out.println("Error so exception occurred" + e.getMessage());
		}
		
		
	}
     
}
