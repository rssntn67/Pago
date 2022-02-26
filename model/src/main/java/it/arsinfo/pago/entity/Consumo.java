package it.arsinfo.pago.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Consumo implements PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional=false,fetch=FetchType.EAGER)
    private Utenza utenza;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Anno anno = Anno.getAnnoCorrente();

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Mese mese = Mese.getMeseCorrente();

    @Column(nullable=false)
    private Integer consumo = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date from = PagoEntity.getStandardDate(new Date());

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date to = PagoEntity.getStandardDate(new Date());

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
        return String.format("Consumo[id=%d, %s %s '%s', consumo=%d, importo='%.2f']",
                             id,
                             mese,
                             anno,
                             utenza.getIdentificativo(),
                            consumo,importo);
    }

    public Anno getAnno() {
        return anno;
    }

    public void setAnno(Anno anno) {
        this.anno = anno;
    }

    public Mese getMese() {
        return mese;
    }

    public void setMese(Mese mese) {
        this.mese = mese;
    }


    @Transient 
    public String getCaption() {
        return mese.getNomeBreve()+anno.getAnnoAsString();
    }


	@Override
	public String getHeader() {
        return String.format("%s %s %s '%.2f'",
                mese.getNomeBreve(),
                anno.getAnno(),
                utenza.getHeader(),
                importo);
	}

}
