package eventos.configuration;

//import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
/*import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;*/
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class DataUserConfiguration{
	
	@Bean
	public UserDetailsManager usersCustom(DataSource dataSource) {

	JdbcUserDetailsManager users = 
			new JdbcUserDetailsManager(dataSource); 
	users.setUsersByUsernameQuery("select username,password,enabled from Usuarios u where username=?"); 
	users.setAuthoritiesByUsernameQuery("select u.username,p.nombre from Usuario_Perfiles up " +
	 "inner join usuarios u on u.username = up.username " +
			"inner join perfiles p on p.id_perfil = up.id_perfil " +
			"where u.username = ?");

	return users;

	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf -> csrf.disable());
		// Los recursos estáticos no requieren autenticación
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("/static/**", "/css/**", "/js/**", "/json/**", "/images/**").permitAll()
			// Las vistas públicas no requieren autenticación
			.requestMatchers("/", "/home", "/login", "/logout", "/eventos/verUno/**","/inicioSesion","/falloInicioSesion").permitAll()
			.requestMatchers("/eventos/activos", "/eventos/destacados", "/eventos/filtrarTipo", "/eventos/detalle/{id:\\d+}").permitAll()
			.requestMatchers("/rest/encriptar/**").permitAll()
			.requestMatchers("/registro").permitAll()
			// Todas las demás URLs de la Aplicación requieren autenticación
			// Asignar permisos a URLs por ROLES
			//.requestMatchers("/eventos/**").hasAnyAuthority("ROLE_CLIENTE") 
			//.requestMatchers("/reservas/**").hasAnyAuthority("ROLE_CLIENTE") 
			
			.anyRequest().authenticated())
		// El formulario de Login no requiere autenticacion
		.formLogin(form -> form.loginPage("/login")
				   .defaultSuccessUrl("/inicioSesion")
				   .failureUrl("/falloInicioSesion")
				   .permitAll())
       .logout(logout -> logout.logoutSuccessUrl("/").permitAll());
		//  .sessionManagement(sessionManagement -> sessionManagement
       // .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
    //);
		return http.build();
	}
	
	//.formLogin(form -> form.permitAll());
	
	//Activar este bean si queremos las password encriptadas
	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/
	

}
