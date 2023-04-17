package com.springsecuritydcb.springsecurityclient.service;

import com.springsecuritydcb.springsecurityclient.entity.User;
import com.springsecuritydcb.springsecurityclient.entity.VerificationToken;
import com.springsecuritydcb.springsecurityclient.model.UserModel;

import java.util.Optional;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User getUserByEmail(String email);

    void createPasswordResetToken(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);
}
