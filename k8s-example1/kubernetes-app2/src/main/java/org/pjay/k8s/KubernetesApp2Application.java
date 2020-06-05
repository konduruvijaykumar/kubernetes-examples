package org.pjay.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author vijayk
 *
 */
@SpringBootApplication
public class KubernetesApp2Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(KubernetesApp2Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KubernetesApp2Application.class);
	}

}
