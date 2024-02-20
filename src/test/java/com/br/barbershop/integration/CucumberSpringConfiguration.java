package com.br.barbershop.integration;

import com.br.barbershop.BarbershopApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = BarbershopApplication.class)
public class CucumberSpringConfiguration {
}
