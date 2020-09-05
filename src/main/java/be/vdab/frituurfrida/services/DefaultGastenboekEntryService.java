package be.vdab.frituurfrida.services;

import be.vdab.frituurfrida.domain.GastenboekEntry;
import be.vdab.frituurfrida.repositories.GastenboekEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
class DefaultGastenboekEntryService implements GastenboekEntryService {

    private final GastenboekEntryRepository repository;

    public DefaultGastenboekEntryService(GastenboekEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public long create(GastenboekEntry entry) {
        return repository.create(entry);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastenboekEntry> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(long[] ids) {
        Arrays.stream(ids).forEach(
                id -> repository.delete(id)
        );
    }
}
