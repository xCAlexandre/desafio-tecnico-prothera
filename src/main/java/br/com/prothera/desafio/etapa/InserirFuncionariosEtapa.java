package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.modelo.Funcionario;
import br.com.prothera.desafio.servico.FuncionarioServico;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class InserirFuncionariosEtapa extends EtapaBase {

    private final Supplier<List<Funcionario>> fonteDeDados;

    public InserirFuncionariosEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora,
                                    Supplier<List<Funcionario>> fonteDeDados) {
        super("3.1 - Inserir todos os funcionários", servico, impressora);
        this.fonteDeDados = Objects.requireNonNull(fonteDeDados, "A fonte de dados é obrigatória");
    }

    @Override
    protected void executarConteudo() {
        List<Funcionario> funcionarios = fonteDeDados.get();
        servico.cadastrar(funcionarios);
        impressora.imprimirMensagem(funcionarios.size() + " funcionários inseridos.");
    }
}
