package com.algonquincollege.cst8277.utils;

public interface RestDemoConstants {

    // the nickname of this Hash algorithm is 'PBandJ' (Peanut-Butter-And-Jam, like the sandwich!)
    // I would like to use the constants from org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl
    // but they are not visible, so type in them all over again :-( Hope there are no typos!
    public static final String PROPERTY_ALGORITHM  = "Pbkdf2PasswordHash.Algorithm";
    public static final String DEFAULT_PROPERTY_ALGORITHM  = "PBKDF2WithHmacSHA256";
    public static final String PROPERTY_ITERATIONS = "Pbkdf2PasswordHash.Iterations";
    public static final String DEFAULT_PROPERTY_ITERATIONS = "2048";
    public static final String PROPERTY_SALTSIZE   = "Pbkdf2PasswordHash.SaltSizeBytes";
    public static final String DEFAULT_SALT_SIZE   = "32";
    public static final String PROPERTY_KEYSIZE    = "Pbkdf2PasswordHash.KeySizeBytes";
    public static final String DEFAULT_KEY_SIZE    = "32";

    //JPA constants
    public static final String PU_NAME = "rest-demo-with-security";
    public static final String PLATFORM_USER_TABLE_NAME = "PLATFORM_USER";
    public static final String PLATFORM_USER_JOIN_TABLE_NAME = "PLATFORM_USER_ROLE";
    public static final String ID_COLUMN_NAME = "ID";
    public static final String PLATFORM_USER_JOIN_COLUMN_NAME = "USER_ID";
    public static final String PLATFORM_USER_INVERSEJOIN_COLUMN_NAME = "ROLE_ID";
    public static final String FIND_PLATFORM_USER_BY_NAME_QUERYNAME = "findPlatformUserByName";
    public static final String NAME_PARAM = "nameParam";
    public static final String FIND_PLATFORM_USER_BY_NAME_JPQL =
        "SELECT u FROM PlatformUser u WHERE u.username = :" + NAME_PARAM;

    // REST and Security constants
    public static final String DEFAULT_ADMIN_USER_PROPNAME = "default-admin-user";
    public static final String DEFAULT_ADMIN_USER = "admin";
    public static final String SERVER_API_DESC = "API resource for Employee (slightly modified from Assignment3) with Java EE 8 Security";
    public static final String SERVER_URL = "/demo-security";
    public static final String ADMIN_ROLENAME = "admin";
    public static final String USER_ROLENAME = "user";
    public static final String DEFAULT_ADMIN_USER_PASSWORD_PROPNAME = "default-admin-user-password";

}