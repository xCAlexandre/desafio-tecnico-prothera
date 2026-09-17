package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.reajuste.PoliticaReajuste;
import br.com.prothera.desafio.servico.FuncionarioServico;

import java.util.Objects;

public class AplicarReajusteEtapa extends EtapaBase {

    private final PoliticaReajuste politica;

    public AplicarReajusteEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora,
                                PoliticaReajuste politica) {
        super(titulo(politica), servico, impressora);
        this.politica = politica;
    }

    private static String titulo(PoliticaReajuste politica) {
        Objects.requireNonNull(politica, "A política de reajuste é obrigatória");
        return "3.4 - Aplicar " + politica.descricao() + " nos salários";
    }

    @Override
    protected void executarConteudo() {
        servico.aplicarReajuste(politica);
        impressora.imprimirMensagem("Salários atualizados:");
        impressora.imprimirTabela(servico.listarTodos());
    }
}
