/**
 * File: MemberResource.java
 * Course: CST8277
 * @author: Byeongyun Goo (#040888224), Zeyang Hu (#040885680), Sohaila Binte Ridwan (#040847430)
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.MemberBean;
import com.algonquincollege.cst8277.models.Member;
import com.algonquincollege.cst8277.models.PlatformUser;

import static com.algonquincollege.cst8277.rest.EmployeeConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.rest.MemberConstants.*;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.*;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.security.Principal;
import java.util.List;

@Path(MEMBER_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MemberResource {

    /**
     * declare MemberBean memberBean
     */
    @EJB
    MemberBean memberBean;
    
    /**
     * declare SecurityContext sc
     */
    @Inject
    protected SecurityContext sc;
    
    /**
     * Get list of Members
     * @return Response
     */
    @GET
    @Operation(description = GET_MEMBERS_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_MEMBERS_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_MEMBERS_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_MEMBERS_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response GetMembers() {
        Response response = null;
        
        List<Member> members = memberBean.getMemberList();
        response = Response.ok(members).build();
        return response;
        
    }
    
    /**
     * Get member by Id
     * @param id
     * @return Response 
     */
    @GET
    @Operation(description = GET_MEMBER_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_MEMBER_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_MEMBER_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_MEMBER_BY_ID_OP_404_DESC),
    })
    @RolesAllowed(USER_ROLENAME)
    @Path(MEMBER_RESOURCE_PATH_ID_PATH)
    public Response getMemberById(@Parameter(description = PRIMARY_KEY_DESC, required = true)
            @PathParam(MEMBER_RESOURCE_PATH_ID_ELEMENT) int id) {
        Response response = null; 
        
        Principal principal = sc.getCallerPrincipal();
        if (principal == null) {

            //because of @RolesAllowed(USER_ROLENAME) this should never happen!

            response = Response.serverError().entity("{\"message\":\"missing principal\"}").build();

        }else {
            PlatformUser platformUser = (PlatformUser)principal;

            if (platformUser.getMember() == null || platformUser.getMember().getId() != id) {
                response = Response.status(Status.UNAUTHORIZED).entity(GET_MEMBER_OP_403_DESC_JSON_MSG).build();
            }else {
                response = Response.ok(platformUser.getMember()).build();
            }
        }
        
        return response;     
    }
    

    /**
     * update a member
     * @param member
     * @return Response
     */
    @PUT
    @Operation(description = "update a member")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "updated member successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Path("/{id}")
    public Response updateMember(@Parameter(description = PRIMARY_KEY_DESC, required = true)
     Member member) {
       
        memberBean.updateExistedMember(member);

        return Response.ok().build();        
    }
    
    /**
     * Delete a member
     * @param id
     * @return Response
     */
    @DELETE
    @Operation(description = "Delete a member")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Delete member successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Path("/{id}")
    public Response deleteMember(@Parameter(description = PRIMARY_KEY_DESC, required = true)
                                 @PathParam("id") int id) {
        memberBean.deletedExistedMemberById(id);
        
        return Response.ok().build();
    }
    
    /**
     * Create a new member
     * @param rawMember
     * @return Response
     */
    @POST
    @Operation(description = "Create a member")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Create member successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Path("/rawMember")
    public Response createMember(@Parameter(description = PRIMARY_KEY_DESC, required = true)
                                Member rawMember) {
        memberBean.createNewMember(rawMember);

        return Response.ok().build();
    }
}
