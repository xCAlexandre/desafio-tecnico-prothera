package br.com.prothera.desafio.servico;

import br.com.prothera.desafio.dados.CargaInicialFuncionarios;
import br.com.prothera.desafio.modelo.Funcionario;
import br.com.prothera.desafio.reajuste.ReajustePercentual;
import br.com.prothera.desafio.repositorio.FuncionarioRepositorioEmMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioServicoTest {

    private static final Clock RELOGIO_FIXO = Clock.fixed(Instant.parse("2026-09-17T12:00:00Z"), ZoneOffset.UTC);

    private FuncionarioServico servico;

    @BeforeEach
    void prepararCenario() {
        servico = new FuncionarioServico(new FuncionarioRepositorioEmMemoria(), RELOGIO_FIXO);
        servico.cadastrar(new CargaInicialFuncionarios().get());
    }

    private static List<String> nomes(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(Funcionario::getNome).toList();
    }

    @Test
    void deveCadastrarFuncionariosMantendoAOrdemDeInsercao() {
        assertEquals(
                List.of("Maria", "João", "Caio", "Miguel", "Alice", "Heitor", "Arthur", "Laura", "Heloísa", "Helena"),
                nomes(servico.listarTodos()));
    }

    @Test
    void deveRemoverFuncionarioPeloNome() {
        assertTrue(servico.removerPorNome("João"));

        assertEquals(9, servico.listarTodos().size());
        assertFalse(nomes(servico.listarTodos()).contains("João"));
        assertFalse(servico.removerPorNome("João"));
    }

    @Test
    void deveAplicarReajusteEmTodosOsFuncionarios() {
        servico.removerPorNome("João");

        servico.aplicarReajuste(new ReajustePercentual(new BigDecimal("10")));

        assertEquals(new BigDecimal("21031.87"), servico.listarTodos().get(2).getSalario());
        assertEquals(new BigDecimal("50906.82"), servico.totalSalarios());
    }

    @Test
    void deveAgruparFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> agrupados = servico.agruparPorFuncao();

        assertEquals(
                List.of("Contador", "Coordenador", "Diretor", "Eletricista", "Gerente", "Operador", "Recepcionista"),
                List.copyOf(agrupados.keySet()));
        assertEquals(List.of("Maria", "João", "Heitor"), nomes(agrupados.get("Operador")));
        assertEquals(List.of("Laura", "Helena"), nomes(agrupados.get("Gerente")));
    }

    @Test
    void deveListarAniversariantesDosMesesInformados() {
        List<Funcionario> aniversariantes = servico.aniversariantesNosMeses(Set.of(Month.OCTOBER, Month.DECEMBER));

        assertEquals(List.of("Maria", "Miguel"), nomes(aniversariantes));
    }

    @Test
    void deveEncontrarFuncionarioComMaiorIdade() {
        Funcionario maisVelho = servico.maisVelho().orElseThrow();

        assertEquals("Caio", maisVelho.getNome());
        assertEquals(65, servico.idadeDe(maisVelho));
    }

    @Test
    void deveOrdenarFuncionariosPorNomeConsiderandoAcentos() {
        assertEquals(
                List.of("Alice", "Arthur", "Caio", "Heitor", "Helena", "Heloísa", "João", "Laura", "Maria", "Miguel"),
                nomes(servico.ordenadosPorNome()));
    }

    @Test
    void deveSomarOsSalarios() {
        assertEquals(new BigDecimal("48563.31"), servico.totalSalarios());
    }

    @Test
    void deveCalcularQuantosSalariosMinimosCadaFuncionarioGanha() {
        Map<Funcionario, BigDecimal> salariosMinimos =
                servico.salariosMinimosPorFuncionario(new BigDecimal("1212.00"));

        List<Funcionario> funcionarios = servico.listarTodos();
        assertEquals(funcionarios, List.copyOf(salariosMinimos.keySet()));
        assertEquals(new BigDecimal("1.66"), salariosMinimos.get(funcionarios.get(0)));
        assertEquals(new BigDecimal("15.78"), salariosMinimos.get(funcionarios.get(3)));
    }

    @Test
    void deveTratarListaVazia() {
        FuncionarioServico servicoVazio = new FuncionarioServico(new FuncionarioRepositorioEmMemoria(), RELOGIO_FIXO);

        assertTrue(servicoVazio.maisVelho().isEmpty());
        assertEquals(BigDecimal.ZERO, servicoVazio.totalSalarios());
        assertTrue(servicoVazio.agruparPorFuncao().isEmpty());
    }
}
