package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.modelo.Funcionario;
import br.com.prothera.desafio.servico.FuncionarioServico;

public class ImprimirFuncionarioMaisVelhoEtapa extends EtapaBase {

    public ImprimirFuncionarioMaisVelhoEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora) {
        super("3.9 - Imprimir o funcionário com a maior idade", servico, impressora);
    }

    @Override
    protected void executarConteudo() {
        servico.maisVelho().ifPresentOrElse(
                this::imprimirNomeEIdade,
                () -> impressora.imprimirMensagem("Nenhum funcionário encontrado."));
    }

    private void imprimirNomeEIdade(Funcionario funcionario) {
        impressora.imprimirMensagem("Nome: " + funcionario.getNome());
        impressora.imprimirMensagem("Idade: " + servico.idadeDe(funcionario) + " anos");
    }
}
