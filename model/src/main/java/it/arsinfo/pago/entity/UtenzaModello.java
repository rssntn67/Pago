package it.arsinfo.pago.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"nome"})})
public class UtenzaModello implements PagoEntity {

    public enum TipoConsumo {
        ELETTRICA("Kw"),
        TELEFONICA("min");

        private final String unit;
        TipoConsumo(String unit) {
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
    private BigDecimal costoUnitario=BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TipoConsumo tipo=TipoConsumo.ELETTRICA;

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
        return String.format("UtenzaModello[id=%d, Nome='%s', Tipo='%s',CostoUnitario='%.2f']",
                             id, nome, tipo, costoUnitario);
    }

    public UtenzaModello() {
        super();
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

}
