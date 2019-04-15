/**
 * File: CartResource.java
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.CartBean;
import com.algonquincollege.cst8277.ejb.MemberBean;
import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.Member;
import com.algonquincollege.cst8277.models.PlatformUser;

import static com.algonquincollege.cst8277.rest.CartConstants.*;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.rest.MemberConstants.GET_MEMBER_OP_403_DESC_JSON_MSG;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.security.Principal;
import java.util.List;

@Path(CART_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartResource {
    
   /**
    * Declare CartBean cartBean
    */
    @EJB
    CartBean cartBean;
    
    /**
     * Declare MemberBean memberBean
     */
    @EJB
    MemberBean memberBean;
    
    /**
     * declare SecurityContent sc
     */
    @Inject
    protected SecurityContext sc;
    
    /**
     * Getting all carts
     * @return Response
     */
    @GET
    @Operation(description = GET_CARTS_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description =GET_CARTS_OP_200_DESC),
        @APIResponse(responseCode = "403", description =GET_CARTS_OP_403_DESC),
        @APIResponse(responseCode = "404", description =GET_CARTS_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getCarts() {
        Response response = null;
        
        List<Cart> carts = cartBean.getAllCarts();
        response = Response.ok(carts).build();
        
        return response;
    }
    
    /**
     * Getting a specific cart
     * @param id
     * @return Response
     */
    @GET
    @Operation(description = GET_CART_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description =GET_CARTS_OP_200_DESC),
        @APIResponse(responseCode = "403", description =GET_CARTS_OP_403_DESC),
        @APIResponse(responseCode = "404", description =GET_CARTS_OP_404_DESC)
    })
    @RolesAllowed(USER_ROLENAME)
    @Path("/{id}")
    public Response getCartById(@Parameter(description = PRIMARY_KEY_DESC, required = true)
    @PathParam(CART_RESOURCE_PATH_ID_ELEMENT) int id) {
        
        Response response = null;
        
        Principal principal = sc.getCallerPrincipal();
        if (principal == null) {
            response = Response.serverError().entity("{\"message\":\"missing principal\"}").build();
        }else {
            PlatformUser platformUser = (PlatformUser)principal;

            if (platformUser.getMember().getCart() == null || platformUser.getMember().getCart().getId() != id) {
                response = Response.status(Status.UNAUTHORIZED).entity(GET_MEMBER_OP_403_DESC_JSON_MSG).build();
            }else {
                
                //Cart cart = cartBean.getCartContent(id);
                
                response = Response.ok(platformUser.getMember().getCart()).build();
            }
        }
     
        return response;
    }
    
    /**
     * update a cart total amount
     * @param cart
     * @return Response
     */
    @PUT
    @Operation(description = "update a cart total amount")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "updated Cart total amount successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Path("/{id}")
    public Response updateCartTotalAmount(@Parameter(description = PRIMARY_KEY_DESC, required = true)
                                          Cart cart) {
        cartBean.updateExistedCart(cart);
        
        return Response.ok().build();  
    }
    
    
    /**
     * Delete a cart
     * @param id
     * @return response
     */
    @DELETE
    @Operation(description = "Delete a cart")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Delete member successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Path("/{id}")
    public Response deleteCart(@Parameter(description = PRIMARY_KEY_DESC, required = true)
    @PathParam("id") int id) {
        cartBean.deleteCart(id);
        
        return Response.ok().build();
    }

    /**
     * Create a new Cart
     * @param member_id
     * @return Response
     */
    @POST
    @Operation (description = "Create a cart")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Delete member successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response createCart(@QueryParam("member-id") int member_id) {
        
        
        Member member = memberBean.getExistedMemberById(member_id);
        
        cartBean.createCart(member);
        
        return Response.ok().build();
    }
}
