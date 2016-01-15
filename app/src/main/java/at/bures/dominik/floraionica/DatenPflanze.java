package at.bures.dominik.floraionica;

import java.util.Date;

/**
 * Created by Dominik on 18.12.15.
 */
public class DatenPflanze {

    //Fundpunkt:

    String FundpunktNr;
    Date Datum;
    String Insel;
    String Lokalitaet;
    String KmFeld;
    String Habitat;
    String Beobachter;


    // Bild speichern LOL
    // Beobachtungen:

    //Position speichern N:123 W123 Genauigkeit: 10m
    String Taxon;
    String Bezirk;
    String Herbar;
    String PalDat;
    String KulturNr;
    String Status;
    String Habitus;
    String Anmerkungen;


    public DatenPflanze() {


    }

    public DatenPflanze(String fundpunktNr, Date datum, String insel, String lokalitaet, String kmFeld, String habitat, String beobachter, String taxon, String bezirk, String herbar, String palDat, String kulturNr, String status, String habitus, String anmerkungen) {
        FundpunktNr = fundpunktNr;
        Datum = datum;
        Insel = insel;
        Lokalitaet = lokalitaet;
        KmFeld = kmFeld;
        Habitat = habitat;
        Beobachter = beobachter;
        Taxon = taxon;
        Bezirk = bezirk;
        Herbar = herbar;
        PalDat = palDat;
        KulturNr = kulturNr;
        Status = status;
        Habitus = habitus;
        Anmerkungen = anmerkungen;
    }


    public String getFundpunktNr() {
        return FundpunktNr;
    }

    public void setFundpunktNr(String fundpunktNr) {
        FundpunktNr = fundpunktNr;
    }

    public Date getDatum() {
        return Datum;
    }
    // return aneresDate format

    public void setDatum(Date datum) {
        Datum = datum;
    }

    public String getInsel() {
        return Insel;
    }

    public void setInsel(String insel) {
        Insel = insel;
    }

    public String getLokalitaet() {
        return Lokalitaet;
    }

    public void setLokalitaet(String lokalitaet) {
        Lokalitaet = lokalitaet;
    }

    public String getKmFeld() {
        return KmFeld;
    }

    public void setKmFeld(String kmFeld) {
        KmFeld = kmFeld;
    }

    public String getHabitat() {
        return Habitat;
    }

    public void setHabitat(String habitat) {
        Habitat = habitat;
    }

    public String getBeobachter() {
        return Beobachter;
    }

    public void setBeobachter(String beobachter) {
        Beobachter = beobachter;
    }

    public String getTaxon() {
        return Taxon;
    }

    public void setTaxon(String taxon) {
        Taxon = taxon;
    }

    public String getBezirk() {
        return Bezirk;
    }

    public void setBezirk(String bezirk) {
        Bezirk = bezirk;
    }

    public String getHerbar() {
        return Herbar;
    }

    public void setHerbar(String herbar) {
        Herbar = herbar;
    }

    public String getPalDat() {
        return PalDat;
    }

    public void setPalDat(String palDat) {
        PalDat = palDat;
    }

    public String getKulturNr() {
        return KulturNr;
    }

    public void setKulturNr(String kulturNr) {
        KulturNr = kulturNr;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getHabitus() {
        return Habitus;
    }

    public void setHabitus(String habitus) {
        Habitus = habitus;
    }

    public String getAnmerkungen() {
        return Anmerkungen;
    }

    public void setAnmerkungen(String anmerkungen) {
        Anmerkungen = anmerkungen;
    }


}
