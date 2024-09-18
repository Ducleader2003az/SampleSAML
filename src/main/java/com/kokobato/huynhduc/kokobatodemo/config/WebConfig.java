package com.kokobato.huynhduc.kokobatodemo.config;

//import com.kokobato.huynhduc.kokobatodemo.filters.JwtTokenFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.saml2.provider.service.authentication.OpenSaml4AuthenticationProvider;
import org.springframework.security.saml2.provider.service.authentication.OpenSaml4AuthenticationProvider.ResponseToken;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
//    private final JwtTokenFilter jwtTokenFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(request -> {
//                    request.requestMatchers(
//                            "/api/user/register",
//                            "api/user/login"
//                    ).permitAll();
//                    request.requestMatchers("/api/user/list").hasRole("ADMIN")
//                            .anyRequest().authenticated();
//                })
//                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    SecurityFilterChain configureWithSaml(HttpSecurity http) throws Exception {
        OpenSaml4AuthenticationProvider provider = new OpenSaml4AuthenticationProvider();
        provider.setResponseAuthenticationConverter(groupsConverter());
        http
                .csrf().disable()
                .authorizeHttpRequests(request ->
                        request.anyRequest().authenticated())
                .saml2Login(saml2 ->
                        saml2.authenticationManager(new ProviderManager(provider))
                )
                .saml2Logout(withDefaults());
        return http.build();
    }

    private Converter<ResponseToken, Saml2Authentication> groupsConverter() {
        Converter<ResponseToken, Saml2Authentication> delegate =
                OpenSaml4AuthenticationProvider.createDefaultResponseAuthenticationConverter();

        return (reponseToken) -> {
            Saml2Authentication authentication = delegate.convert(reponseToken);
            Saml2AuthenticatedPrincipal principal = (Saml2AuthenticatedPrincipal) authentication.getPrincipal();
            List<String> groups = principal.getAttribute("groups");

            Set<GrantedAuthority> authorities = new HashSet<>();

            if (groups != null) {
                groups.stream().map(SimpleGrantedAuthority::new).forEach(authorities::add);
            } else {
                authorities.addAll(authentication.getAuthorities());
            }

            return new Saml2Authentication(principal, authentication.getSaml2Response(), authorities);
        };
    }

}
