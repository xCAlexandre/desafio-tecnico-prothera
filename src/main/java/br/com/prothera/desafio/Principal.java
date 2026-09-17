package br.com.prothera.desafio;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.dados.CargaInicialFuncionarios;
import br.com.prothera.desafio.etapa.AgruparPorFuncaoEtapa;
import br.com.prothera.desafio.etapa.AplicarReajusteEtapa;
import br.com.prothera.desafio.etapa.Etapa;
import br.com.prothera.desafio.etapa.ImprimirAgrupadosPorFuncaoEtapa;
import br.com.prothera.desafio.etapa.ImprimirAniversariantesEtapa;
import br.com.prothera.desafio.etapa.ImprimirFuncionarioMaisVelhoEtapa;
import br.com.prothera.desafio.etapa.ImprimirFuncionariosEtapa;
import br.com.prothera.desafio.etapa.ImprimirOrdemAlfabeticaEtapa;
import br.com.prothera.desafio.etapa.ImprimirSalariosMinimosEtapa;
import br.com.prothera.desafio.etapa.ImprimirTotalSalariosEtapa;
import br.com.prothera.desafio.etapa.InserirFuncionariosEtapa;
import br.com.prothera.desafio.etapa.RemoverFuncionarioEtapa;
import br.com.prothera.desafio.reajuste.ReajustePercentual;
import br.com.prothera.desafio.repositorio.FuncionarioRepositorioEmMemoria;
import br.com.prothera.desafio.servico.FuncionarioServico;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Month;
import java.util.List;
import java.util.Set;

public class Principal {

    private static final String FUNCIONARIO_REMOVIDO = "João";
    private static final BigDecimal PERCENTUAL_AUMENTO = new BigDecimal("10");
    private static final Set<Month> MESES_ANIVERSARIO = Set.of(Month.OCTOBER, Month.DECEMBER);
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        FuncionarioServico servico = new FuncionarioServico(new FuncionarioRepositorioEmMemoria(), Clock.systemDefaultZone());
        ImpressoraFuncionarios impressora = new ImpressoraFuncionarios(System.out);

        criarEtapas(servico, impressora).forEach(Etapa::executar);
    }

    private static List<Etapa> criarEtapas(FuncionarioServico servico, ImpressoraFuncionarios impressora) {
        AgruparPorFuncaoEtapa agruparPorFuncao = new AgruparPorFuncaoEtapa(servico, impressora);

        return List.of(
                new InserirFuncionariosEtapa(servico, impressora, new CargaInicialFuncionarios()),
                new RemoverFuncionarioEtapa(servico, impressora, FUNCIONARIO_REMOVIDO),
                new ImprimirFuncionariosEtapa(servico, impressora),
                new AplicarReajusteEtapa(servico, impressora, new ReajustePercentual(PERCENTUAL_AUMENTO)),
                agruparPorFuncao,
                new ImprimirAgrupadosPorFuncaoEtapa(servico, impressora, agruparPorFuncao),
                new ImprimirAniversariantesEtapa(servico, impressora, MESES_ANIVERSARIO),
                new ImprimirFuncionarioMaisVelhoEtapa(servico, impressora),
                new ImprimirOrdemAlfabeticaEtapa(servico, impressora),
                new ImprimirTotalSalariosEtapa(servico, impressora),
                new ImprimirSalariosMinimosEtapa(servico, impressora, SALARIO_MINIMO)
        );
    }
}
