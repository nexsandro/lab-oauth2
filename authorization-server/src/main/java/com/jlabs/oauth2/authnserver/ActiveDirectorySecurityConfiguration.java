package com.jlabs.oauth2.authnserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@Configuration
@Profile(value = "active-directory")
public class ActiveDirectorySecurityConfiguration {

    @Value("${auth.ad.domain.name}")
    private String domainName;

    @Value("${auth.ad.domain.ldapUrl}")
    private String domainLdapUrl;

    @Bean
    public AuthenticationProvider activeDirectoryAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider ad = new ActiveDirectoryLdapAuthenticationProvider(domainName, domainLdapUrl);
        return ad;
    }

}
