package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.servico.FuncionarioServico;

import java.util.Objects;

public class ImprimirAgrupadosPorFuncaoEtapa extends EtapaBase {

    private final AgruparPorFuncaoEtapa agrupamento;

    public ImprimirAgrupadosPorFuncaoEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora,
                                           AgruparPorFuncaoEtapa agrupamento) {
        super("3.6 - Imprimir os funcionários agrupados por função", servico, impressora);
        this.agrupamento = Objects.requireNonNull(agrupamento, "A etapa de agrupamento é obrigatória");
    }

    @Override
    protected void executarConteudo() {
        agrupamento.getFuncionariosPorFuncao().forEach((funcao, funcionarios) -> {
            impressora.imprimirSubtitulo(funcao);
            impressora.imprimirTabela(funcionarios);
        });
    }
}
