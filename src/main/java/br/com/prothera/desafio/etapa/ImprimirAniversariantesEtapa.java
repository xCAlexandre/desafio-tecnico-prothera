package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.servico.FuncionarioServico;

import java.time.Month;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ImprimirAniversariantesEtapa extends EtapaBase {

    private final Set<Month> meses;

    public ImprimirAniversariantesEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora,
                                        Set<Month> meses) {
        super(titulo(meses), servico, impressora);
        this.meses = EnumSet.copyOf(meses);
    }

    private static String titulo(Set<Month> meses) {
        Objects.requireNonNull(meses, "Os meses são obrigatórios");
        if (meses.isEmpty()) {
            throw new IllegalArgumentException("Informe ao menos um mês");
        }
        String numerosDosMeses = EnumSet.copyOf(meses).stream()
                .map(mes -> String.valueOf(mes.getValue()))
                .collect(Collectors.joining(" e "));
        return "3.8 - Imprimir os funcionários que fazem aniversário nos meses " + numerosDosMeses;
    }

    @Override
    protected void executarConteudo() {
        impressora.imprimirTabela(servico.aniversariantesNosMeses(meses));
    }
}
