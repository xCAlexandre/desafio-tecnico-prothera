package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.modelo.Funcionario;
import br.com.prothera.desafio.servico.FuncionarioServico;

import java.util.List;
import java.util.Map;

public class AgruparPorFuncaoEtapa extends EtapaBase {

    private Map<String, List<Funcionario>> funcionariosPorFuncao = Map.of();

    public AgruparPorFuncaoEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora) {
        super("3.5 - Agrupar os funcionários por função", servico, impressora);
    }

    @Override
    protected void executarConteudo() {
        funcionariosPorFuncao = servico.agruparPorFuncao();
        impressora.imprimirMensagem("Funcionários agrupados em " + funcionariosPorFuncao.size() + " funções.");
    }

    public Map<String, List<Funcionario>> getFuncionariosPorFuncao() {
        return funcionariosPorFuncao;
    }
}
