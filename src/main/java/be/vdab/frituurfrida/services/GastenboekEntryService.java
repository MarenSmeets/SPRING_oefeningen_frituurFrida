package be.vdab.frituurfrida.services;


import be.vdab.frituurfrida.domain.GastenboekEntry;

import java.util.List;

public interface GastenboekEntryService {
    long create(GastenboekEntry entry);
    List<GastenboekEntry> findAll();
    void delete(long[] ids);
}
