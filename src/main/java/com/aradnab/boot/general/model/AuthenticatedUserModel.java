package com.aradnab.boot.general.model;

import com.aradnab.boot.db_tier.entity.AuthenticatedUser;
import com.aradnab.boot.db_tier.entity.LoginCredentialFb;
import com.aradnab.boot.db_tier.entity.LoginCredentialGoogle;
import com.aradnab.boot.db_tier.entity.LoginCredentialNormal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUserModel {
    int id;
    int userId;
    String sessionId;
    Date logInAt;
    Date logOutAt;
    String userName;
    String password;
    String facebookId;
    String googleId;
    byte status;

    public static AuthenticatedUserModel entityToModel(AuthenticatedUser user, LoginCredentialNormal normal, LoginCredentialFb fb, LoginCredentialGoogle google) {
        AuthenticatedUserModel model = new AuthenticatedUserModel();
        model.setId(user.getId());
        model.setUserId(user.getUserId());
        model.setSessionId(user.getSessionId());
        model.setLogInAt(user.getLoginAt());
        model.setLogOutAt(user.getLogoutAt());
        if (normal != null) {
            model.setUserName(normal.getUsername());
            model.setPassword(normal.getPassword());
        }
        if (fb != null) {
            model.setFacebookId(fb.getFbId());
        }
        if (google != null) {
            model.setGoogleId(google.getgId());
        }
        return model;
    }

}