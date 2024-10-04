package com.mobi.hotel.Controller;

import com.mobi.hotel.Model.User;
import com.mobi.hotel.Repository.UserRepo;
import com.mobi.hotel.Service.ServiceClass.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String showForgotPasswordPage() {
        return "ForgotPassword";
    }



    @RequestMapping(value = "/verifyforgotPassword", method = RequestMethod.POST)
    public String verifyUser(@RequestParam("username") String username,
                             @RequestParam("securityQuestion") String securityQuestion,
                             @RequestParam("securityAnswer") String securityAnswer,
                             HttpServletRequest request, Model model) {


        User user = userService.findUserByUsername(username);
        System.out.println("Username: " + username + " | Security Question: " + securityQuestion + " | Security Answer: " + securityAnswer+"   ");

        // Check if user exists and if the security question and answer match (case-insensitive and trimmed)
        if (user != null &&
                user.getSecurityQuestion().equalsIgnoreCase(securityQuestion.trim()) &&
                user.getSecurityAnswer().equalsIgnoreCase(securityAnswer.trim())) {

            // Store username in the session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            return "resetPassword";
        } else {

            model.addAttribute("error", "Invalid security question or answer.");
            return "ForgotPassword"; // Should map to ForgotPassword.jsp
        }
    }


    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPassword(@RequestParam("username") String username,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                HttpServletRequest request, Model model) {

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "resetPassword";
        }
        System.out.println(username);
        boolean success = userService.updatePassword(username, newPassword);
        if (success) {
            return "login";
        } else {
            model.addAttribute("error", "Failed to update password.");
            return "resetPassword";
        }
    }

}
