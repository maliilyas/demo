package customer.application;

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

/**
 * Created by aliilyas on 04/03/2017.
 */
@SpringBootApplication
@ComponentScan("customer.web, customer.service, customer.datalayer")
@EnableSwagger2
public class CustomerApp {

    public static void main(final String [] args) {
        System.setProperty("spring.config.name", "customer-config");
        SpringApplication.run(CustomerApp.class, args);
    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Public Api for Customer Checkin/out")
                .select()
                .apis(RequestHandlerSelectors.basePackage("customer.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .genericModelSubstitutes(ResponseEntity.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Customer Api")
                .description("Customer Check In and Check Out Service.")
                .version("1.0-SNAPSHOT")
                .license("Copyrights of Ali,Mian Muhammad")
                .build();
    }
}
