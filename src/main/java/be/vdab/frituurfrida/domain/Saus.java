package be.vdab.frituurfrida.domain;

import java.util.Arrays;

public class Saus {

    private long nummer;
    private String naam;
    private String[] ingredienten;

    public Saus(long nummer, String naam, String[] ingredienten) {
        this.nummer = nummer;
        this.naam = naam;
        this.ingredienten = ingredienten;
    }

    public long getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String[] getIngredienten() {
        return ingredienten;
    }

    @Override
    public String toString() {
        return nummer + " , " + naam + " , " + Arrays.toString(ingredienten);
    }
}
