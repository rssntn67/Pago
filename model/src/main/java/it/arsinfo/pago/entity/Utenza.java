package it.arsinfo.pago.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"identificativo"})})
public class Utenza implements Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String identificativo;

    private String descrizione;

    @Column(nullable = false)
    private boolean active = false;

    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    private Modello modello;

    @ManyToOne(fetch=FetchType.LAZY)
    private Armatore utente;

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

    public Modello getModello() {
        return modello;
    }

    public void setModello(Modello modello) {
        this.modello = modello;
    }

    public Armatore getUtente() {
        return utente;
    }

    public void setUtente(Armatore utente) {
        this.utente = utente;
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
