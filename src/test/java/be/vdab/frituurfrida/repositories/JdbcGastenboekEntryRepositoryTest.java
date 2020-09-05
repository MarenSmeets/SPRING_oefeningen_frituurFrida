package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.GastenboekEntry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;

@JdbcTest
@Import(JdbcGastenboekEntryRepository.class)
@Sql("/insertGastenboekEntries.sql")
class JdbcGastenboekEntryRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final GastenboekEntryRepository repository;
    private final static String GB_ENTRIES = "gbEntries";

    JdbcGastenboekEntryRepositoryTest(GastenboekEntryRepository repository) {
        this.repository = repository;
    }

    @Test
    void create(){
        var entry = new GastenboekEntry(0, "test3", "test test test", LocalDate.now());
        var id = repository.create(entry);
//        assertThat(id).isEqualTo(3);
        assertThat(id).isPositive();
        assertThat(super.countRowsInTableWhere(GB_ENTRIES, "id=" + id)).isOne();

    }

    @Test
    void findAll(){
//        assertThat(repository.findAll().size()).isEqualTo(2);
        assertThat(repository.findAll())
                .hasSize(super.countRowsInTable(GB_ENTRIES))
                .extracting(entry->entry.getTijdstip())
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void delete(){
        long id = idVanTestEntry();
        repository.delete(id);
        assertThat(super.countRowsInTableWhere(GB_ENTRIES, "id="+ id)).isZero();
    }

    private long idVanTestEntry() {
        return super.jdbcTemplate.queryForObject(
                "select id from gbEntries where naam='test1'", Long.class);
    }

}