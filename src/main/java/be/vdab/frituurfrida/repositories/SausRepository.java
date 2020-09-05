package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.Saus;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface SausRepository {

    List<Saus> findAll();
}
