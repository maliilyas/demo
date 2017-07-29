package com.demo.task.chatup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.demo.task.chatup.web, com.demo.task.chatup.datalayer, com.demo.task.chatup.service")
@EnableSwagger2
public class ChatUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatUpApplication.class, args);
	}

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Public Api for Chat")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.demo.task.chatup.web"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.genericModelSubstitutes(ResponseEntity.class);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ChatUp Api")
				.description("A rest api for ChatUp.")
				.version("1.0-SNAPSHOT")
				.license("Copyrights of Ali,Mian Muhammad")
				.build();
	}
}
