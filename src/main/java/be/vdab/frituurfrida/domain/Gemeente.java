package be.vdab.frituurfrida.domain;

public class Gemeente {

    private final String naam;
    private final int postCode;

    public Gemeente( int postCode, String naam) {
        this.naam = naam;
        this.postCode = postCode;
    }

    public String getNaam() {
        return naam;
    }

    public int getPostCode() {
        return postCode;
    }
}
