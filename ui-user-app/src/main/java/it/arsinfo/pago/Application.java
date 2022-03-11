package it.arsinfo.pago;

import it.arsinfo.pago.entity.*;
import it.arsinfo.pago.service.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));   // It will set CET timezone
        System.out.println("Spring boot application running in CET timezone :"+new Date());   // It will print UTC timezone
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}


    @Bean
    @Transactional
    public CommandLineRunner loadData(
            ArmatoreService armatoreService,
            ModelloService utenzaModelloService,
            PagoUserService pagoService,
            UtenzaService utenzaService,
            RicaricaService ricaricaService,
            PasswordEncoder passwordEncoder
    ) {
        return (args) -> {


            PagoUser administrator = pagoService.findByUsernameAndProvider("admin", PagoUser.Provider.LOCAL);
            if (administrator == null) {
                administrator = new PagoUser("admin", passwordEncoder.encode("admin000!!!"), PagoUser.Role.ADMIN);
                pagoService.save(administrator);
                log.info("creato user admin/admin000!!!");
            }

            PagoUser pago = pagoService.findByUsernameAndProvider("pago", PagoUser.Provider.LOCAL);
            if (pago == null) {
                pago = new PagoUser("pago", passwordEncoder.encode("pago111!!"), PagoUser.Role.USER);
                pagoService.save(pago);
                log.info("creato user pago/pago111!!");
            }

            Armatore a = armatoreService.add();
            a.setImbarcazione("Medusa");
            a.setNome("Antonio");
            a.setCognome("Russo");
            armatoreService.save(a);
            log.info("creato Armatore {}", a.getCaption());

            Armatore b = armatoreService.add();
            b.setImbarcazione("Appio");
            b.setNome("Nicola");
            b.setCognome("Iadi");
            b.setCreditoResiduo(new BigDecimal("10.34"));
            armatoreService.save(b);
            log.info("creato Armatore {}", b.getCaption());

            Modello modello = utenzaModelloService.add();
            modello.setNome("Standard");
            modello.setCostoUnitario(new BigDecimal("0.23"));
            utenzaModelloService.save(modello);
            log.info("creato {}", modello);

            Utenza ua= utenzaService.add();
            ua.setModello(modello);
            ua.setIdentificativo("T0-P1");
            utenzaService.save(ua);
            log.info("creato {}", ua);

            Utenza ub= utenzaService.add();
            ub.setModello(modello);
            ub.setIdentificativo("T0-P2");
            utenzaService.save(ub);
            log.info("creato {}", ub);

            Utenza uc= utenzaService.add();
            uc.setModello(modello);
            uc.setIdentificativo("T0-P3");
            utenzaService.save(uc);
            log.info("creato {}", uc);

            Utenza ud= utenzaService.add();
            ud.setModello(modello);
            ud.setIdentificativo("T0-P4");
            utenzaService.save(ud);
            log.info("creato {}", ud);

            Ricarica ricarica = ricaricaService.add();
            ricarica.setCommittente(b);
            ricarica.setImporto(new BigDecimal("50"));
            ricaricaService.save(ricarica);
            log.info("creato {}", ricarica);



        };
    }

}
