package it.arsinfo.pago.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Ricarica implements PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date dataPagamento = PagoEntity.getStandardDate(new Date());

    @Column(nullable=false)
    private BigDecimal importo = BigDecimal.ZERO;

    @ManyToOne(fetch=FetchType.EAGER)
    @Column(nullable=false)
    private Armatore committente;

    public Ricarica() {
        super();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Ricarica[id=%d, committente'%s', importo='%.2f']",
                             id, committente,importo);
    }

    @Transient
    public String getNomeCommittente() {
    	if (committente != null) {
    		return committente.getIntestazione();
    	}
    	return "";
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = PagoEntity.getStandardDate(dataPagamento);
    }

	public Armatore getCommittente() {
		return committente;
	}

	public void setCommittente(Armatore committente) {
		this.committente = committente;
	}


	@Override
	public String getHeader() {
        return String.format("['%.2f']",
                importo);
	}
}
