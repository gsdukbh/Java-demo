package top.werls.springsecurity.jwtdemo.config;

/**
 * @author leejiawei
 */
public class JwtTokenConfig {
    public static final String AUTH_LOGIN_URL = "/public/login";

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPcdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer+";
    public static final String TOKEN_TYPE = "Jwt";
    public static final String TOKEN_ISSUER = "as";
    public static final String TOKEN_AUDIENCE = "ca";
}
