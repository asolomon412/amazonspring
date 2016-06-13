/**
 * 
 */
package com.gc.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.gc.model.dao.ProductsDAO;
import com.gc.model.dao.UserDAO;
import com.gc.model.dto.Product;
import com.gc.model.dto.User;
import com.gc.model.dto.UserRole;

/**
 * @author Maurice Tedder
 * 
 *         Java Apprenticeship Spring project PART 10: PROJECT Use Spring MVC
 *         with Hibernate to program a shopping portal. You can take Amazon as
 *         an example. You can choose anything to sell on that site. Your site
 *         should have accounts for users, where they can have usernames and
 *         passwords to login to their accounts. The website should also
 *         implement a shopping cart and checkout features. You should make two
 *         types of users, a seller and a buyer. A seller can add items to buy,
 *         and they can be rated.
 */

@Controller
@SessionAttributes("shoppingcart")
// ref:
// http://www.intertech.com/Blog/understanding-spring-mvc-model-and-session-attributes/
public class AppController {

	@Autowired
	ServletContext servletContext;

	private static final String PRODUCT_ADDED_MESSAGE = "Product Successfully Added";

	private static final String ADDED_TO_CART_MESSAGE = "Item added to your shopping cart. <br> <a href='showproducts' >Continue Shopping</a><br> <br> <a href='' >Checkout</a>";

	private static final Object THANK_YOU_MESSAGE = "Thank you for your order!";

	private static final Object CART_EMPTY_MESSAGE = "Your shopping cart is empty.";

	private static final Object USER_ADDED_MESSAGE = "You can now login to your account.";

	/*
	 * Show all products in DB
	 */
	@RequestMapping(value = "/showproducts", method = RequestMethod.GET)
	// @PreAuthorize("hasRole('ROLE_USER')") //you could also secure at the
	// method level using this line of code
	public ModelAndView showProducts() {
		// Get get ProductsDAO object using Spring framework
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/applicationContext.xml");
		context.setServletContext(servletContext);
		context.refresh();

		ProductsDAO productsDAO = (ProductsDAO) context.getBean("d");// (ProductsDAO)factory.getBean("d");
		
		List<Product> list = productsDAO.productList();
		return new ModelAndView("shop", "products", list);
	}

	/*
	 * Send model to addproduct form.
	 */
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public ModelAndView addProduct(Model model) {
		// Get get ProductsDAO object using Spring framework
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/applicationContext.xml");
		context.setServletContext(servletContext);
		context.refresh();
		//
		Product product = (Product) context.getBean("prod");// (ProductsDAO)factory.getBean("d");
		
		return new ModelAndView("addproduct", "command", product);
	}

	/*
	 * Add product to user shopping cart.
	 */
	@RequestMapping(value = "/addtocart", method = RequestMethod.GET)
	public String addToCart(@RequestParam("userid") String userid, @RequestParam("productID") String productID,
			@RequestParam("qty") String qty, Model model) {

		// Get get ProductsDAO object using Spring framework
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/applicationContext.xml");
		context.setServletContext(servletContext);
		context.refresh();
		
		model.addAttribute("message", "Product " + productID + " " + ADDED_TO_CART_MESSAGE);	

		Map<String, Object> cart = null;
		if (model.containsAttribute("shoppingcart")) {// shopping cart exist. Add/update product.
			cart = (Map<String, Object>) model.asMap().get("shoppingcart");
			// if product already exist then update
		} else {// create shopping cart
			cart = new HashMap<String, Object>();
		}
		cart.put(productID, qty);
		// Save shopping cart data in session object until users checkout
		model.addAttribute("shoppingcart", cart);

		// get products again - I could have also stored this in the session
		ProductsDAO productsDAO = (ProductsDAO) context.getBean("d");// (ProductsDAO)factory.getBean("d");
		List<Product> list = productsDAO.productList();
		model.addAttribute("products", list);

		return "shop";// return to shop page
	}

	/*
	 * Add new product to DB. Process form data from add product form. Only
	 * Users with a seller role are allowed to access this operation. ref:
	 * http://www.coderanch.com/t/424153/Spring/beans-xml-opened-exist
	 * http://www.programcreek.com/java-api-examples/index.php?api=org.
	 * springframework.web.context.support.XmlWebApplicationContext
	 */
	@RequestMapping(value = "/submitaddproduct", method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute Product product, Model model) {
		// Get get ProductsDAO object using Spring framework
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/applicationContext.xml");
		context.setServletContext(servletContext);
		context.refresh();

		ProductsDAO productsDAO = (ProductsDAO) context.getBean("d");// (ProductsDAO)factory.getBean("d");
		productsDAO.saveProduct(product);

		return new ModelAndView("result", "message", PRODUCT_ADDED_MESSAGE);
	}

	/*
	 * Take this users shopping cart from the session and add to orders table
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkOut(@ModelAttribute Product product, Model model) {

		if (model.containsAttribute("shoppingcart")) {// do nto display empty
														// cart

			// Get get ProductsDAO object using Spring framework
			XmlWebApplicationContext context = new XmlWebApplicationContext();
			context.setConfigLocation("/WEB-INF/applicationContext.xml");
			context.setServletContext(servletContext);
			context.refresh();

			// get products again - I could have also stored this in the session
			// because products is used across different jsp's
			ProductsDAO productsDAO = (ProductsDAO) context.getBean("d");// (ProductsDAO)factory.getBean("d");
			List<Product> list = productsDAO.productList();
			model.addAttribute("products", list);

			return new ModelAndView("checkout", "message", THANK_YOU_MESSAGE);
		} else {
			return new ModelAndView("checkout", "message", CART_EMPTY_MESSAGE);
		}
	}

	/*
	 * Cancel user order by invalidating the shoppingcart session object Ref:
	 * https://www.javacodegeeks.com/2013/04/spring-mvc-session-tutorial.html
	 * http://vmustafayev4en.blogspot.com/2012/10/power-of-springs-
	 * modelattribute-and.html
	 */
	@RequestMapping(value = "/cancelcart", method = RequestMethod.GET)
	public ModelAndView cancelCart(@ModelAttribute("shoppingcart") HashMap<String, Object> something,
			SessionStatus sessionStatus) {

		sessionStatus.setComplete();

		return new ModelAndView("checkout", "message", CART_EMPTY_MESSAGE);
	}

	/*
	 * Login Spring security controller Ref:
	 * http://www.beingjavaguys.com/2014/05/spring-security-authentication-and.
	 * html
	 * http://docs.spring.io/spring-security/site/docs/4.1.0.RELEASE/reference/
	 * htmlsingle/#what-is-acegi-security
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginForm(@RequestParam(required = false) String authfailed, String logout, String denied) {
		String message = "";
		if (authfailed != null) {
			message = "Invalid username of password, try again!";
		} else if (logout != null) {
			message = "Logged Out successfully, login again to continue!";
		} else if (denied != null) {
			message = "Access denied for this user!";
		}
		return new ModelAndView("login_page", "message", message);
	}

	/*
	 * Login failed
	 */
	@RequestMapping("403page")
	public String ge403denied() {
		return "redirect:/login?denied";
	}

	/*
	 * Sign up form
	 */
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public ModelAndView addUser(Model model) {		
		// Get get User object using Spring framework
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/applicationContext.xml");
		context.setServletContext(servletContext);
		context.refresh();
		//
		User user = (User) context.getBean("usr");
		
		return new ModelAndView("signup", "command", user);
	}

	/*
	 * Add new user to DB. Process form data from add user form. Only ref:
	 * http://www.coderanch.com/t/424153/Spring/beans-xml-opened-exist
	 * http://www.programcreek.com/java-api-examples/index.php?api=org.
	 * springframework.web.context.support.XmlWebApplicationContext
	 */
	@RequestMapping(value = "/submitadduser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user, Model model) {
		// Get get ProductsDAO object using Spring framework
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/applicationContext.xml");
		context.setServletContext(servletContext);
		context.refresh();
		// Set username for Userrole object
		String username = user.getUsername();
		user.getUserrole().setUsername(username);

		UserDAO userDAO = (UserDAO) context.getBean("u");// (ProductsDAO)factory.getBean("d");
		userDAO.saveUser(user);	

		return new ModelAndView("result", "message", USER_ADDED_MESSAGE);
	}

	/*
	 * List all users in DB. ref:
	 * http://www.coderanch.com/t/424153/Spring/beans-xml-opened-exist
	 * http://www.programcreek.com/java-api-examples/index.php?api=org.
	 * springframework.web.context.support.XmlWebApplicationContext
	 */
	@RequestMapping(value = "/listusers", method = RequestMethod.GET)
	public ModelAndView listUsers() {
		// Get get ProductsDAO object using Spring framework
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/applicationContext.xml");
		context.setServletContext(servletContext);
		context.refresh();

		UserDAO userDAO = (UserDAO) context.getBean("u");// (ProductsDAO)factory.getBean("d");
		List<User> userList = userDAO.listSellers();		

		return new ModelAndView("listusers", "userlist", userList);
	}

	/*
	 * Send model to addproduct form.
	 */
	@RequestMapping(value = "/rateseller", method = RequestMethod.GET)
	public ModelAndView rateseller(@RequestParam("userid") String userid, @RequestParam("username") String username,
			Model model) {
		// Get get UserRole object using Spring framework
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/applicationContext.xml");
		context.setServletContext(servletContext);
		context.refresh();
		
		UserRole userRole = (UserRole) context.getBean("ur");//	
		userRole.setUserid(Long.parseLong(userid));
		userRole.setUsername(username);

		return new ModelAndView("rateseller", "command", userRole);
	}

	/*
	 * Update this sellers rating
	 */
	@RequestMapping(value = "/submitsellerrating", method = RequestMethod.POST)
	public String rateseller(@ModelAttribute UserRole userRole, Model model) {		
		// Get get ProductsDAO object using Spring framework
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		context.setConfigLocation("/WEB-INF/applicationContext.xml");
		context.setServletContext(servletContext);
		context.refresh();

		UserDAO userDAO = (UserDAO) context.getBean("u");
		userDAO.updatRating(userRole);
		
		return "redirect:listusers";//redirect back to list users page
	}
}
