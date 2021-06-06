package omar.mebarki.security.config;

import omar.mebarki.security.auth.CustomAuthenticationProvider;
import omar.mebarki.security.rest.HelloRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration()
@EnableWebSecurity
@ComponentScan(basePackageClasses = {CustomAuthenticationProvider.class, HelloRestController.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(getAuthenticationFiler(), BasicAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }

    public AuthenticationFilter getAuthenticationFiler() throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), request -> {
            return new UsernamePasswordAuthenticationToken(request.getParameter("login"), request.getParameter("password"));
        });
        AuthenticationSuccessHandler successHandler =(HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> { };
        authenticationFilter.setSuccessHandler(successHandler);
        return authenticationFilter;
    }
}
