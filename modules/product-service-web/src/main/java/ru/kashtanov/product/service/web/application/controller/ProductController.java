package ru.kashtanov.product.service.web.application.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

//JAX-RS = Java API for X-something Restful Services
@Component( // Register this class in OSGI container for further usage
        service = Object.class,
        property = {
                JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true",
                "osgi.jaxrs.application.select=(osgi.jaxrs.name=ProductApi)"
        }
)
@Path("/products")
@Consumes(MediaType.APPLICATION_JSON) // help the server to understand format of sending and receiving
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @GET
    @Path("/all")
    public Response getProducts() {
        Map<String, Object> response = new HashMap<>();
        response.put("total", 2);
        response.put("items", new Object[]{
                Map.of("id", 1, "name", "Laptop"),
                Map.of("id", 2, "name", "Mouse")
        });
        return Response.ok(response).build();
    }
}