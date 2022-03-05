package it.arsinfo.pago.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
public class Ricarica implements Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(nullable=false)
    private LocalDateTime dataPagamento =  LocalDateTime.now(ZoneId.of("Europe/Rome"));

    @Column(nullable=false)
    private BigDecimal importo = BigDecimal.ZERO;

    @ManyToOne(optional = false,fetch=FetchType.EAGER)
    private Armatore committente;

    public Ricarica() {
        super();
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Ricarica[id=%d, %s committente'%s', importo='%.2f']",
                             id, dataPagamento.format(DateTimeFormatter.ISO_DATE_TIME),committente,importo);
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

	public Armatore getCommittente() {
		return committente;
	}

	public void setCommittente(Armatore committente) {
		this.committente = committente;
	}

}
