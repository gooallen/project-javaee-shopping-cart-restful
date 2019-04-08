package com.algonquincollege.cst8277.rest;


// Done below
import static com.algonquincollege.cst8277.rest.LineItemConstants.LINEITEM_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.LineItemConstants.LINEITEM_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.LineItemConstants.LINEITEM_RESOURCE_PATH_ID_PATH;


// Will be updated later
import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEES_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEES_OP_403_DESC;
//import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEES_OP_403_DESC_JSON_MSG;
//import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEES_OP_403_JSON_MSG;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEES_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEES_OP_DESC;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEE_BY_ID_OP_200_DESC;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEE_BY_ID_OP_403_DESC;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEE_BY_ID_OP_404_DESC;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.GET_EMPLOYEE_BY_ID_OP_DESC;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.PRIMARY_KEY_DESC;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.ADMIN_ROLENAME;
import static com.algonquincollege.cst8277.utils.RestDemoConstants.USER_ROLENAME;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.SimpleBean;
import com.algonquincollege.cst8277.models.Employee;
import com.algonquincollege.cst8277.models.LineItem;

@Path(LINEITEM_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LineItemResource {

    @EJB
    protected SimpleBean simpleBean;

    @Inject
    protected SecurityContext sc;

    @GET
    @Operation(description = GET_EMPLOYEES_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_EMPLOYEES_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_EMPLOYEES_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_EMPLOYEES_OP_404_DESC)
    })
    @RolesAllowed(ADMIN_ROLENAME)
    public Response getItemLine() {
        Response response = null;

        /*
        if (!sc.isCallerInRole(ADMIN_ROLENAME)) {
            response = Response.status(Status.FORBIDDEN).entity(GET_EMPLOYEES_OP_403_JSON_MSG).build();
        }
        else {
        */
            List<LineItem> emps = simpleBean.getLineItemList();
            response = Response.ok(emps).build();
        /*
        }
        */
        return response;
    }

    
    
    
    
    
    
    
    @GET
    @Operation(description = GET_EMPLOYEE_BY_ID_OP_DESC)
    @APIResponses({
        @APIResponse(responseCode = "200", description = GET_EMPLOYEE_BY_ID_OP_200_DESC),
        @APIResponse(responseCode = "403", description = GET_EMPLOYEE_BY_ID_OP_403_DESC),
        @APIResponse(responseCode = "404", description = GET_EMPLOYEE_BY_ID_OP_404_DESC)
    })
    @RolesAllowed(USER_ROLENAME)
    @Path(LINEITEM_RESOURCE_PATH_ID_PATH)
    public Response getEmployeeById(@Parameter(description = PRIMARY_KEY_DESC, required = true)
        @PathParam(LINEITEM_RESOURCE_PATH_ID_ELEMENT) int id) {
        Response response = null;

        /*
        if (!sc.isCallerInRole(USER_ROLENAME)) {
            //TODO - check if specific user is allowed to retrieve the specific employee
            response = Response.status(Status.FORBIDDEN).entity(GET_EMPLOYEES_OP_403_DESC_JSON_MSG).build();
        }
        else {
        */
            Employee emp = simpleBean.getEmployeeById(id);
            if (emp == null) {
                response = Response.status(NOT_FOUND).build();
            }
            else {
                response = Response.ok(emp).build();
            }
        /*
        }
        */

        return response;
    }

}