package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.GastenboekEntry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Repository
class JdbcGastenboekEntryRepository implements GastenboekEntryRepository {

    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final RowMapper<GastenboekEntry> entryRowMapper =
            (result, rowNum) -> new GastenboekEntry(
                    result.getLong("id"),
                    result.getString("naam"),
                    result.getString("boodschap"),
                    result.getDate("tijdstip").toLocalDate()
            );

    public JdbcGastenboekEntryRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template)
                        .withTableName("gbEntries")
                        .usingGeneratedKeyColumns("id");
    }

    @Override
    public long create(GastenboekEntry entry) {
        var values = Map.of(
                "naam", entry.getNaam(),
                "boodschap", entry.getBoodschap(),
                "tijdstip", entry.getTijdstip()
        );
        var id = insert.executeAndReturnKey(values);
        return id.longValue();
    }

    @Override
    public List<GastenboekEntry> findAll() {
        var sql ="select id, naam, boodschap, tijdstip from gbEntries order by tijdstip desc";
        return template.query(sql, entryRowMapper);
    }

    @Override
    public void delete(long id) {
        template.update("delete from gbEntries where id = ?", id);
    }
}
