package com.sanjit.peopletracking.config;

import com.sanjit.peopletracking.service.CustomUserDetailsService;
import com.sanjit.peopletracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/resources/**",
            "/fonts/**",
            "/login",
            "/",
            "/img/**",
    };

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    protected UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userService);
    }

    public void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers("/noauth/**").permitAll()
                .antMatchers("/documents/**").permitAll()
                .antMatchers("/actuator/**", "/v2/api-docs/**", "/oauth/*").permitAll()
                .antMatchers("/auth/admin/**").hasAuthority("SYSTEM_ADMIN")
                .antMatchers("/auth/RELIANCElist/**").hasAnyAuthority("SYSTEM_ADMIN", "ADMIN", "USER")
                .antMatchers("/auth/**").hasAnyAuthority("SYSTEM_ADMIN", "ADMIN")
                .antMatchers("/api/**").hasAnyAuthority("SYSTEM_ADMIN", "ADMIN")
                .anyRequest()
                .authenticated()
                .and().csrf().disable().formLogin()
                .successHandler(customizeAuthenticationSuccessHandler)
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/pagenotfound");
    }

    @Bean
    public FilterRegistrationBean simpleCorsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
