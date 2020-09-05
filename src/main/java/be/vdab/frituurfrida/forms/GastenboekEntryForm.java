package be.vdab.frituurfrida.forms;

import be.vdab.frituurfrida.domain.GastenboekEntry;

import java.time.LocalDate;

public class GastenboekEntryForm extends GastenboekEntry {

    public GastenboekEntryForm(String naam, String boodschap){
        super(0, naam, boodschap, LocalDate.now());
    }
}
