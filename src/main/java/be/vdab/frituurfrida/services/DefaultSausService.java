package be.vdab.frituurfrida.services;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.repositories.SausRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DefaultSausService implements SausService {

    private final SausRepository repository;

    public DefaultSausService(@Qualifier("propertiesSaus") SausRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Saus> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Saus> findByNaamBegintMet(char letter) {
//        var lijstMetDezeLetter = new ArrayList<Saus>();
//        repository.findAll().stream().filter(saus -> saus.getNaam().charAt(0) == letter)
//                .forEach(lijstMetDezeLetter::add);
//        return lijstMetDezeLetter;
        return repository.findAll().stream().filter(saus -> saus.getNaam().charAt(0) == letter)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Saus> findByNummer(long nummer) {
        return repository.findAll().stream().filter(saus -> saus.getNummer()==nummer).findFirst();
    }
}
