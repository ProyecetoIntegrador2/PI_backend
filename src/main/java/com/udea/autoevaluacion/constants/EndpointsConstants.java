package com.udea.autoevaluacion.constants;

public class EndpointsConstants {
    // URLs de usuario
    public static final String LOGIN_URL = "/login";
    public static final String REGISTER_URL = "/register";
    public static final String USER_URL = "/user/{id}";
    public static final String LOGOUT_URL = "/logout";
    public static final String PROFILE_URL = "/profile";

    // URLs de contraseña y verificacion
    public static final String FORGOT_PASSWORD_URL = "/forgot-password";
    public static final String RESET_PASSWORD_URL = "/reset-password";
    public static final String VERIFY_EMAIL_URL = "/verify-email";
    public static final String CHANGE_PASSWORD_URL = "/user/{id}/change-password";
}
