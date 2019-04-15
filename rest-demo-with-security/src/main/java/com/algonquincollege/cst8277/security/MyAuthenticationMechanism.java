package com.algonquincollege.cst8277.security;

import static javax.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;
import static javax.servlet.http.HttpServletRequest.BASIC_AUTH;

import java.security.Principal;
import java.util.Base64;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

import org.glassfish.soteria.WrappingCallerPrincipal;

@ApplicationScoped
public class MyAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStore identityStore;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {

        AuthenticationStatus result = httpMessageContext.doNothing();
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {
            result = httpMessageContext.responseUnauthorized();
        }
        else if (authHeader != null) {
            String name = null;
            String password = null;
            //parse BasicAuth header
            boolean startsWithBasic = authHeader.toLowerCase().startsWith(BASIC_AUTH.toLowerCase());
            if (startsWithBasic) {
                String b64Token = authHeader.substring(BASIC_AUTH.length() + 1, authHeader.length());
                //                                                 ^^^^^^^^^^^ account for space between BASIC and base64-string
                byte[] token = Base64.getDecoder().decode(b64Token);
                String tmp = new String(token);
                String[] tokenFields = tmp.split(":");
                if (tokenFields.length == 2) {
                    name = tokenFields[0];
                    password = tokenFields[1];
                }
            }
            if (name != null && password != null) {
                CredentialValidationResult validationResult = identityStore.validate(new UsernamePasswordCredential(name, password));
                if (validationResult.getStatus() == VALID) {
                    Principal principal = validationResult.getCallerPrincipal();
                    
                    if (principal instanceof WrappingCallerPrincipal) {

                        principal = ((WrappingCallerPrincipal)principal).getWrapped();

                    }
                    result = httpMessageContext.notifyContainerAboutLogin(principal,validationResult.getCallerGroups());
                    //result = httpMessageContext.notifyContainerAboutLogin(validationResult);
                }
                else {
                    result = httpMessageContext.responseUnauthorized();
                }
            }
        }
        return result;
    }
}