package ru.kashtanov.product.service.web.application;


import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;


@Component(
		service = Application.class,
		property = {
				"osgi.jaxrs.application.base=/api/v1",
				"osgi.jaxrs.name=ProductApi"
		}
)
public class ProductServiceWebApplication extends Application {

}