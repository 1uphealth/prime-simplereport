package gov.cdc.usds.simplereport.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import gov.cdc.usds.simplereport.service.model.IdentitySupplier;

/**
 * Stub no-op configuration for development and test environments.
 */
@Profile(DevSecurityConfiguration.PROFILE)
@Configuration
@ConditionalOnWebApplication
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DevSecurityConfiguration.class);

    public static final String PROFILE = "no-security";

    @Autowired
    private InitialSetupProperties _setupProps;

    @Override
    public void configure(WebSecurity web) {
        logger.warn("SECURITY DISABLED BY {} PROFILE", PROFILE);
        web
                .ignoring()
                .antMatchers("/**");
    }

	@Bean
	public IdentitySupplier getDummyIdentity() {
		return _setupProps::getDefaultUser;
	}
}
