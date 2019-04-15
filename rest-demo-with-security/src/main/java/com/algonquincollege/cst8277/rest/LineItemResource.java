/**
 * File: LineItemResource.java
 * Course: CST8277
 * @author: Byeongyun Goo (#040888224), Zeyang Hu (#040885680), Sohaila Binte Ridwan (#040847430)
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.rest;

import static com.algonquincollege.cst8277.rest.CartConstants.CART_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.CartConstants.GET_CART_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.rest.LineItemConstants.*;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;

import java.util.List;

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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.LineItemBean;
import com.algonquincollege.cst8277.models.Cart;
import com.algonquincollege.cst8277.models.LineItem;

@Path(LINEITEM_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LineItemResource {
    
    /**
     * connect to lineItemBean
     */
    @EJB
    LineItemBean lineItemBean;
    
    /**
     * dependency injection with SecurityContext
     */
    @Inject
    protected SecurityContext sc;
    
    /**
     * get lineitems by cart id by GET
     * @param cartId
     * @return code 200
     */
    @GET
    @Operation(description = GET_LINEITEMS_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description =GET_LINEITEMS_OP_200_DESC),
        @APIResponse(responseCode = "403", description =GET_LINEITEMS_OP_403_DESC),
        @APIResponse(responseCode = "404", description =GET_LINEITEMS_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getLineItems(
            @QueryParam("cartId") int cartId) {
        Response response = null;
        
        List<LineItem> lineItems = lineItemBean.getLineItemList(cartId);
        response = Response.ok(lineItems).build();
        return response;
    }
    

    /**
     * create new lineitem by POST
     * @param cartId passing id
     * @param productId passing product id
     * @param quantity passing quantitiy
     * @return code 200
     */
    @POST
    @Operation (description = "Create a lineitem")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Delete member successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response createNewLineItem(
            @QueryParam("cartId") int cartId,
            @QueryParam("productId") int productId,
            @QueryParam("quantity") int quantity) {
        
        lineItemBean.createNewLineItem(cartId, productId, quantity);
        
        return Response.ok().build();
    }
    
    /**
     * delete lineitem by lineitem id by DELETE
     * @param lineItemId passing line item id
     * @return code 200
     */
    @DELETE
    @Operation(description = "Delete a member")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Delete member successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response deleteLineItem(
            @QueryParam("lineItemId") int lineItemId) {
        
        lineItemBean.deleteLineItem(lineItemId);
        
        return Response.ok().build();
    }
}
