package com.aradnab.boot.general.controller;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.*;
import com.aradnab.boot.general.model.AuthenticatedUserModel;
import com.aradnab.boot.general.model.UserModel;
import com.aradnab.boot.general.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserController userController;
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
    @Autowired
    AuthenticatedUserService authenticatedUserService;
    @Autowired
    LoginCredentialNormalService loginCredentialNormalService;
    @Autowired
    LoginCredentialFbService loginCredentialFbService;
    @Autowired
    LoginCredentialGoogleService loginCredentialGoogleService;

    @PostMapping("/signUp")
    public ResponseEntity<UserModel> signUp(@RequestBody AuthenticatedUserModel authenticatedUserModel) {
        Date d = new Date();
        AuthenticatedUser au = new AuthenticatedUser();
        au.setUserId(authenticatedUserModel.getUserId());
        au.setSessionId(createSessionId(authenticatedUserModel.getUserId()));
        au.setLoginAt(d);
        au.setSavedAt(d);
        au.setLastUpdatedAt(d);
        au.setStatus(Status.LIVE_ACTIVE_STATUS);
        authenticatedUserService.create(au);
        if (authenticatedUserModel.getFacebookId() != null) {
            LoginCredentialFb f = new LoginCredentialFb();
            f.setUserId(authenticatedUserModel.getUserId());
            f.setFbId(authenticatedUserModel.getFacebookId());
            f.setSavedAt(d);
            f.setLastUpdatedAt(d);
            f.setStatus(Status.LIVE_ACTIVE_STATUS);
            loginCredentialFbService.create(f);
        }
        if (authenticatedUserModel.getGoogleId() != null) {
            LoginCredentialGoogle g = new LoginCredentialGoogle();
            g.setUserId(authenticatedUserModel.getUserId());
            g.setgId(authenticatedUserModel.getGoogleId());
            g.setSavedAt(d);
            g.setLastUpdatedAt(d);
            g.setStatus(Status.LIVE_ACTIVE_STATUS);
            loginCredentialGoogleService.create(g);
        }
        if (authenticatedUserModel.getUserName() != null) {
            LoginCredentialNormal n = new LoginCredentialNormal();
            n.setUserId(authenticatedUserModel.getUserId());
            n.setUsername(authenticatedUserModel.getUserName());
            n.setPassword(authenticatedUserModel.getPassword());
            n.setSavedAt(d);
            n.setLastUpdatedAt(d);
            n.setStatus(Status.LIVE_ACTIVE_STATUS);
            loginCredentialNormalService.create(n);
        }
        return userController.get(authenticatedUserModel.getUserId());
    }

    private String createSessionId(int userId) {
        return "ss_" + userId;
    }

    @PostMapping("/signIn/fb")
    public ResponseEntity<UserModel> signInWithFb(@RequestBody  AuthenticatedUserModel response) {
        String fbId = response.getFacebookId();
        User auth = loginCredentialFbService.getAuth(fbId);
        UserModel userModel = UserModel.entityToModel(auth);
        return ResponseEntity.ok().body(userModel);
    }

    @PostMapping("/signIn/google")
    public ResponseEntity<UserModel> signInWithGoogle(@RequestBody AuthenticatedUserModel response) {
        return ResponseEntity.ok().body(UserModel.entityToModel(loginCredentialGoogleService.getAuth(response.getGoogleId())));
    }

    @PostMapping("/signIn/normal")
    public ResponseEntity<UserModel> signIn(@RequestBody  AuthenticatedUserModel response) {
        return ResponseEntity.ok().body(UserModel.entityToModel(loginCredentialNormalService.getAuth(response.getUserName(), response.getPassword())));
    }

    @PostMapping("/signUp/userNameAlreadyExist")
    public ResponseEntity<Integer> isUserNameAlreadyExists(@RequestBody Map<String, String> response) {
        int status = 0;
        if (userService.isUserNameAlreadyExists(response.get("username"))) {
            status = 1;
        }
        return ResponseEntity.ok().body(status);
    }

    @PostMapping("/signUp/userEmailAlreadyExist")
    public ResponseEntity<Integer> isEmailAlreadyExists(@RequestBody Map<String, String> response) {
        int status = 0;
        if (emailService.isEmailAlreadyExists(response.get("email"))) {
            status = 1;
        }
        return ResponseEntity.ok().body(status);
    }

}
