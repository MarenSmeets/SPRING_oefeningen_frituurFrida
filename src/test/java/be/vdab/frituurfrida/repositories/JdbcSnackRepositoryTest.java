package be.vdab.frituurfrida.repositories;

import be.vdab.frituurfrida.domain.Snack;
import be.vdab.frituurfrida.exceptions.SnackNietGevondenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(JdbcSnackRepository.class)
@Sql("/insertSnacks.sql")

class JdbcSnackRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final JdbcSnackRepository repository;

    private long idVanTestSnack() {
        return super.jdbcTemplate.queryForObject(
                "select id from snacks where naam='test1'", Long.class
        );
    }

    JdbcSnackRepositoryTest(JdbcSnackRepository repository) {
        this.repository = repository;
    }

    @Test
    void update() {
        var id = idVanTestSnack();
        var snack = new Snack(id, "test", BigDecimal.ONE);
        repository.update(snack);
        assertThat(super.jdbcTemplate.queryForObject(
                "select prijs from snacks where id =?", BigDecimal.class, id)).isOne();
    }

    @Test
    void updateOnbestaandeSnack(){
        var snack = new Snack(-1, "test-1", BigDecimal.valueOf(-1));
        assertThatExceptionOfType(SnackNietGevondenException.class).isThrownBy(
                ()-> repository.update(snack));
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestSnack()).get().getNaam()).isEqualTo("test1");
    }

    @Test
    void findByIdOnbestaandeSnack(){
        assertThat(repository.findById(-2)).isNotPresent();
    }

    @Test
    void findByBeginNaam() {
        assertThat(repository.findByBeginNaam("t"))
                .hasSize(super.jdbcTemplate.queryForObject(
                        "select count(*) from snacks where naam like 't%'", Integer.class
                ))
                .extracting(snack -> snack.getNaam().toLowerCase())
                .allSatisfy(naam -> assertThat(naam.startsWith("t")))
                .isSorted();
    }
}