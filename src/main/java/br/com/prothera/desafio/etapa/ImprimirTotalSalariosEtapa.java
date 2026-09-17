package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.formatacao.Formatador;
import br.com.prothera.desafio.servico.FuncionarioServico;

public class ImprimirTotalSalariosEtapa extends EtapaBase {

    public ImprimirTotalSalariosEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora) {
        super("3.11 - Imprimir o total dos salários dos funcionários", servico, impressora);
    }

    @Override
    protected void executarConteudo() {
        impressora.imprimirMensagem("Total dos salários: R$ " + Formatador.numero(servico.totalSalarios()));
    }
}
