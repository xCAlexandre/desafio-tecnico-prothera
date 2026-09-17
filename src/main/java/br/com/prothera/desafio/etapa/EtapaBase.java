package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.servico.FuncionarioServico;

import java.util.Objects;

public abstract class EtapaBase implements Etapa {

    private final String titulo;
    protected final FuncionarioServico servico;
    protected final ImpressoraFuncionarios impressora;

    protected EtapaBase(String titulo, FuncionarioServico servico, ImpressoraFuncionarios impressora) {
        this.titulo = Objects.requireNonNull(titulo, "O título é obrigatório");
        this.servico = Objects.requireNonNull(servico, "O serviço é obrigatório");
        this.impressora = Objects.requireNonNull(impressora, "A impressora é obrigatória");
    }

    @Override
    public final void executar() {
        impressora.imprimirTitulo(titulo);
        executarConteudo();
    }

    protected abstract void executarConteudo();
}
