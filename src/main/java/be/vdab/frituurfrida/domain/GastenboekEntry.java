package be.vdab.frituurfrida.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class GastenboekEntry {

    private final long id;
    @NotBlank
    private final String naam;
    @NotBlank
    private final String boodschap;
    @NotNull @DateTimeFormat(style = "S-")
    private final LocalDate tijdstip;


    public GastenboekEntry(long id, String naam, String boodschap, LocalDate tijdstip) {
        this.id = id;
        this.naam = naam;
        this.boodschap = boodschap;
        this.tijdstip = tijdstip;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getBoodschap() {
        return boodschap;
    }

    public LocalDate getTijdstip() {
        return tijdstip;
    }

    @Override
    public String toString() {
        return id + " ; " + naam ;
    }
}
