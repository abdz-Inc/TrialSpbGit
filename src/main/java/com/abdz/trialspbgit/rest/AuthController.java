package com.abdz.trialspbgit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abdz.trialspbgit.enitity.User;
import com.abdz.trialspbgit.form.RegisterForm;
import com.abdz.trialspbgit.service.PowerService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
     
     public PowerService powerService;
     @Autowired
     public AuthController(PowerService powerService) {
         this.powerService = powerService;
     }
     
     @PostMapping("/update")
    public String update(@ModelAttribute("registerform") RegisterForm reg, Model model ,HttpSession session){

        User user = (User) session.getAttribute("usr");
        if(user==null) {
            return "redirect:/test/login";
        }
        // System.out.println(reg);

         user.setDescription(reg.getDescription());
         user.setGmail(reg.getGmail());
         user.setUsername(reg.getUsername());       
         session.setAttribute("usr", user);;
         powerService.userDAO.update(user); 
         return "products";
    }
         

     @PostMapping("/register")
    public String register(@ModelAttribute("registerform") RegisterForm reg, Model model ,HttpSession session,RedirectAttributes redirectAttributes){
         
         String extractedString = reg.getWalletaccntno().replace("walletaccntno=", "");
         User user = powerService.userDAO.findByWalletAccntNo(extractedString);          
         if(user==null) {
              powerService.createUserFromForm(reg);          
              user = powerService.userDAO.findByWalletAccntNo(extractedString);          
              session.setAttribute("usr", user);
              redirectAttributes.addFlashAttribute("user", user);
              return "redirect:/test/products";
          }else{
              session.setAttribute("usr", user);
              redirectAttributes.addFlashAttribute("user", user);
               return "redirect:/test/login";
         }         
     }


     
     @PostMapping("/login")
    public String login(@RequestBody String walletaccntno,HttpSession session, RedirectAttributes redirectAttributes){
          String extractedString = walletaccntno.replace("walletaccntno=", "");
          User user = powerService.userDAO.findByWalletAccntNo(extractedString);          
          if(user==null) return "redirect:/test/login";
          session.setAttribute("usr", user);
          redirectAttributes.addFlashAttribute("user", user);
	     return "redirect:/test/products";
     }

     @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("usr");
        return "logout";
       
     }


}
