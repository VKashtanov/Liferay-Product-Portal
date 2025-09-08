package ru.kashtanov.product.service.web.application.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import ru.kashtanov.product.service.api.dto.ProductDto;
import ru.kashtanov.product.service.api.dto.ProductRequestDto;
import ru.kashtanov.product.service.api.service.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//JAX-RS = Java API for X-something Restful Services
@Component(
        service = Object.class,
        property = {
                JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true",
                "osgi.jaxrs.application.select=(osgi.jaxrs.name=ProductApi)",
                // 👇 Also disable access control at resource level (belt and suspenders)
                "liferay.access.control.disable=true"
        }
)
@Path("/products")
@Consumes(MediaType.APPLICATION_JSON) // help the server to understand format of sending and receiving
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @Reference
    private ProductService productService;

    @POST
    @Path("/create")
    public Response createProduct(ProductRequestDto productRequestDto) {
        try {
            productService.createProduct(productRequestDto);
            return Response.ok().entity("Product created successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error creating product: " + e.getMessage())
                    .build();
        }
    }


    @GET
    @Path("/all")
    public Response getProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();
        Map<String, Object> response = new HashMap<>();
        response.put("products", allProducts);
        return Response.ok(response).build();
    }
}