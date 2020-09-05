package be.vdab.frituurfrida.services;

import be.vdab.frituurfrida.domain.Saus;
import be.vdab.frituurfrida.repositories.SausRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultSausServiceTest {

    @Mock
    private SausRepository sausRepository;
    private DefaultSausService sausService;

    @BeforeEach
    void beforeEach(){
        sausService = new DefaultSausService(sausRepository);
    }

    @Test
    void findAll(){
        var sauzenLijst = new ArrayList<Saus>();
        sauzenLijst.add(new Saus(1, "cocktail", new String[]{"mayonaise", "ketchup"}));
        when(sausRepository.findAll()).thenReturn(sauzenLijst);
        assertThat(sausRepository.findAll().stream().findFirst()).isEqualTo(Optional.of(sauzenLijst.get(0)));
        verify(sausRepository).findAll();
    }


}