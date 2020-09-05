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
@Qualifier("CSVSaus")
public class CSVSausRepository implements SausRepository {

    private final Path pad;

    public CSVSausRepository(@Value("${sausCSV}") Path pad) {
        this.pad = pad;
    }

    @Override
    public List<Saus> findAll() {
//        var sauzenLijst = new ArrayList<Saus>();

        try {
            return Files.lines(pad).filter(regel -> !regel.isEmpty()).map(this::maakSaus)
                    .collect(Collectors.toList());
        }catch (IOException ex) {
//            ex.printStackTrace();
            throw new SausRepositoryException("Fout bij lezen " + pad);
        }
    }

    private Saus maakSaus(String regel) {
        var sausGegevens = regel.split(",");
        if (sausGegevens.length < 2){
            throw new SausRepositoryException(pad + ":" + regel + " bevat minder dan 2 onderdelen");
        }
        try {
            var ingredienten = Arrays.copyOfRange(sausGegevens, 2, sausGegevens.length);
            return new Saus(Long.parseLong(sausGegevens[0]), sausGegevens[1], ingredienten);
        } catch (NumberFormatException ex) {
            throw new SausRepositoryException(pad + " : " + regel + " bevat verkeerde id");
        }
    }
}
