package it.arsinfo.pago.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Armatore implements PagoEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Utenza getUtenza() {
        return utenza;
    }

    public void setUtenza(Utenza utenza) {
        this.utenza = utenza;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    private Utenza utenza;

    @Column(nullable=false)
    private String nome;
    @Column(nullable=false)
    private String cognome;
    @Column(nullable=false)
    private String imbarcazione;

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public BigDecimal getCreditoResiduo() {
        return creditoResiduo;
    }

    public void setCreditoResiduo(BigDecimal creditoResiduo) {
        this.creditoResiduo = creditoResiduo;
    }

    @Column(nullable=false)
    private BigDecimal creditoResiduo = BigDecimal.ZERO;

    private String indirizzo;

    @Enumerated(EnumType.STRING)
    private Provincia provincia = Provincia.ND;

    private String cap;
    private String citta;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Paese paese = Paese.IT;

    private String email;
    private String telefono;
    private String cellulare;
    private String codfis;
    private String piva;

    public Armatore() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
    	if (nome == null) {
    		return "";
    	}
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImbarcazione() {
        return imbarcazione;
    }

    public void setImbarcazione(String imbarcazione) {
        this.imbarcazione = imbarcazione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public String toString() {
        return String.format("Anagrafica[id=%d, %s %s,credito='%.2f']",
                             id, nome, imbarcazione,creditoResiduo);
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public Paese getPaese() {
        return paese;
    }

    public void setPaese(Paese paese) {
        this.paese = paese;
    }

    public String getCodfis() {
        return codfis;
    }

    public void setCodfis(String codfis) {
        this.codfis = codfis;
    }

    public String getPiva() {
        return piva;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    @Transient
    public String getCaption() {
    	return Armatore.generaCaption(this);
    }

    @Transient
    public String getIntestazione() {
    	return Armatore.generaIntestazione(this);
    }

    @Transient
    public String getHeader() {
    	return generaIntestazione(this);
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public static String generaIntestazione(Armatore a) {
    	return String.format("%s %s",
        		a.getImbarcazione(),a.getNome());
    }

    public static String generaCaption(Armatore a) {
        return String.format("'%s %s %s %s'",
        		a.getImbarcazione(),
        		a.getNome(),
        		a.getCitta(),
        		a.getCap());
    }

}
