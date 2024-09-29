package com.reviewapp.security;

import cds.gen.srv.review.Users;
import org.springframework.stereotype.Component;

@Component
public class ReviewUserInfo {
    public static final String ADMINID = "243a08f9-5fb9-46fd-806a-2349b393c771";
    public Users getUser(){
        Users user = Users.create();
        user.setRole("admin");
        user.setId(ADMINID);
        user.setUsername("Mary Voytovych");
        return user;
    }
}
