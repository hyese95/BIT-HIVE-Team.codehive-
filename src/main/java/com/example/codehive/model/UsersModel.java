package com.example.codehive.model;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * users
 */
@Data
public class UsersModel implements Serializable {
    public enum nationality {
        KOREAN, JAPAN
    }
    public enum gender {
        MALE, FEMALE
    }
    public enum theme {
       LIGHT, DARK
    }
    public enum role {
        USER, MANGER
    }
    private String userNo;

    private String userId;

    private String password;

    private String nickname;

    private String email;

    private String phone;

    private Boolean privacyAgreements;

    private Boolean marketingAgreements;

    private Timestamp createdAt;

    private String profileImgUrl;

    private nationality nationality;

    private gender gender;

    private theme theme;

    private Timestamp birthDate;

    private String name;

    private String selfIntroduction;

    private role role;

    private static final long serialVersionUID = 1L;
}