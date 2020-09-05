package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.GastenboekEntry;

import java.util.List;

public interface GastenboekEntryRepository {

    long create(GastenboekEntry entry);
    List<GastenboekEntry> findAll();
    void delete(long id);
}
