package br.com.se.oxp.api.auditoria;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import br.com.se.oxp.api.enums.Status;
import br.com.se.oxp.api.model.Usuario;

@Configuration
@EnableJpaAuditing
class AuditConfig {
    @Bean
    public AuditorAware<Usuario> createAuditorProvider() {
        return new SecurityAuditor();
    }

    @Bean
    public AuditingEntityListener createAuditingListener() {
        return new AuditingEntityListener();
    }

    public static class SecurityAuditor implements AuditorAware<Usuario> {
    	
        @Override
        public Optional<Usuario> getCurrentAuditor() {
            //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            //String username = auth.getName();
            //return username;
        	Usuario usuario = Usuario.builder()
        			.id(1L)
        			.nome("Malibu Leiro")
        			.login("malibu")
        			.status(Status.A)
        			.build();
        	
        	
        	return Optional.of(usuario);
        }
    }	
}