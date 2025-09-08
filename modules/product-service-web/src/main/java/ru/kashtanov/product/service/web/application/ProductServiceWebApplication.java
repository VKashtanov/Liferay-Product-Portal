package ru.kashtanov.product.service.web.application;


import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;


@Component(
		service = Application.class,
		property = {
				"osgi.jaxrs.application.base=/api/v1",
				"osgi.jaxrs.name=ProductApi",
				// 👇 Allow unauthenticated access
				"liferay.access.control.disable=true",
				// 👇 Optional: Disable CSRF for testing (not for production)
				"liferay.oauth.disable=true"
		}
)
public class ProductServiceWebApplication extends Application {

}