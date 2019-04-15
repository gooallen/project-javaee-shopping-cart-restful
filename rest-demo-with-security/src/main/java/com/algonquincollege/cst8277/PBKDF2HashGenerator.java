package com.algonquincollege.cst8277;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_KEY_SIZE;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_PROPERTY_ALGORITHM;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_PROPERTY_ITERATIONS;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.DEFAULT_SALT_SIZE;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_ALGORITHM;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_ITERATIONS;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_KEYSIZE;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PROPERTY_SALTSIZE;

import java.util.HashMap;
import java.util.Map;

import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

public class PBKDF2HashGenerator {

    public static void main(String[] args) {

        Pbkdf2PasswordHash pbAndjPasswordHash = new Pbkdf2PasswordHashImpl();

        Map<String, String> pbAndjProperties = new HashMap<>();
        pbAndjProperties.put(PROPERTY_ALGORITHM, DEFAULT_PROPERTY_ALGORITHM);
        pbAndjProperties.put(PROPERTY_ITERATIONS, DEFAULT_PROPERTY_ITERATIONS);
        pbAndjProperties.put(PROPERTY_SALTSIZE, DEFAULT_SALT_SIZE);
        pbAndjProperties.put(PROPERTY_KEYSIZE, DEFAULT_KEY_SIZE);
        pbAndjPasswordHash.initialize(pbAndjProperties);
        String pwHash = pbAndjPasswordHash.generate(args[0].toCharArray());
        System.out.printf("Hash for %s is %s%n", args[0], pwHash);
    }
}
