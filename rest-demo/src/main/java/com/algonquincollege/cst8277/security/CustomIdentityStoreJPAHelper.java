package com.algonquincollege.cst8277.security;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

//import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformUser;

@Singleton
public class CustomIdentityStoreJPAHelper {

    private static final String PROPERTY_ALGORITHM  = "Pbkdf2PasswordHash.Algorithm";
    private static final String DEFAULT_PROPERTY_ALGORITHM  = "PBKDF2WithHmacSHA256";
    private static final String PROPERTY_ITERATIONS = "Pbkdf2PasswordHash.Iterations";
    private static final String DEFAULT_PROPERTY_ITERATIONS = "2048";
    private static final String PROPERTY_SALTSIZE   = "Pbkdf2PasswordHash.SaltSizeBytes";
    private static final String DEFAULT_SALT_SIZE   = "32";
    private static final String PROPERTY_KEYSIZE    = "Pbkdf2PasswordHash.KeySizeBytes";
    private static final String DEFAULT_KEY_SIZE    = "32";

    @PersistenceContext(name="rest-demo")
    protected EntityManager em;

    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;

    //TODO - actually go to database

    public PlatformUser findUserByPassword(String username, String password) {
        Map<String, Object> emProps = em.getProperties();

        PlatformUser platformUser = new PlatformUser();
        platformUser.setUsername("mike");
        // the nickname of this Hash algorithm is 'PBandJ' (Peanut-Butter-And-Jam, like the sandwich)
        Map<String, String> pbAndjProperties = new HashMap<>();
        pbAndjProperties.put(PROPERTY_ALGORITHM, DEFAULT_PROPERTY_ALGORITHM);
        pbAndjProperties.put(PROPERTY_ITERATIONS, DEFAULT_PROPERTY_ITERATIONS);
        pbAndjProperties.put(PROPERTY_SALTSIZE, DEFAULT_SALT_SIZE);
        pbAndjProperties.put(PROPERTY_KEYSIZE, DEFAULT_KEY_SIZE);
        pbAndjPasswordHash.initialize(pbAndjProperties);
        String pwHash = pbAndjPasswordHash.generate("Password!".toCharArray());
        platformUser.setPwHash(pwHash);

        // keep local variable emProps 'live' in debugger
        System.identityHashCode(emProps);
        return platformUser;
    }

    public PlatformUser findUserByName(String username) {
        PlatformUser platformUser = new PlatformUser();
        platformUser.setUsername("mike");
        return platformUser;
    }

    public Set<PlatformRole> findRolesForUser(String username) {
        Set<PlatformRole> platformRoles = new HashSet<>();
        PlatformRole adminRole = new PlatformRole();
        adminRole.setRoleName("admin");
        PlatformRole userRole = new PlatformRole();
        userRole.setRoleName("user");
        platformRoles.add(userRole);
        return platformRoles;
    }
}
