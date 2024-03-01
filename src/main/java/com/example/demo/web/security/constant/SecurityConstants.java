package com.example.demo.web.security.constant;

public final class SecurityConstants {

    public static final String AUTHENTICATION_URL = "/api/v1/login";

    public static final String REGISTRATION_URL = "/api/v1/signup";

    public static final String API_SECURE_URL = "/api/v1/secure/**";

    public static final Integer EXPIRATION_TOKEN_DATE_IN_MIN = 240;

    public static final String ISSUER = "Nicolo";

    public static final String SECRET = "TEST";
    public static final String ROLE_CLAIM = "role";
    public static final String SCOPES_CLAIM = "scopes";
    public static final String AUTHENTICATION_HEADER_NAME = "Authorization";

    public static final String HEADER_PREFIX = "Bearer ";

}
