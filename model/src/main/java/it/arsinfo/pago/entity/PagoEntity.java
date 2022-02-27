package it.arsinfo.pago.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Transient;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public interface PagoEntity {

    Logger log = LoggerFactory.getLogger(PagoEntity.class);

    Long getId();

}
