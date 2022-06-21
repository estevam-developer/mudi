package br.com.estevam.mudi;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/home/**").permitAll()		
			.anyRequest().authenticated()
		.and()
			.formLogin(form -> form
					.loginPage("/login")
					.defaultSuccessUrl("/pedido/listar", true)
					.permitAll())
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/home"))
			.csrf().disable();
		
        return http.build();
    }
			
	@Bean
    public UserDetailsManager users(DataSource dataSource) {
		
		//createUser("leonardo", "123", dataSource);
		//createUser("anna", "123", dataSource);
		
        return new JdbcUserDetailsManager(dataSource);
	}
	
	private void createUser(String username, String password, DataSource dataSource) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails user = User.builder()
    			.username(username)
    			.password(encoder.encode(password))
    			.roles("ADM")
    			.build();
		
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        
        users.createUser(user);
	}
}
