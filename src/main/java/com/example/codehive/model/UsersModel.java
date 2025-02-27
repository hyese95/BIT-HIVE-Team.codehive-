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
        KOR, USA, EN, JPA, CHA
    }
    public enum gender {
        M, F
    }
    public enum theme {
        Dark, Light
    }
    public enum role {
        admin, user
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