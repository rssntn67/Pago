package it.arsinfo.pago.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"nome"})})
public class UtenzaModello implements PagoEntity {

    public enum TipoConsumo {
        ELETTRICA("Kw"),
        TELEFONICA("min");

        private String unit;
        private TipoConsumo(String unit) {
            this.unit=unit;
        }

        public String getUnit() {
            return unit;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String nome;

    private String descrizione;

    @Column(nullable=false)
    private boolean active = true;

    @Column(nullable=false)
    private BigDecimal costoUnitario=BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TipoConsumo tipo=TipoConsumo.ELETTRICA;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Anno anno;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Mese mese;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return String.format("Pubblicazione[id=%d, Nome='%s', Tipo='%s',CostoUnitario='%.2f']",
                             id, nome, tipo, costoUnitario);
    }

    public UtenzaModello() {
        super();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costo) {
        this.costoUnitario = costo;
    }


    @Transient
    public String getCaption() {
        return String.format("%s, %s. EUR:%f.", nome, tipo, costoUnitario);
    }

    @Transient
    public String getHeader() {
        return String.format("'%s'", nome);
    }

    public Anno getAnno() {
        return anno;
    }

    public void setAnno(Anno anno) {
        this.anno = anno;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoConsumo getTipo() {
        return tipo;
    }

    public void setTipo(TipoConsumo tipo) {
        this.tipo = tipo;
    }

    public Mese getMese() {
        return mese;
    }

    public void setMese(Mese mese) {
        this.mese = mese;
    }
}
