/**
 * File: ProductBean.java
 * Course: CST8277
 * @author: Byeongyun Goo (#040888224), Zeyang Hu (#040885680), Sohaila Binte Ridwan (#040847430)
 * @date: April 13th, 2019
 */
package com.algonquincollege.cst8277.rest;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.management.Descriptor;
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

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.ProductBean;
import com.algonquincollege.cst8277.models.Member;
import com.algonquincollege.cst8277.models.Product;

import static com.algonquincollege.cst8277.rest.EmployeeConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.rest.ProductConstants.*;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

@Path(PRODUCT_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    
    /**
     * declare ProductBean
     */
    @EJB
    protected ProductBean productBean;
    
    /**
     * declare SecurityContent sc
     */
    @Inject
    protected SecurityContext sc;
    
    /**
     * Getting Product list
     * @return Response
     */
    @GET
    @Operation(description = GET_PRODUCT_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description =GET_PRODUCT_OP_200_DESC),
        @APIResponse(responseCode = "403", description =GET_PRODUCT_OP_403_DESC),
        @APIResponse(responseCode = "404", description =GET_PRODUCT_OP_404_DESC),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getProducts() {
        Response response = null;
        List<Product> products = productBean.getProductList();
        response = Response.ok(products).build();
        return response;
    }


   /**
    * Getting product by product id
    * @param id the id related to Product id
    * @return Response
    */
    @GET
    @Operation(description = GET_PRODUCT_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description =GET_PRODUCT_OP_200_DESC),
        @APIResponse(responseCode = "403", description =GET_PRODUCT_OP_403_DESC),
        @APIResponse(responseCode = "404", description =GET_PRODUCT_OP_404_DESC),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Path("/{id}")
    public Response getProductById(@Parameter(description = PRIMARY_KEY_DESC, required = true)
                                    @PathParam("id") int id) {
        Response response = null;
        Product product = productBean.getExistedProductById(id);
        
        
        
        if(product== null) {
            response = Response.status(NOT_FOUND).build();
        }else {
            response = Response.ok(product).build();
        }
        return response;
    }    
    
    /**
     * Update product information
     * @param product
     * @return Response
     */
    @PUT
    @Operation(description = "update a product")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "updated product successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Path("/{id}")
    public Response updateProduct(@Parameter(description = PRIMARY_KEY_DESC, required = true)
     Product product) {
       
        productBean.updateExistedProduct(product);

        return Response.ok().build();        
    }
    
    /**
     * Delete a specific product by id
     * @param id the id of the product user wishes to delete
     * @return Response 
     */
    @DELETE
    @Operation(description = "Delete a product")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Delete product successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Path("/{id}")
    public Response deleteProduct(@Parameter(description = PRIMARY_KEY_DESC, required = true)
                                 @PathParam("id") int id) {
        productBean.deletedExistedProductById(id);
        
        return Response.ok().build();
    }
    
    
    /**
     * Create a new product 
     * @param rawProduct
     * @return Response
     */
    @POST
    @Operation(description = "Create a product")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Create product successfully"),
        @APIResponse(responseCode = "404", description = "can't find the object"),
    })
    @RolesAllowed(ADMIN_ROLENAME)
    @Path("/rawProduct")
    public Response createProduct(@Parameter(description = PRIMARY_KEY_DESC, required = true)
                                Product rawProduct) {
        productBean.createNewProduct(rawProduct);

        return Response.ok().build();
    }
    
    
    
}




