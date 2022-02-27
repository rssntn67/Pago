package it.arsinfo.pago.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Armatore implements PagoEntity {

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String cognome;
    @Column(nullable=false)
    private String imbarcazione;
    @Column(nullable=false)
    private BigDecimal creditoResiduo = BigDecimal.ZERO;
    private String indirizzo;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return String.format("Armatore[id=%d, %s %s,credito='%.2f']",
                             id, nome, imbarcazione,creditoResiduo);
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

    public enum Paese {
        AD("Andorra","AND"),
        AE("Emirati Arabi Uniti","ARE"),
        AF("Afghanistan","AFG"),
        AG("Antigua e Barbuda","AIG"),
        AI("Anguilla","AIA"),
        AL("Albania","ALB"),
        AM("Armenia","ARM"),
        AN("Antille olandesi[1]","ANT"),
        AO("Angola","AGO"),
        AQ("Antartico","ATA"),
        AR("Argentina","ARG"),
        AS("Samoa americane","ASM"),
        AT("Austria","AUT"),
        AU("Australia","AUS"),
        AW("Aruba","ABW"),
        AX("Isole Åland","ALA"),
        AZ("Azerbaigian","AZE"),
        BA("Bosnia-Erzegovina","BIH"),
        BB("Barbados","BRB"),
        BD("Bangladesh","BGD"),
        BE("Belgio","BEL"),
        BF("Burkina Faso","BFA"),
        BG("Bulgaria","BGR"),
        BH("Bahrein","BHR"),
        BI("Burundi","BDI"),
        BJ("Benin","BEN"),
        BL("Saint Barthelémy","BLM"),
        BM("Bermuda","BMU"),
        BN("Brunei Darussalam","BRN"),
        BO("Bolivia","BOL"),
        BQ("Bonaire, Sant'Eustachio e Saba","BES"),
        BR("Brasile","BRA"),
        BS("Bahamas","BHS"),
        BT("Bhutan","BTN"),
        BV("Isola Bouvet","BVT"),
        BW("Botswana","BWA"),
        BY("Bielorussia","BLR"),
        BZ("Belize","BLZ"),
        CA("Canada","CAN"),
        CC("Isole Cocos/Keeling","CCK"),
        CD("Repubblica democratica del Congo","COD"),
        CF("Repubblica Centrafricana","CAF","RCF"),
        CG("Congo","COG"),
        CH("Svizzera","CHE"),
        CI("Costa D'Avorio","CIV"),
        CK("Isole Cook","COK"),
        CL("Cile","CHL"),
        CM("Camerun","CMR"),
        CN("Cina","CHN"),
        CO("Colombia","COL"),
        CR("Costa Rica","CRI"),
        CU("Cuba","CUB"),
        CV("Capo Verde","CPV"),
        CW("Curaçao","CUW"),
        CX("Isola Christmas (Australia)","CXR"),
        CY("Cipro","CYP"),
        CZ("Repubblica Ceca","CZE"),
        DE("Germania","DEU"),
        DJ("Gibuti","DJI"),
        DK("Danimarca","DNK"),
        DM("Dominica","DMA"),
        DO("Repubblica Dominicana","DOM"),
        DZ("Algeria","DZA"),
        EC("Ecuador","ECU"),
        EE("Estonia","EST"),
        EG("Egitto","EGY"),
        EH("Sahara occidentale","ESH"),
        ER("Eritrea","ERI"),
        ES("Spagna","ESP"),
        ET("Etiopia","ETH"),
        FI("Finlandia","FIN"),
        FJ("Figi","FJI"),
        FK("Isole Falkland","FLK"),
        FM("Micronesia","FSM"),
        FO("Isole Fær Øer","FRO"),
        FR("Francia","FRA"),
        GA("Gabon","GAB"),
        GB("Regno Unito","GBR"),
        GD("Grenada","GRD"),
        GE("Georgia","GEO"),
        GF("Guiana francese","GUF"),
        GG("Guernsey","GGY"),
        GH("Ghana","GHA"),
        GI("Gibilterra","GIB"),
        GL("Groenlandia","GRL"),
        GM("Gambia","GMB"),
        GN("Guinea","GIN"),
        GP("Guadalupa","GLP"),
        GQ("Guinea equatoriale","GNQ"),
        GR("Grecia","GRC"),
        GS("Georgia del Sud e isole Sandwich meridionali","SGS"),
        GT("Guatemala","GTM"),
        GU("Guam","GUM"),
        GW("Guinea-Bissau","GNB"),
        GY("Guiana","GUY"),
        HK("Hong Kong","HKG"),
        HM("Isole Heard e McDonald","HMD"),
        HN("Honduras","HND"),
        HR("Croazia","HRV"),
        HT("Haiti","HTI"),
        HU("Ungheria","HUN"),
        ID("Indonesia","IDN"),
        IE("Irlanda","IRL"),
        IL("Israele","ISR"),
        IM("Isola di Man","IMN"),
        IN("India","IND"),
        IO("Territorio britannico dell'Oceano Indiano","IOT"),
        IQ("Iraq","IRQ"),
        IR("Iran","IRN"),
        IS("Islanda","ISL"),
        IT("Italia","ITA"),
        JE("Jersey","JEY"),
        JM("Giamaica","JAM"),
        JO("Giordania","JOR"),
        JP("Giappone","JPN"),
        KE("Kenya","KEN"),
        KG("Kirghizistan","KGZ"),
        KH("Cambogia","KHM"),
        KI("Kiribati","KIR"),
        KM("Comore","COM"),
        KN("Saint Kitts e Nevis","KNA"),
        KP("Repubblica democratica popolare di Corea","PRK"),
        KR("Repubblica di Corea","KOR"),
        KW("Kuwait","KWT"),
        KY("Isole Cayman","CYM"),
        KZ("Kazakistan","KAZ"),
        LA("Laos","LAO"),
        LB("Libano","LBN"),
        LC("Saint Lucia","LCA"),
        LI("Liechtenstein","LIE"),
        LK("Sri Lanka","LKA"),
        LR("Liberia","LBR"),
        LS("Lesotho","LSO"),
        LT("Lituania","LTU"),
        LU("Lussemburgo","LUX"),
        LV("Lettonia","LVA"),
        LY("Libia","LBY"),
        MA("Marocco","MAR"),
        MC("Monaco","MCO"),
        MD("Moldavia","MDA"),
        ME("Montenegro","MNE"),
        MF("Saint Martin (parte francese)","MAF"),
        MG("Madagascar","MDG"),
        MH("Isole Marshall","MHL"),
        MK("Macedonia (Ex-Repubblica Iugoslava di)","MKD"),
        ML("Mali","MLI"),
        MM("Myanmar (ex Birmania)","MMR"),
        MN("Mongolia","MNG"),
        MO("Macao","MAC"),
        MP("Isole Marianne settentrionali","MNP"),
        MQ("Martinica","MTQ"),
        MR("Mauritania","MRT"),
        MS("Montserrat","MSR"),
        MT("Malta","MLT"),
        MU("Mauritius","MUS"),
        MV("Maldive","MDV"),
        MW("Malawi","MWI"),
        MX("Messico","MEX"),
        MY("Malesia","MYS"),
        MZ("Mozambico","MOZ"),
        NA("Namibia","NAM"),
        NC("Nuova Caledonia","NCL"),
        NE("Niger","NER"),
        NF("Isola Norfolk","NFK"),
        NG("Nigeria","NGA"),
        NI("Nicaragua","NIC"),
        NL("Paesi Bassi","NLD"),
        NO("Norvegia","NOR"),
        NP("Nepal","NPL"),
        NR("Nauru","NRU"),
        NU("Niue","NIU"),
        NZ("Nuova Zelanda","NZL"),
        OM("Oman","OMN"),
        PA("Panama","PAN"),
        PE("Perù","PER"),
        PF("Polinesia Francese","PYF"),
        PG("Papua Nuova Guinea","PNG"),
        PH("Filippine","PHL"),
        PK("Pakistan","PAK"),
        PL("Polonia","POL"),
        PM("Saint-Pierre e Miquelon","SPM"),
        PN("Isole Pitcairn", "PCN"),
        PR("Portorico","PRI"),
        PS("Territorio Palestinese, occupato","PSE"),
        PT("Portogallo","PRT"),
        PW("Palau","PLW"),
        PY("Paraguay","PRY"),
        QA("Qatar","QAT"),
        RE("Riunione","REU"),
        RO("Romania","ROU","ROM"),
        RS("Serbia","SRB"),
        RU("Russia","RUS"),
        RW("Ruanda","RWA"),
        SA("Arabia Saudita","SAU"),
        SB("Isole Salomone","SLB"),
        SC("Seychelles","SYC"),
        SD("Sudan","SDN"),
        SE("Svezia","SWE"),
        SG("Singapore","SGP"),
        SH("Sant'Elena, Ascensione e Tristan da Cunha","SHN"),
        SI("Slovenia","SVN"),
        SJ("Svalbard e Isola Jan Mayen","SJN"),
        SK("Slovacchia","SVK"),
        SL("Sierra Leone","SLE"),
        SM("San Marino","SRM","RSM"),
        SN("Senegal","SEN"),
        SO("Somalia","SOM"),
        SR("Suriname","SUR"),
        SS("Sudan del sud","SSD"),
        ST("Sao Tomé e Principe","STP"),
        SV("El Salvador","SLV"),
        SX("Sint Maarten (parte olandese)","SXM"),
        SY("Siria","SYR"),
        SZ("Swaziland","SWZ"),
        TC("Isole Turks e Caicos","TCA"),
        TD("Ciad","TCD"),
        TF("Terre australi e antartiche francesi","ATF"),
        TG("Togo","TGO"),
        TH("Tailandia","THA"),
        TJ("Tagikistan","TJK"),
        TK("Tokelau","TKL"),
        TL("Timor Est (Timor-Leste)","TLS"),
        TM("Turkmenistan","TKM"),
        TN("Tunisia","TUN"),
        TO("Tonga","TON"),
        TR("Turchia","TUR"),
        TT("Trinidad e Tobago","TTO"),
        TV("Tuvalu","TUV"),
        TW("Taiwan","TWN"),
        TZ("Tanzania","TZA"),
        UA("Ucraina","UKR"),
        UG("Uganda","UGA"),
        UM("Isole minori esterne degli Stati Uniti","UMI"),
        US("Stati Uniti","USA"),
        UY("Uruguay","URY"),
        UZ("Uzbekistan","UZB"),
        VA("Santa Sede (Stato della Città del Vaticano)","VAT","CVC"),
        VC("Saint Vincent e Grenadine","VCT"),
        VE("Venezuela","VEN"),
        VG("Isole Vergini britanniche","VGB"),
        VI("Isole Vergini americane","VIR"),
        VN("Vietnam","VNM"),
        VU("Vanuatu","VUT"),
        WF("Wallis e Futuna","WLF"),
        WS("Samoa","WSM"),
        YE("Yemen","YEM"),
        YT("Mayotte","MYT"),
        ZA("Sudafrica","ZAF"),
        ZM("Zambia","ZMB"),
        ZW("Zimbabwe","ZWE"),
        ND("Non Definito","000");

        private final String nome;
        private final String sigla;
        private final String oldsigla;

        Paese(String nome,String sigla) {
            this.nome = nome;
            this.sigla = sigla;
            this.oldsigla=sigla;
        }

        Paese(String nome,String sigla,String oldsigla) {
            this.nome = nome;
            this.sigla = sigla;
            this.oldsigla=oldsigla;
        }

        public String getNome() {
            return nome;
        }
        public String getSigla() {
            return sigla;
        }
        public String getOldsigla() {
            return oldsigla;
        }

        public static Paese getByNome(String nome) {
            for (Paese p : Paese.values()) {
                if (p.getNome().equalsIgnoreCase(nome)) {
                    return p;
                }
            }
            return Paese.ND;

        }

        public static Paese getBySigla(String sigla) {
            for (Paese p : Paese.values()) {
                if (p.getSigla().equals(sigla) ||p.getOldsigla().equals(sigla)) {
                    return p;
                }
            }
            return Paese.ND;
        }
    }

    public enum Provincia {

           ND(""),
           AG("Agrigento"),
           AL("Alessandria"),
           AN("Ancona"),
           AO("Aosta"),
           AR("Arezzo"),
           AP("Ascoli Piceno"),
           AT("Asti"),
           AV("Avellino"),
           BA("Bari"),
           BT("Barletta-Andria-Trani"),
           BL("Belluno"),
           BN("Benevento"),
           BG("Bergamo"),
           BI("Biella"),
           BO("Bologna"),
           BZ("Bolzano"),
           BS("Brescia"),
           BR("Brindisi"),
           CA("Cagliari"),
           CL("Caltanissetta"),
           CB("Campobasso"),
           CI("Carbonia-Iglesias"),
           CE("Caserta"),
           CT("Catania"),
           CZ("Catanzaro"),
           CH("Chieti"),
           CO("Como"),
           CS("Cosenza"),
           CR("Cremona"),
           KR("Crotone"),
           CN("Cuneo"),
           EN("Enna"),
           FM("Fermo"),
           FE("Ferrara"),
           FI("Firenze"),
           FG("Foggia"),
           FC("Forlì-Cesena"),
           FR("Frosinone"),
           GE("Genova"),
           GO("Gorizia"),
           GR("Grosseto"),
           IM("Imperia"),
           IS("Isernia"),
           SP("La Spezia"),
           AQ("L'Aquila"),
           LT("Latina"),
           LE("Lecce"),
           LC("Lecco"),
           LI("Livorno"),
           LO("Lodi"),
           LU("Lucca"),
           MC("Macerata"),
           MN("Mantova"),
           MS("Massa-Carrara"),
           MT("Matera"),
           ME("Messina"),
           MI("Milano"),
           MO("Modena"),
           MB("Monza e della Brianza"),
           NA("Napoli"),
           NO("Novara"),
           NU("Nuoro"),
           OT("Olbia-Tempio"),
           OR("Oristano"),
           PD("Padova"),
           PA("Palermo"),
           PR("Parma"),
           PV("Pavia"),
           PG("Perugia"),
           PU("Pesaro e Urbino"),
           PE("Pescara"),
           PC("Piacenza"),
           PI("Pisa"),
           PT("Pistoia"),
           PN("Pordenone"),
           PZ("Potenza"),
           PO("Prato"),
           RG("Ragusa"),
           RA("Ravenna"),
           RC("Reggio Calabria"),
           RE("Reggio Emilia"),
           RI("Rieti"),
           RN("Rimini"),
           RM("Roma"),
           RO("Rovigo"),
           SA("Salerno"),
           VS("Medio Campidano"),
           SS("Sassari"),
           SV("Savona"),
           SI("Siena"),
           SR("Siracusa"),
           SO("Sondrio"),
           SU("Sud Sardegna"),
           TA("Taranto"),
           TE("Teramo"),
           TR("Terni"),
           TO("Torino"),
           OG("Ogliastra"),
           TP("Trapani"),
           TN("Trento"),
           TV("Treviso"),
           TS("Trieste"),
           UD("Udine"),
           VA("Varese"),
           VE("Venezia"),
           VB("Verbano-Cusio-Ossola"),
           VC("Vercelli"),
           VR("Verona"),
           VV("Vibo Valentia"),
           VI("Vicenza"),
           VT("Viterbo");

        private final String nome;

        Provincia(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

    }
}
