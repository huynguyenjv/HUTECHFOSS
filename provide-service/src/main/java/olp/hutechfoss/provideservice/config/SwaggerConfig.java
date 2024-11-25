package olp.hutechfoss.provideservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HUTECHFOSS API DEVELOPED BY FOSS TEAM")
                        .version("v1")
                        .description("API for managing my application")
                        .contact(new Contact()
                                .name("Support Team")
                                .email("hutech.foss@gmail.com")
                                .url("https://hutechfoss.com"))
                        .license(new License()
                                .name("GNU General Public License")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
    }
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .displayName("Public API")
                .pathsToMatch("/**")
                .build();
    }
}
