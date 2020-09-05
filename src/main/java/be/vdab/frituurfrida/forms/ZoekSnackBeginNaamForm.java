package be.vdab.frituurfrida.forms;

import javax.validation.constraints.NotBlank;

public class ZoekSnackBeginNaamForm {

    @NotBlank
    private final String beginNaam;

    public ZoekSnackBeginNaamForm(String beginNaam) {
        this.beginNaam = beginNaam;
    }

    public String getBeginNaam() {
        return beginNaam;
    }
}
