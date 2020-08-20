package com.algaworks.algafood.core.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.mail.FakeEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.mail.SandboxEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.mail.SmtpEnvioEmailService;

@Configuration
public class MailConfig {
    
	@Autowired
    private MailProperties mailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        // Acho melhor usar switch aqui do que if/else if
        switch (mailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            case SANDBOX:
                return new SandboxEnvioEmailService();                
            default:
                return null;
        }
    }
}
