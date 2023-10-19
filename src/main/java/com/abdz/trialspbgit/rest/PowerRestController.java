package com.abdz.trialspbgit.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.abdz.trialspbgit.enitity.Product;
import com.abdz.trialspbgit.enitity.Request;
import com.abdz.trialspbgit.enitity.User;
import com.abdz.trialspbgit.form.ProductForm;
import com.abdz.trialspbgit.form.RegisterForm;
import com.abdz.trialspbgit.form.RequestForm;
import com.abdz.trialspbgit.service.PowerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/test")
public class PowerRestController{

    private PowerService powerService;

    
    @Autowired
    public PowerRestController(PowerService powerService) {
        this.powerService = powerService;
    }

	@GetMapping("/register")
    public String registerPage(Model model){
	model.addAttribute("registerform", new RegisterForm());
	return "register";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
	return "login";
    }

    @GetMapping("/")
    public String hello(){
        return "login";
    }

    @GetMapping("/products")
	public String generateProductListController(@ModelAttribute("user") Object user, Model model, HttpSession session)
	{
		
		List<Product> products = powerService.getAllProducts();
		HashMap<Product, User> productanduser = powerService.getUserAndProduct(products);
		System.out.println(user);
		if(session.getAttribute("usr") == null) {
			if(!(user instanceof User))
			{
				System.out.println("no user found");
				return "login";
			}
			User usr = (User)user;
			session.setAttribute("usr", usr);
			// System.out.println(usr);
		}
		
		// for(Product r:products)
		// 	{
		// 		System.out.println(r);
		// 	}
		model.addAttribute("products", productanduser);
		return "products";
	}


	@GetMapping("/buydetails")
	public String buyDetailsController(@RequestParam("pid") int pid, HttpSession session, Model model) {
		if(session.getAttribute("usr") == null) {
			// User usr = powerService.getUser(1);
			// session.setAttribute("usr", usr);
			return "login";
		}
		else{
			User usr = (User) session.getAttribute("usr");
		}
			Product product = powerService.getProduct(pid);
			User seller = powerService.getUser(product.getUid());
			System.out.println(product);
			model.addAttribute("product", product);
			model.addAttribute("seller", seller);
			model.addAttribute("requestform", new RequestForm());
		return "buydetails";
		
	}

	@PostMapping("/sendrequest")
	public String sendRequest(@ModelAttribute("requestform") RequestForm requestform, HttpSession session, Model model)
	{
		User usr;
		System.out.println(requestform);
		if(session.getAttribute("usr") == null) {
			// usr = powerService.getUser(1);
			// session.setAttribute("usr", usr);
			return "login";
		}
        else {
			usr = (User) session.getAttribute("usr");
		}
			Request request = powerService.createRequestFromForm(requestform,usr.getUid());
			model.addAttribute("request", request);
			if( request != null)
			{
				return "requestsuccess";
			}
			
		return "requestfailed";
	}

	@GetMapping("/acceptrequest")
	public String acceptRequest(@RequestParam("requestid") int requestid, HttpSession session, Model model)
	{
		User usr;
		if(session.getAttribute("usr") == null) {
			// usr = powerService.getUser(2);
			// session.setAttribute("usr", usr);
			return "login";
		}
        else {
			usr = (User) session.getAttribute("usr");
		}
		Request request = powerService.acceptRequest(requestid,usr.getUid());
		model.addAttribute("request", request);
		if( request != null)
		{
			return "requestacceptfailed";
		}
		return "requestaccepted";
	}

	@GetMapping("/rejectrequest")
	public String rejectRequest(@RequestParam("requestid") int requestid, HttpSession session, Model model)
	{
		User usr;
		if(session.getAttribute("usr") == null) {
			// usr = powerService.getUser(2);
			// session.setAttribute("usr", usr);
			return "login";
		}
        else {
			usr = (User) session.getAttribute("usr");
		}
		Request request = powerService.rejectRequest(requestid);
		model.addAttribute("request", request);
		if( request != null)
		{
			return "requestrejectfailed";
		}
		return "requestrejected";
	}

	@GetMapping("/payproposal")
	public String payProposal(@RequestParam("requestid") int requestid, HttpSession session, Model model)
	{
		User usr;
		if(session.getAttribute("usr") == null) {
			// usr = powerService.getUser(2);
			// session.setAttribute("usr", usr);
			return "login";
		}
        else {
			usr = (User) session.getAttribute("usr");
		}
		Request request = powerService.paymentCompleted(requestid, usr.getUid());
		model.addAttribute("request", request);
		if( request != null)
		{
			return "proposalpaymentfailed";
		}
		return "proposalpaid";
	}

	@GetMapping("/createproduct")
	public String createProductController( HttpSession session, Model model) {
		
		User usr;
		if(session.getAttribute("usr") == null) {
			// usr = powerService.getUser(1);
			// session.setAttribute("usr", usr);
			return "login";
		}
        else {
			usr = (User) session.getAttribute("usr");
		}
			System.out.println("user not null");
			double width = ((powerService.getPowerOnSale(usr.getUid()) * 1.0)/ (powerService.getAvailablePower(usr.getUid()) * 1.0))*100;
            model.addAttribute("available", powerService.getAvailablePower(usr.getUid()));
            model.addAttribute("onsale", powerService.getPowerOnSale(usr.getUid()));
			model.addAttribute("left", powerService.getAvailablePower(usr.getUid()) - powerService.getPowerOnSale(usr.getUid()));
            model.addAttribute("width", width);
			model.addAttribute("username", usr.getUsername());
			model.addAttribute("productform", new ProductForm());
		return "createProduct";
	}

	@PostMapping("/addproduct")
	public String addproduct(@ModelAttribute("requestform") ProductForm productForm, HttpSession session, Model model)
	{
		User usr;
		System.out.println(productForm);
		if(session.getAttribute("usr") == null) {
			// usr = powerService.getUser(1);
			// session.setAttribute("usr", usr);
			return "login";
		}
        else {
			usr = (User) session.getAttribute("usr");
		}
			Product product = powerService.createProductFromForm(productForm,usr.getUid());
			model.addAttribute("product", product);
			if( product != null)
			{
				return "productsuccess";
			}
			
		return "productfailed";
	}

	@GetMapping("/showrequests")
	public String generateRequestListController(HttpSession session, Model model)
	{
		
		User usr;
		if(session.getAttribute("usr") == null) {
			// usr = powerService.getUser(2);
			// session.setAttribute("usr", usr);
			return "login";
		}
		else {
			usr = (User) session.getAttribute("usr");
		}
		model.addAttribute("user", usr);
		List<Request> recvrequests = powerService.getAllRecvRequests(usr.getUid());
		HashMap<User, Request> recvrequestsanduser = powerService.getUserAndRequest(recvrequests);
		
		model.addAttribute("recvrequests", recvrequestsanduser);
		
		
		return "requests";
	}

	@GetMapping("/showbids")
	public String generateBidController(HttpSession session, Model model)
	{
		
		User usr;
		if(session.getAttribute("usr") == null) {
			// usr =powerService.getUser(1);
			// session.setAttribute("usr", usr);
			return "login";
		}
		else {
			usr = (User) session.getAttribute("usr");
		}
		model.addAttribute("user", usr);
		List<Request> sentrequests = powerService.getAllSentRequests(usr.getUid());
		HashMap<User, Request> sentrequestsanduser = powerService.getUserAndProposal(sentrequests);
		model.addAttribute("sentrequests", sentrequestsanduser);
		
		
		return "proposal";
	}

	@GetMapping("/profile")
	public String userProfile(Model model, HttpSession session) {
		
		User usr;
		if(session.getAttribute("usr") == null) {
			// usr =powerService.getUser(1);
			// session.setAttribute("usr", usr);
			return "login";
		}
		else {
			usr = (User) session.getAttribute("usr");
		}
		double width = ((powerService.getPowerOnSale(usr.getUid()) * 1.0)/ (powerService.getAvailablePower(usr.getUid()) * 1.0))*100;
            model.addAttribute("available", powerService.getAvailablePower(usr.getUid()));
            model.addAttribute("onsale", powerService.getPowerOnSale(usr.getUid()));
			model.addAttribute("left", powerService.getAvailablePower(usr.getUid()) - powerService.getPowerOnSale(usr.getUid()));
            model.addAttribute("width", width);
		model.addAttribute("user", usr);
		return "profile";
	}

}