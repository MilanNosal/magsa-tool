package sk.tuke.magsa.personalistika.entity;

import sk.tuke.magsa.framework.Entity;

public class Zamestnanec extends Entity {
    private static final long serialVersionUID = 1L;

    private String meno;

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    private String priezvisko;

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    private Integer vek;

    public Integer getVek() {
        return vek;
    }

    public void setVek(Integer vek) {
        this.vek = vek;
    }

}
