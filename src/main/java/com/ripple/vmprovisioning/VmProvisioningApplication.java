package com.ripple.vmprovisioning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
@EnableTransactionManagement
public class VmProvisioningApplication {

	public static void main(String[] args) {
		SpringApplication.run(VmProvisioningApplication.class, args);
	}

}
