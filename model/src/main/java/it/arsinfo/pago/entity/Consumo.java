package it.arsinfo.pago.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

    public enum Anno {
        ANNO2016(2016),
        ANNO2017(2017),
        ANNO2018(2018),
        ANNO2019(2019),
        ANNO2020(2020),
        ANNO2021(2021),
        ANNO2022(2022),
        ANNO2023(2023),
        ANNO2024(2024),
        ANNO2025(2025),
        ANNO2026(2026),
        ANNO2027(2027),
        ANNO2028(2028),
        ANNO2029(2029),
        ANNO2030(2030);

        private int anno;

        public static Anno getAnnoPrecedente(Anno anno) {
            int annomenouno= anno.getAnno()-1;
            return Anno.valueOf("ANNO"+annomenouno);

        }
            public static Anno getAnnoSuccessivo(Anno anno) {
                int annopiuuno= anno.getAnno()+1;
                return Anno.valueOf("ANNO"+annopiuuno);

            }Anno(int anno) {
            this.anno=anno;
        }

        public String getAnnoAsString() {
            return Integer.toString(anno);
        }

        public int getAnno() {
            return anno;
        }

        public void setAnno(int anno) {
            this.anno = anno;
        }
        public static Anno getAnnoProssimo() {
            int annoProssimo = getAnnoCorrente().getAnno()+1;
            return Anno.valueOf("ANNO"+annoProssimo);
        }
        public static Anno getAnnoPassato() {
            int annoScorso = getAnnoCorrente().getAnno()-1;
            return Anno.valueOf("ANNO"+annoScorso);
        }
        public static Anno getAnnoCorrente() {
            return Anno.valueOf("ANNO"+new SimpleDateFormat("yyyy").format(new Date()));
        }
    }

    public enum Mese {
        GENNAIO("Gen.",1, "01"),
        FEBBRAIO("Feb.",2, "02"),
        MARZO("Mar.",3, "03"),
        APRILE("Apr.",4, "04"),
        MAGGIO("Mag.",5, "05"),
        GIUGNO("Giu.",6, "06"),
        LUGLIO("Lug.",7, "07"),
        AGOSTO("Ago.",8, "08"),
        SETTEMBRE("Set.",9, "09"),
        OTTOBRE("Ott.",10, "10"),
        NOVEMBRE("Nov.",11, "11"),
        DICEMBRE("Dic.",12, "12");

        private final String nomeBreve;
        private final int posizione;
        private final String code;

        public static Mese getMeseSuccessivo(Mese mese) {
            if (mese == Mese.DICEMBRE)
                return Mese.GENNAIO;
            return getByPosizione(mese.getPosizione()+1);
        }

        public static Mese getByPosizione(int posizione) {
                for (Mese mese: Mese.values()) {
                    if (mese.getPosizione() == posizione) {
                        return mese;
                    }
                }
                return null;

        }

        public static Mese getByCode(String code) {
            for (Mese mese: Mese.values()) {
                if (mese.getCode().equals(code)) {
                    return mese;
                }
            }
            return null;
        }

        Mese(String nome, int posizione, String cod) {
            this.nomeBreve=nome;
            this.posizione=posizione;
            this.code=cod;
        }

        public String getNomeBreve() {
            return nomeBreve;
        }
        public int getPosizione() {
            return posizione;
        }
        public String getCode() {
            return code;
        }
        public static Mese getMeseCorrente() {
            return getByCode(new SimpleDateFormat("MM").format(new Date()));
        }
    }
}
