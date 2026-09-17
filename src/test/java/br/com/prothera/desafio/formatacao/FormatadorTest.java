package br.com.prothera.desafio.formatacao;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatadorTest {

    @Test
    void deveFormatarDataNoPadraoBrasileiro() {
        assertEquals("02/05/1961", Formatador.data(LocalDate.of(1961, 5, 2)));
    }

    @Test
    void deveFormatarNumeroComPontoNoMilharEVirgulaNoDecimal() {
        assertEquals("19.119,88", Formatador.numero(new BigDecimal("19119.88")));
        assertEquals("1.234.567,89", Formatador.numero(new BigDecimal("1234567.89")));
    }

    @Test
    void deveFormatarNumeroMenorQueMilComDuasCasasDecimais() {
        assertEquals("0,50", Formatador.numero(new BigDecimal("0.5")));
        assertEquals("999,00", Formatador.numero(new BigDecimal("999")));
    }
}
