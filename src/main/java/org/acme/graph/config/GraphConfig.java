package org.acme.graph.config;

import java.io.File;
import java.io.IOException;

import org.acme.graph.io.GraphReader;
import org.acme.graph.model.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * [spring] Cette classe permet d'initialiser des objects pour l'application
 * et de gérer des paramètres
 * 
 * @see https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html
 * 
 * @author mborne
 */
@Configuration
public class GraphConfig {
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Value("${graph.path}")
	private String path;

	@Bean
	public Graph graph() throws Exception {
		return GraphReader.read(getGraphFile());
	}

	File getGraphFile() throws IOException{
		if ( path.startsWith("classpath:") ) {
			return resourceLoader.getResource(path).getFile();
		}else {
			return new File(path);
		}
	}
	
	
}
