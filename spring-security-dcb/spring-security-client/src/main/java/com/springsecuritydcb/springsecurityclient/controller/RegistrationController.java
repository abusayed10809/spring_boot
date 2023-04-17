package com.springsecuritydcb.springsecurityclient.controller;

import com.springsecuritydcb.springsecurityclient.entity.User;
import com.springsecuritydcb.springsecurityclient.entity.VerificationToken;
import com.springsecuritydcb.springsecurityclient.event.RegistrationCompleteEvent;
import com.springsecuritydcb.springsecurityclient.model.PasswordModel;
import com.springsecuritydcb.springsecurityclient.model.UserModel;
import com.springsecuritydcb.springsecurityclient.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        System.out.println("inside verify registration");
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User verification successful";
        }
        return "User not verified";
    }

    /*
    * resend verification token
    * */
    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, final HttpServletRequest request) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(verificationToken, applicationUrl(request));
        return "Verification Link Sent";
    }

    /*
    * reset password
    * */
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, final HttpServletRequest request){
        User user = userService.getUserByEmail(passwordModel.getEmail());
        String url = "";
        if(user != null){
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetToken(user, token);
            url = passwordResetTokenMail(token, applicationUrl(request));
        }
        return url;
    }

    /*
    * save password
    * */
    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel) {
        String result = userService.validatePasswordResetToken(token);
        if(!result.equalsIgnoreCase("valid")){
            return "Invalid token";
        }
        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if(user.isPresent()){
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            return "Password reset successful";
        } else{
            return "Invalid token";
        }
    }

    /*
    * change password
    * */
    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = userService.getUserByEmail(passwordModel.getEmail());
        if(!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())){
            return "Invalid Old Password";
        }
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password Changed Successfully";
    }

    /*
    * password reset token mail
    * */
    private String passwordResetTokenMail(String token, String applicationUrl) {
        String url = applicationUrl + "/savePassword?token=" + token;

        log.info("Click the link to reset your password: {}", url);
        return url;
    }

    private void resendVerificationTokenMail(VerificationToken verificationToken, String applicationUrl) {
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();

        log.info("Click the link to verify your account: {}", url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
