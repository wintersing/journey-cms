package com.lt.journey_cms.model;

import lombok.Data;

/**
 * 用户信息
 */

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String code;
}