package it.arsinfo.pago.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
public class Consumo implements PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional=false,fetch=FetchType.EAGER)
    private Utenza utenza;

    public Integer getConsumo() {
        return consumo;
    }

    public void setConsumo(Integer consumo) {
        this.consumo = consumo;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    @Column(nullable=false)
    private Integer consumo = 0;

    @Basic
    @Column(nullable=false)
    private LocalDateTime fromDate = LocalDateTime.now(ZoneId.of("Europe/Rome"));

    @Basic
    @Column(nullable=false)
    private LocalDateTime toDate = LocalDateTime.now(ZoneId.of("Europe/Rome"));

    @Column(nullable=false)
    private BigDecimal importo = BigDecimal.ZERO;

    public Consumo() {
    }


    public Long getId() {
        return id;
    }


    public Utenza getUtenza() {
        return utenza;
    }

    public void setUtenza(Utenza utenza) {
        this.utenza = utenza;
    }

    @Override
    public String toString() {
        return String.format("Consumo[id=%d, '%s',da %s a %s -> consumo=%d %s, importo='%.2f']",
                             id,
                utenza.getIdentificativo(),
                 fromDate.format(DateTimeFormatter.ISO_DATE_TIME),
                 toDate.format(DateTimeFormatter.ISO_DATE_TIME),
                consumo,
                utenza.getModello().getTipo().getUnit(),
                            importo);
    }

}
