package br.com.prothera.desafio.modelo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioTest {

    private Funcionario criarMaria() {
        return Funcionario.builder()
                .nome("Maria")
                .dataNascimento(LocalDate.of(2000, 10, 18))
                .salario(new BigDecimal("2009.44"))
                .funcao("Operador")
                .build();
    }

    @Test
    void deveCriarFuncionarioComTodosOsAtributos() {
        Funcionario maria = criarMaria();

        assertEquals("Maria", maria.getNome());
        assertEquals(LocalDate.of(2000, 10, 18), maria.getDataNascimento());
        assertEquals(new BigDecimal("2009.44"), maria.getSalario());
        assertEquals("Operador", maria.getFuncao());
    }

    @Test
    void deveCalcularIdadeNaDataDeReferencia() {
        Funcionario maria = criarMaria();

        assertEquals(25, maria.idadeEm(LocalDate.of(2026, 10, 17)));
        assertEquals(26, maria.idadeEm(LocalDate.of(2026, 10, 18)));
    }

    @Test
    void deveIdentificarMesDeAniversario() {
        Funcionario maria = criarMaria();

        assertTrue(maria.fazAniversarioEm(Month.OCTOBER));
        assertFalse(maria.fazAniversarioEm(Month.DECEMBER));
    }

    @Test
    void deveAplicarReajusteUsandoAPoliticaInformada() {
        Funcionario maria = criarMaria();

        maria.receberReajuste(salario -> salario.add(BigDecimal.TEN));

        assertEquals(new BigDecimal("2019.44"), maria.getSalario());
    }

    @Test
    void deveCalcularQuantidadeDeSalariosMinimos() {
        Funcionario maria = criarMaria();

        assertEquals(new BigDecimal("1.66"), maria.quantidadeSalariosMinimos(new BigDecimal("1212.00")));
    }

    @Test
    void naoDevePermitirSalarioNegativo() {
        Funcionario.Builder builder = Funcionario.builder()
                .nome("Maria")
                .dataNascimento(LocalDate.of(2000, 10, 18))
                .salario(new BigDecimal("-1"))
                .funcao("Operador");

        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    void naoDevePermitirNomeEmBranco() {
        Funcionario.Builder builder = Funcionario.builder()
                .nome(" ")
                .dataNascimento(LocalDate.of(2000, 10, 18))
                .salario(BigDecimal.ONE)
                .funcao("Operador");

        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    void naoDevePermitirFuncaoAusente() {
        Funcionario.Builder builder = Funcionario.builder()
                .nome("Maria")
                .dataNascimento(LocalDate.of(2000, 10, 18))
                .salario(BigDecimal.ONE);

        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    void naoDevePermitirDataDeNascimentoAusente() {
        Funcionario.Builder builder = Funcionario.builder()
                .nome("Maria")
                .salario(BigDecimal.ONE)
                .funcao("Operador");

        assertThrows(NullPointerException.class, builder::build);
    }
}
