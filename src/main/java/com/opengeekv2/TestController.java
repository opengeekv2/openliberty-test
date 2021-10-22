package com.opengeekv2;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("hola")
@Controller
@RequestScoped
public class TestController {

    @Inject
    Models models;

    @Inject
    @ConfigProperty(name = "config_property")
    String configProperty;
    
    @GET
    public String getTest() {
        models.put("property", configProperty);
        return "hola.jsp";
    }
}
