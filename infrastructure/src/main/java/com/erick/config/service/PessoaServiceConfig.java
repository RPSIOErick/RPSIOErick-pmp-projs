package com.erick.config.service;

import com.erick.repository.PessoaRepository;
import com.erick.service.PessoaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaServiceConfig {
    @Bean
    public PessoaService pessoaService(PessoaRepository repository){
        return new PessoaService(repository);
    }
}
