package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.servico.FuncionarioServico;

public class ImprimirOrdemAlfabeticaEtapa extends EtapaBase {

    public ImprimirOrdemAlfabeticaEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora) {
        super("3.10 - Imprimir a lista de funcionários por ordem alfabética", servico, impressora);
    }

    @Override
    protected void executarConteudo() {
        impressora.imprimirTabela(servico.ordenadosPorNome());
    }
}
