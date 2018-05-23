package com.spring.finalproject.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.captcha.botdetect.web.servlet.Captcha;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.finalproject.dao.AccountDAO;
import com.spring.finalproject.dao.OrderDAO;
import com.spring.finalproject.dao.ProductDAO;
import com.spring.finalproject.impl.AccountDAOImpl;
import com.spring.finalproject.model.OrderDetailInfo;
import com.spring.finalproject.model.OrderInfo;
import com.spring.finalproject.model.PaginationResult;
import com.spring.finalproject.model.ProductInfo;
import com.spring.finalproject.pojo.Account;
import com.spring.finalproject.validator.ProductInfoValidator;
import com.spring.finalproject.view.XlsView;

@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class AdminController {

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private ProductInfoValidator productInfoValidator;

	// Configurated In ApplicationContextConfig.
	@Autowired
	private ResourceBundleMessageSource messageSource;

	@InitBinder
	public void myInitBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == ProductInfo.class) {
			dataBinder.setValidator(productInfoValidator);
			// For upload Image.
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}

	// Create User of Employee role
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm() {

		return "user-create-form";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String handleCreateForm(HttpServletRequest request, ModelMap map) {

		// Need to validate the input first
		// Captcha object
		Captcha captcha = Captcha.load(request, "exampleCaptcha");

		// Captcha code user has entered
		String captchaCode = request.getParameter("captchaCode");

		// Creating a session
		HttpSession session = request.getSession();

		// Checking if user already exists
		Account aa = accountDAO.findAccount(request.getParameter("username"));
		if (aa != null) {
			map.addAttribute("errorMessage", "User already existss");
			return "user-create-form";
		}

		/*
		 * //Checking if user already exists UserDetails userDetails = (UserDetails)
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 * if(userDetails.getUsername().equalsIgnoreCase(request.getParameter("username"
		 * ))) { map.addAttribute("errorMessage", "User already existss"); return
		 * "user-create-form"; }
		 */

		if (captcha.validate(captchaCode)) {
			// If captcha code is correct
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			Account a = new Account();
			a.setUserName(username);
			a.setPassword(password);
			a.setActive(false);
			a.setUserRole("EMPLOYEE");

			try {
				Account acc = accountDAO.register(a);
				Random random = new Random();
				int key1 = random.nextInt(3000000);
				int key2 = random.nextInt(3000000); // Using these keys to check if user has clicked the validate email
													// ID link

				String url = "http://localhost:8080/finalproject/validateemail.htm?email=" + username + "&key1=" + key1
						+ "&key2=" + key2;

				// Storing keys in the session
				session.setAttribute("key1", key1);
				session.setAttribute("key2", key2);

				if (acc != null)
					sendEmail(username, "Click on the link to activate your account \n" + url);

				return "user-created";

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			map.addAttribute("errorMessage", "Invalid Captcha Code");
			return "user-create-form";

		}

		return null;
	}

	@RequestMapping(value = "/validateemail.htm", method = RequestMethod.GET)
	public String validateEmail(HttpServletRequest request, ModelMap map) {

		// The user will be sent the following link when the use registers
		// This is the format of the email
		// http://localhost:8080/finalproject/validateemail.htm?email=useremail&key1=<random_number>&key2=<body
		// of the email that when user registers>
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		int key1 = Integer.parseInt(request.getParameter("key1"));
		int key2 = Integer.parseInt(request.getParameter("key2"));
		System.out.println(session.getAttribute("key1"));
		System.out.println(session.getAttribute("key2"));

		if ((Integer) (session.getAttribute("key1")) == key1 && ((Integer) session.getAttribute("key2")) == key2) {
			try {
				System.out.println("HI________");
				boolean updateStatus = accountDAO.updateUser(email);
				if (updateStatus) {
					return "login";
				} else {

					return "error";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			map.addAttribute("errorMessage", "Link expired , register once again");
			map.addAttribute("resendLink", true);
			return "error";
		}

		return "login";
	}

	@RequestMapping(value = "/resendemail.htm", method = RequestMethod.POST)
	public String resendEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String useremail = request.getParameter("username");
		Random rand = new Random();
		int randomNum1 = rand.nextInt(5000000);
		int randomNum2 = rand.nextInt(5000000);
		try {
			String str = "http://localhost:8080/finalproject/validateemail.htm?email=" + useremail + "&key1="
					+ randomNum1 + "&key2=" + randomNum2;
			session.setAttribute("key1", randomNum1);
			session.setAttribute("key2", randomNum2);
			sendEmail(useremail, "Click on this link to activate your account : " + str);
		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}

		return "user-created";
	}

	// Forgot password
	@RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
	public String getForgotPasswordForm(HttpServletRequest request) {

		return "forgot-password";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String handleForgotPasswordForm(HttpServletRequest request) {
		String useremail = request.getParameter("useremail");
		// Need to validate the input first
		// Captcha object
		Captcha captcha = Captcha.load(request, "exampleCaptcha");

		// Captcha code user has entered
		String captchaCode = request.getParameter("captchaCode");

		if (captcha.validate(captchaCode)) {

			Account aa = accountDAO.findAccount(useremail);

			//if (aa.getUserName().equalsIgnoreCase(useremail)) 
			if(aa!=null){
				System.out.println(aa.getUserName());
				sendEmail(useremail, "Your password is : " + aa.getPassword());
				return "forgot-password-success";
			} else {
				request.setAttribute("captchamsg", "Username not valid");
				return "forgot-password";
			}

		} else {
			request.setAttribute("captchamsg", "Captcha not valid");
			return "forgot-password";
		}

	}

	// GET: Show Login Page
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.isEnabled());

		model.addAttribute("userDetails", userDetails);
		return "accountInfo";
	}

	@RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
	public String orderList(Model model, //
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		final int MAX_RESULT = 10;
		final int MAX_NAVIGATION_PAGE = 10;

		PaginationResult<OrderInfo> paginationResult //
				= orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

		model.addAttribute("paginationResult", paginationResult);
		return "orderList";
	}
	
	/*
	@RequestMapping(path ="/ordersList", method=RequestMethod.GET)
	@ResponseBody
	public List<OrderInfo> getAllOrders(){
		System.out.println("Entered controller");
		List<OrderInfo> ll = orderDAO.getAllOrders();
		System.out.println("Printing the size of orders table" + ll.size());
		return orderDAO.getAllOrders();
	}*/
	
	@RequestMapping(path ="/ordersList", method=RequestMethod.GET)
	public String getAllOrders(Model model) throws JsonProcessingException{
		System.out.println("Entered controller");
		List<OrderInfo> ll = orderDAO.getAllOrders();
		System.out.println("Printing the size of orders table" + ll.size());
		ObjectMapper om = new ObjectMapper();
		String res = om.writeValueAsString(ll);
		System.out.println(res);
		model.addAttribute("JSONdata", res);
		return "ordersList";
		
	}
	
	//Saving the order List
	@RequestMapping(value = { "/saveToXls" }, method = RequestMethod.POST)
	public ModelAndView orderList(HttpServletRequest request,//
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			
			//page = Integer.parseInt(pageStr);
			page = Integer.parseInt(request.getParameter("currPageId"));
		} catch (Exception e) {
		}
		final int MAX_RESULT = 10;
		//int MAX_RESULT = Integer.parseInt(request.getParameter("pageSize"));
		final int MAX_NAVIGATION_PAGE = 10;

		PaginationResult<OrderInfo> paginationResult //
				= orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
		List<OrderInfo> o = paginationResult.getList();

		System.out.println("Page number is :"+request.getParameter("currPageId"));
		System.out.println("Page size is :");
		
		ModelAndView mv = new ModelAndView(new XlsView());
		mv.addObject("OrderList", o);
		//mv.addObject("paginationResult", paginationResult);
		mv.addObject("page", request.getParameter("currPageId"));
		return mv;
	}

	// GET: Show product.
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
		ProductInfo productInfo = null;

		if (code != null && code.length() > 0) {
			productInfo = productDAO.findProductInfo(code);
		}
		if (productInfo == null) {
			productInfo = new ProductInfo();
			productInfo.setNewProduct(true);
		}
		model.addAttribute("productForm", productInfo);
		return "product";
	}

	// POST: Save product
	@RequestMapping(value = { "/product" }, method = RequestMethod.POST)
	// Avoid UnexpectedRollbackException (See more explanations)
	@Transactional(propagation = Propagation.NEVER)
	public String productSave(Model model, //
			@ModelAttribute("productForm") @Validated ProductInfo productInfo, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "product";
		}
		try {
			productDAO.save(productInfo);
		} catch (Exception e) {
			// Need: Propagation.NEVER?
			String message = e.getMessage();
			model.addAttribute("message", message);
			// Show product form.
			return "product";

		}
		return "redirect:/productList";
	}

	@RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	public String orderView(Model model, @RequestParam("orderId") String orderId) {
		OrderInfo orderInfo = null;
		if (orderId != null) {
			orderInfo = this.orderDAO.getOrderInfo(orderId);
		}
		if (orderInfo == null) {
			return "redirect:/orderList";
		}
		List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
		orderInfo.setDetails(details);

		model.addAttribute("orderInfo", orderInfo);

		return "order";
	}

	public void sendEmail(String useremail, String message) {

		// Creating an email from which you want to send to the user
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));
		email.setSSLOnConnect(true);

		try {
			email.setFrom("no-reply@msis.neu.edu"); // This user email doesnot exist

			email.setMsg(message);

			email.addTo(useremail);

			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			System.out.println("Error so exception occurred" + e.getMessage());
		}

	}

}
