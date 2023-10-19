// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.azuresamples.msal4j.msidentityspringbootwebapp;

import com.azure.spring.Microsoft Entra ID.webapp.Microsoft Entra IDWebSecurityConfigurerAdapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Microsoft Entra IDWebSecurityConfigurer (Microsoft Entra IDWSC) is an extension of Spring's WebSecurityConfigurer (WSC).
 * 
 * You must extend Microsoft Entra IDWSC to define your own custom configuration in the configure() method.
 * Be sure to call super.configure() first. This will set up all of your AuthN/AuthZ properly.
 * 
 * You may omit this by not extending the Microsoft Entra IDWSC class.
 * 
 * If you don't extend Microsoft Entra IDWSC or WSC, Microsoft Entra ID boot starter will create a DefaultMicrosoft Entra IDWebSecurityConfigurerAdapter
 * bean automatically, and define its own default http.authorizeRequests() rule (authorize ALL requests).
 * 
 * See DefaultMicrosoft Entra IDWebSecurityConfigurerAdapter in com.azure.spring.Microsoft Entra ID.webapp.Microsoft Entra IDWebAppConfiguration
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends Microsoft Entra IDWebSecurityConfigurerAdapter{
  @Value( "${app.protect.authenticated}" )
  private String[] protectedRoutes;

    @Override
    public void configure(HttpSecurity http) throws Exception {
    // use required configuration from Microsoft Entra IDWebSecurityAdapter.configure:
    super.configure(http);
    // add custom configuration:
    http.authorizeRequests()
      .antMatchers(protectedRoutes).authenticated()     // limit these pages to authenticated users (default: /token_details, /call_graph)
      .antMatchers("/**").permitAll();                  // allow all other routes.
    }
}
