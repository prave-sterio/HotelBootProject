package com.mobi.hotel.Controller;
import com.mobi.hotel.Model.User;
import com.mobi.hotel.Service.ServiceClass.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public  String home()
    {
        return "index";
    }

    @RequestMapping(value = "tologin")
    public String tologin() {
        return "login";
    }

    @RequestMapping("/homeB")
    public String homeB() {
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, HttpSession session) {
        boolean result = false;
        System.out.println("In controller" + username);
        result = userService.validateUser(username, password);
        if (result) {
            System.out.println("out controller true");
            session.setAttribute("username", username);
            return "Check";
        } else {
            // Set error message in session
            session.setAttribute("ErrorMessage", "Invalid username or password");
            return "login";  // Stay on login page with error message
        }
    }

    @RequestMapping(value = "/Register")
    public String signUpUser(@RequestParam("username") String username,
                             @RequestParam("phone") Long phone,
                             @RequestParam("password") String password,
                             @RequestParam("confirm-password") String confirmPassword,
                             @RequestParam("securityQuestion") String securityQuestion,
                             @RequestParam("securityAnswer") String securityAnswer,
                             Model model, HttpSession session) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "SignUp";
        }

       if (userService.isUserExists(username)) {
            model.addAttribute("error", "Username already exists!");
            return "SignUp";
       }

        User user = new User(username, password, phone, securityQuestion, securityAnswer);
        userService.saveUser(user);
        session.setAttribute("username", username);
        return "login";
    }

    @RequestMapping(value = "/signup")
    public String showSignUpForm() {
        return "SignUp";
    }

    @RequestMapping(value="/back")
    public String backbutton() {
        return "login";
    }


}