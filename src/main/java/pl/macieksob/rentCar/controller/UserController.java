package pl.macieksob.rentCar.controller;

import net.bytebuddy.utility.RandomString;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.macieksob.rentCar.dto.UserDTO;
import pl.macieksob.rentCar.service.MailService;
import pl.macieksob.rentCar.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/register")
    public String registerUser(UserDTO user, HttpServletRequest httpServletRequest) throws MessagingException, UnsupportedEncodingException {
        userService.addUser(user);
        String url = Utility.getURL(httpServletRequest);
        userService.sendVerificationEmail(user,url);

        return "";
    }

    @GetMapping("/verify")
    public String verifyAccount(String code){
        boolean verify = userService.verify(code);

        String pageTitle = verify ? "Weryfikacja przeszła pomyślnie!" : "Weryfikacja nieudana.";
        return "";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "";
    }

    @PostMapping("/forgot_password")
    public String processForgotPasswordForm(HttpServletRequest request){
        String email = request.getParameter("email");
        String token = RandomString.make(45);

        userService.updateResetPasswordToken(token,email);
        String resetPasswordLink = Utility.getURL(request)+"/reset_password?token=" + token;
        try {
            mailService.sendMailResetPassword(email,resetPasswordLink);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(String token){
        UserDTO user = userService.getUserByResetPasswordToken(token);

        if(user == null){

        }
        return "";
    }

    @PostMapping("/reset_password")
    public String processResetPasswordForm(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("token");
        String password = httpServletRequest.getParameter("password");

        UserDTO user = userService.getUserByResetPasswordToken(token);

        if(user == null){

        }else {
            userService.updatePassword(user,password );
        }

        return "";
    }
}
