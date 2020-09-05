package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.exceptions.SausRepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("propertiesSaus")

public class PropertiesSausRepository implements SausRepository {

    private final Path pad;

    public PropertiesSausRepository(@Value("${sausProperties}") Path pad) {
        this.pad = pad;
    }

    @Override
    public List<Saus> findAll() {

        try {
            return Files.lines(pad).filter(regel -> !regel.isEmpty())
                    .map(this::maakSaus).collect(Collectors.toList());
        } catch (IOException ex) {
            throw new SausRepositoryException("Fout bij het lezen : " + pad);
        }
    }

    private Saus maakSaus(String regel) {
        var onderdelen = regel.split(":");
        if (onderdelen.length < 2){
            throw new SausRepositoryException(pad + ":" + regel + " bevat minder dan 2 onderdelen");
        }
        try {
            var sausGegevens = onderdelen[1].split(",");
            var ingredienten = Arrays.copyOfRange(sausGegevens, 1, sausGegevens.length);
            return new Saus(Long.parseLong(onderdelen[0]), sausGegevens[0], ingredienten);
        } catch (NumberFormatException ex) {
            throw new SausRepositoryException(pad + " : " + regel + " bevat verkeerde id");
        }
    }
}
