package com.udea.autoevaluacion.constants;

public class EndpointsConstants {
    // URL Base de la API
    public static final String BASE_URL = "/api";

    // URLs de usuario
    public static final String AUTH_URL = BASE_URL + "/auth";
    public static final String LOGIN_URL = AUTH_URL + "/login";
    public static final String REGISTER_URL = AUTH_URL + "/register";
    public static final String USER_URL = BASE_URL + "/user/{id}";
    public static final String LOGOUT_URL = AUTH_URL + "/logout";
    public static final String PROFILE_URL = BASE_URL + "/profile";

    // URLs de contraseña y verificacion
    public static final String FORGOT_PASSWORD_URL = AUTH_URL + "/forgot-password";
    public static final String RESET_PASSWORD_URL = AUTH_URL + "/reset-password";
    public static final String VERIFY_EMAIL_URL = AUTH_URL + "/verify-email";
    public static final String CHANGE_PASSWORD_URL = BASE_URL + "/user/{id}/change-password";

    // CrossOrigin de dev local y producción
    public static final String LOCAL_FRONTEND_ORIGIN = "http://localhost:3000"; 
    public static final String PROD_FRONTEND_ORIGIN = "https://your-production-domain.com"; // Cambiar a la URL cuando se despliegue en producción
}
