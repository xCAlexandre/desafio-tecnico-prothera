package br.com.prothera.desafio.reajuste;

import java.math.BigDecimal;

@FunctionalInterface
public interface PoliticaReajuste {

    BigDecimal aplicar(BigDecimal salarioAtual);

    default String descricao() {
        return "reajuste salarial";
    }
}
