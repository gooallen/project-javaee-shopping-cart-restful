package com.algonquincollege.cst8277.security;

import static com.algonquincollege.cst8277.utils.RestDemoConstants.FIND_PLATFORM_USER_BY_NAME_QUERYNAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.NAME_PARAM;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.PU_NAME;
import static java.util.Collections.emptySet;

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

    @PersistenceContext(name = PU_NAME)
    protected EntityManager em;

    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;

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

    public Set<PlatformRole> findRolesForUser(String username) {
        Set<PlatformRole> foundRoles = emptySet();
        PlatformUser platformUser = findUserByName(username);
        if (platformUser != null) {
            foundRoles = platformUser.getPlatformRoles();
        }
        return foundRoles;
    }

    public void savePlatformUser(PlatformUser platformUser) {
        em.persist(platformUser);

    }
}
