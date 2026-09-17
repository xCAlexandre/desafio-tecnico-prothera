package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.servico.FuncionarioServico;

import java.util.Objects;

public class RemoverFuncionarioEtapa extends EtapaBase {

    private final String nome;

    public RemoverFuncionarioEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora, String nome) {
        super("3.2 - Remover o funcionário \"" + nome + "\" da lista", servico, impressora);
        this.nome = Objects.requireNonNull(nome, "O nome é obrigatório");
    }

    @Override
    protected void executarConteudo() {
        if (servico.removerPorNome(nome)) {
            impressora.imprimirMensagem("Funcionário " + nome + " removido.");
        } else {
            impressora.imprimirMensagem("Funcionário " + nome + " não encontrado.");
        }
    }
}
