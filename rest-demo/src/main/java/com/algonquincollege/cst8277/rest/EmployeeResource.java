package com.algonquincollege.cst8277.rest;

import java.util.List;

//import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.algonquincollege.cst8277.ejb.SimpleBean;
import com.algonquincollege.cst8277.models.Employee;

@Path("employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @EJB
    protected SimpleBean simpleBean;

    @Inject
    protected SecurityContext sc;

    @GET
    @Operation(description = "Retrieves list of Employees")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Successful, returning employees"),
        @APIResponse(responseCode = "404", description = "We could not find employees")
    })
    public Response getEmployees() throws AuthenticationException {
        if (!sc.isCallerInRole("admin")) {
            throw new AuthenticationException("only admin's can list all employees");
        }
        List<Employee> emps = simpleBean.getEmployeeList();
        return Response.ok(emps).build();
    }

    @PUT
    @Operation(description = "Updates a given employee")
    @APIResponses({
        @APIResponse(responseCode = "200",description = "We successfully updated the employee"),
        @APIResponse(responseCode = "404",description = "We could not update the given employes")
    })
    public Response updateEmployee(@Parameter(description = "a given employee", required = true)Employee e) throws AuthenticationException {

        if (!sc.isCallerInRole("user")) {
            throw new AuthenticationException("only user can update employee");
        }

        simpleBean.updateEmployee(e);

        return Response.ok().build();
    }
}
