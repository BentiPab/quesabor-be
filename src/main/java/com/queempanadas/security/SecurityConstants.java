package com.queempanadas.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

    @Value("${security.jwt.expiration-time}")
    public static long JWT_EXPIRATION;
    @Value("${security.jwt.secret-key}")
    public static String JWT_SECRET;
}