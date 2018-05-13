package com.CouponSystem.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
 * CORS - Cross Origin Resource Sharing 
 * this class is for Rest Configuration .
 * will allow Access-control-Allow-Origin from different address , port .
 */

@Configuration
public class RestConfiguration 
{
    @Bean
    public CorsFilter corsFilter() 
    {
    	//variable of URLBasedCorsConfigurationSource
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //variable of CORS Configuration 
        CorsConfiguration config = new CorsConfiguration();
        
        //setting the allowed credentials , Origin , Header , Allowed-Methods
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        
        //registering the CORS Configuration to URLBasedCorsConfigurationSource for all the paths 
        source.registerCorsConfiguration("/**", config);
        
        //returning the CrosFilter
        return new CorsFilter(source);
    }
}