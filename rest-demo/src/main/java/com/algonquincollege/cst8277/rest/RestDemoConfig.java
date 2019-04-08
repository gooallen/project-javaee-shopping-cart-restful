package com.algonquincollege.cst8277.rest;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("/api/v1")
@OpenAPIDefinition(info = @Info(
    title = "Rest'ful Demo API",
    version = "1.0.0",
    description = "API resource for Employee model from Assignment3"
    ),
    servers = {
        @Server(url = "/demo")
    }
)
//in web.xml
@DeclareRoles({ "user", "admin" })
public class RestDemoConfig extends Application {
}