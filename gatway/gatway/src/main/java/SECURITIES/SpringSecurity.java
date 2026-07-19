package SECURITIES;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurity {
      @Value("${Cross.origin.value}")
      private List<String> Origin;
      private final KeyCloakConverter keyCloakConverter;
        @Bean public SecurityFilterChain FilterChain(HttpSecurity httpSecurity){
            httpSecurity.csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(httpSecuritySessionManagementConfigurer ->
                            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(configuration()))
                    /*
                     * author:@me
                     */
                    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                            .requestMatchers("/actuator/health/",
                                    "/actuator/info").permitAll()
                            .requestMatchers(
                                    "/swagger-ui/**",
                                    "/v3/api-docs/**"
                            ).denyAll()
                            .requestMatchers("http://localhost:9083/matches/**").authenticated()
                            .requestMatchers("feed/**").authenticated()
                            .requestMatchers("Admin/**").hasRole("ADMIN")
                            .requestMatchers("users/**").hasRole("USERS")
                            .anyRequest().authenticated())
                    .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer

                            .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(keyCloakConverter)));


                   return httpSecurity.build();
        }

        @Bean
        public CorsConfigurationSource configuration() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Origin);
            configuration.setAllowedMethods(List.of("POST", "PUT", "GET", "DELETE"));
            configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Request-Id"));
            configuration.setExposedHeaders(List.of("X-Request-Id"));
            configuration.setAllowCredentials(true);
            configuration.setMaxAge(3800L);
            UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
            urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);

            return urlBasedCorsConfigurationSource;
        }

    }


