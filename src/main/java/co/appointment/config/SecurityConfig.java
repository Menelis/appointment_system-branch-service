package co.appointment.config;

import co.appointment.shared.constant.RoleConstants;
import co.appointment.shared.util.SharedJwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Profile("!test")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppConfigProperties appConfigProperties;
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(this::setRequestMatchers)
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
    private void setRequestMatchers(
            final AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry requestMatcherRegistry) {
        if(appConfigProperties.getWhiteList() != null && appConfigProperties.getWhiteList().length > 0 ) {
            requestMatcherRegistry
                    .requestMatchers(appConfigProperties.getWhiteList())
                    .permitAll();
        }
        if(appConfigProperties.getAdminRoutes() != null && appConfigProperties.getAdminRoutes().length > 0 ) {
            requestMatcherRegistry
                    .requestMatchers(appConfigProperties.getAdminRoutes())
                    .hasAnyRole(RoleConstants.ADMIN_ROLE, RoleConstants.USER_ROLE);
        }
        requestMatcherRegistry.anyRequest().authenticated();
    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(final AppConfigProperties appConfigProperties) {
        return SharedJwtUtils.convertJwtGrantedAuthorities(
                appConfigProperties.getAuthoritiesClaimName(),
                appConfigProperties.getAuthoritiesPrefix());
    }
}
