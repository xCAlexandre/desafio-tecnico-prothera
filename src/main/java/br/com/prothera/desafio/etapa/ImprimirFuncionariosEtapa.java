package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.servico.FuncionarioServico;

public class ImprimirFuncionariosEtapa extends EtapaBase {

    public ImprimirFuncionariosEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora) {
        super("3.3 - Imprimir todos os funcionários", servico, impressora);
    }

    @Override
    protected void executarConteudo() {
        impressora.imprimirTabela(servico.listarTodos());
    }
}
