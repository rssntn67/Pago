package it.arsinfo.pago.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"nome"})})
public class Utenza implements PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String identificativo;

    private String descrizione;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch=FetchType.EAGER)
    @Column(nullable = false)
    private UtenzaModello modello;

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return String.format("Utenza[id=%d, Nome='%s', Modello='%s']",
                id, identificativo, modello);
    }
    public Utenza() {
        super();
    }

    public String getIdentificativo() {
        return identificativo;
    }

    public void setIdentificativo(String identificativo) {
        this.identificativo = identificativo;
    }

    public UtenzaModello getModello() {
        return modello;
    }

    public void setModello(UtenzaModello modello) {
        this.modello = modello;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Transient
    public String getHeader() {
        return String.format("'%s'", identificativo);
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
