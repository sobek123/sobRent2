package pl.macieksob.rentCar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import pl.macieksob.rentCar.service.CustomUserDetailsService;

import java.util.Arrays;
//import pl.macieksob.rentCar.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthEntryPointJWT unauthorizedhandler;

//    @Autowired
//    private JWTTokenFilter jwtTokenFilter;

//    @Autowired
//    private JWTTokenFilter jwtTokenFilter;

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(customUserDetailsService);
//        authProvider.setPasswordEncoder(getPasswordEncoder());
//
//        return authProvider;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedhandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/auth/login", "/fullOrder/sortByPrizeAsc", "/fullOrder/sortByPrizeDes", "/fullOrder/sortByLanuchDateAsc", "/fullOrder/sortByLaunchDateDesc"+
                        "/fullOrder/newFullOrder", "/fullOrder/{id}","/fullOrder/deleteFullOrder/{id}","/fullOrder/getHistoricOrdersUser","/fullOrder/getActiveOrdersUser", "/users/**", "/roles/**","/contact/**", "/orders/**", "/cars/**", "/fullOrder", "/additional/**").permitAll().and()
                .authorizeRequests().antMatchers("/fullOrder/getActiveOrders", "/fullOrder/getHistoricOrders", "/fullOrder/rentToday", "/fullOrder/backToday").hasAnyRole("WORKER", "ADMIN")
//                .authorizeRequests().antMatchers("/auth/login").hasRole("ROLE_ADMIN")
                .anyRequest().authenticated().and().rememberMe().key("afdfgdgdfg").tokenValiditySeconds(86400);


////        http.authenticationProvider(authenticationProvider());
////        http.headers().frameOptions().disable();
//
//       http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

//        http.build();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://sobrent-front.herokuapp.com"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true); //error
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Set-Cookie", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
