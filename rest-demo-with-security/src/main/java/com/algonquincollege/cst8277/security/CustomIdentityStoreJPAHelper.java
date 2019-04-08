package com.algonquincollege.cst8277.security;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.FIND_PLATFORM_USER_BY_NAME_QUERYNAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.NAME_PARAM;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;
import static java.util.Collections.emptySet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.algonquincollege.cst8277.models.PlatformRole;
import com.algonquincollege.cst8277.models.PlatformUser;

@ApplicationScoped
@Default
public class CustomIdentityStoreJPAHelper {
	
	
    private static final String PROPERTY_ALGORITHM  = "Pbkdf2PasswordHash.Algorithm";
    private static final String DEFAULT_PROPERTY_ALGORITHM  = "PBKDF2WithHmacSHA256";
    private static final String PROPERTY_ITERATIONS = "Pbkdf2PasswordHash.Iterations";
    private static final String DEFAULT_PROPERTY_ITERATIONS = "2048";
    private static final String PROPERTY_SALTSIZE   = "Pbkdf2PasswordHash.SaltSizeBytes";
    private static final String DEFAULT_SALT_SIZE   = "32";
    private static final String PROPERTY_KEYSIZE    = "Pbkdf2PasswordHash.KeySizeBytes";
    private static final String DEFAULT_KEY_SIZE    = "32";
	
	
	
	
	

    @PersistenceContext(name = PU_NAME)
    protected EntityManager em;

    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;

    
    
    
    
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
    
    
    
    
    
      
    // By Name!
    public PlatformUser findUserByName(String username) {
        PlatformUser platformUser = null;
        try {
            TypedQuery<PlatformUser> q = em.createNamedQuery(FIND_PLATFORM_USER_BY_NAME_QUERYNAME, PlatformUser.class);
            q.setParameter(NAME_PARAM, username);
            platformUser = q.getSingleResult();
        }
        catch (Exception e) {
            // e.printStackTrace();
        }
        return platformUser;
    }

    // By Role for user
    public Set<PlatformRole> findRolesForUser(String username) {
        Set<PlatformRole> foundRoles = emptySet();
        PlatformUser platformUser = findUserByName(username);
        if (platformUser != null) {
        	
        	// get the role of the platformUser into foundRoles
            foundRoles = platformUser.getPlatformRoles();
        }
        return foundRoles;
    }

    // Save user in the persistence
    public void savePlatformUser(PlatformUser platformUser) {
        em.persist(platformUser);

    }
}
