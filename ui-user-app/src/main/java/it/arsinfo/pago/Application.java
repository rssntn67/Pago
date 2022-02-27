package it.arsinfo.pago;

import it.arsinfo.pago.entity.Armatore;
import it.arsinfo.pago.entity.Utenza;
import it.arsinfo.pago.entity.UtenzaModello;
import it.arsinfo.pago.service.api.ArmatoreService;
import it.arsinfo.pago.service.api.UtenzaModelloService;
import it.arsinfo.pago.service.api.UtenzaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));   // It will set CET timezone
        System.out.println("Spring boot application running in CET timezone :"+new Date());   // It will print UTC timezone
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    @Transactional
    public CommandLineRunner loadData(
            ArmatoreService armatoreService,
            UtenzaModelloService utenzaModelloService,
                    UtenzaService utenzaService) {
        return (args) -> {

            Armatore a = armatoreService.add();
            a.setImbarcazione("Medusa");
            a.setNome("Antonio");
            a.setCognome("Russo");
            armatoreService.save(a);

            Armatore b = armatoreService.add();
            b.setImbarcazione("Appio");
            b.setNome("Nicola");
            a.setCognome("Iadi");
            armatoreService.save(b);

            UtenzaModello modello = utenzaModelloService.add();
            modello.setNome("Standard");
            modello.setCostoUnitario(new BigDecimal("0.23"));

            utenzaModelloService.save(modello);

            Utenza ua= utenzaService.add();
            ua.setModello(modello);
            ua.setIdentificativo("T0-P1");
            utenzaService.save(ua);

            Utenza ub= utenzaService.add();
            ub.setModello(modello);
            ub.setIdentificativo("T0-P2");
            utenzaService.save(ub);

            Utenza uc= utenzaService.add();
            uc.setModello(modello);
            uc.setIdentificativo("T0-P3");
            utenzaService.save(uc);

            Utenza ud= utenzaService.add();
            ud.setModello(modello);
            ud.setIdentificativo("T0-P4");
            utenzaService.save(ud);


        };
    }

}
