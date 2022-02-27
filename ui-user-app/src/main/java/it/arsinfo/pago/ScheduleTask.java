package it.arsinfo.pago;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduleTask {
    private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    @Scheduled(fixedDelay = 60000)
    public void downloadConsumi() {
        log.info("downloadConsumi; Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }
}
