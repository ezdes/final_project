
package com.example.project.Config;

import com.example.project.Filter.JwtFilter;
import com.example.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers("/sign-in/**").permitAll()
                .antMatchers("/search/**").permitAll()
                .antMatchers("/sign-up/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/categories/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/products/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/payments/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/roles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/images/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/jwtGetUser").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html/**", "/webjars/**");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}