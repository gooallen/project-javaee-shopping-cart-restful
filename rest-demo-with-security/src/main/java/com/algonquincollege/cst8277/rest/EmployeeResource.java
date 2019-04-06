package com.algonquincollege.cst8277.rest;


import static com.algonquincollege.cst8277.rest.EmployeeConstants.EMPLOYEE_RESOURCE_NAME;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.EMPLOYEE_RESOURCE_PATH_ID_ELEMENT;
import static com.algonquincollege.cst8277.rest.EmployeeConstants.EMPLOYEE_RESOURCE_PATH_ID_PATH;
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

@Path(EMPLOYEE_RESOURCE_NAME)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

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
    public Response getEmployees() {
        Response response = null;

        /*
        if (!sc.isCallerInRole(ADMIN_ROLENAME)) {
            response = Response.status(Status.FORBIDDEN).entity(GET_EMPLOYEES_OP_403_JSON_MSG).build();
        }
        else {
        */
            List<Employee> emps = simpleBean.getEmployeeList();
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
    @Path(EMPLOYEE_RESOURCE_PATH_ID_PATH)
    public Response getEmployeeById(@Parameter(description = PRIMARY_KEY_DESC, required = true)
        @PathParam(EMPLOYEE_RESOURCE_PATH_ID_ELEMENT) int id) {
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