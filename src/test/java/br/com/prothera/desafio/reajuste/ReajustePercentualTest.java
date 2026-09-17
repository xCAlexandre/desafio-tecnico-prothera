package br.com.prothera.desafio.reajuste;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReajustePercentualTest {

    private final ReajustePercentual dezPorCento = new ReajustePercentual(new BigDecimal("10"));

    @Test
    void deveAplicarDezPorCentoArredondandoParaDuasCasas() {
        assertEquals(new BigDecimal("21031.87"), dezPorCento.aplicar(new BigDecimal("19119.88")));
        assertEquals(new BigDecimal("3319.20"), dezPorCento.aplicar(new BigDecimal("3017.45")));
    }

    @Test
    void deveManterSalarioQuandoPercentualForZero() {
        ReajustePercentual semReajuste = new ReajustePercentual(BigDecimal.ZERO);

        assertEquals(new BigDecimal("2009.44"), semReajuste.aplicar(new BigDecimal("2009.44")));
    }

    @Test
    void deveDescreverOPercentualAplicado() {
        assertEquals("aumento de 10%", dezPorCento.descricao());
    }

    @Test
    void naoDevePermitirPercentualNegativo() {
        BigDecimal negativo = new BigDecimal("-5");

        assertThrows(IllegalArgumentException.class, () -> new ReajustePercentual(negativo));
    }
}
