package br.com.prothera.desafio.etapa;

import br.com.prothera.desafio.apresentacao.ImpressoraFuncionarios;
import br.com.prothera.desafio.formatacao.Formatador;
import br.com.prothera.desafio.servico.FuncionarioServico;

import java.math.BigDecimal;
import java.util.Objects;

public class ImprimirSalariosMinimosEtapa extends EtapaBase {

    private final BigDecimal salarioMinimo;

    public ImprimirSalariosMinimosEtapa(FuncionarioServico servico, ImpressoraFuncionarios impressora,
                                        BigDecimal salarioMinimo) {
        super(titulo(salarioMinimo), servico, impressora);
        this.salarioMinimo = salarioMinimo;
    }

    private static String titulo(BigDecimal salarioMinimo) {
        Objects.requireNonNull(salarioMinimo, "O salário mínimo é obrigatório");
        return "3.12 - Imprimir quantos salários mínimos ganha cada funcionário (salário mínimo: R$ "
                + Formatador.numero(salarioMinimo) + ")";
    }

    @Override
    protected void executarConteudo() {
        impressora.imprimirQuantidadeSalariosMinimos(servico.salariosMinimosPorFuncionario(salarioMinimo));
    }
}
