package br.com.prothera.desafio.reajuste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ReajustePercentual implements PoliticaReajuste {

    private static final int ESCALA_MONETARIA = 2;
    private static final int CASAS_PERCENTUAIS = 2;

    private final BigDecimal percentual;

    public ReajustePercentual(BigDecimal percentual) {
        Objects.requireNonNull(percentual, "O percentual é obrigatório");
        if (percentual.signum() < 0) {
            throw new IllegalArgumentException("O percentual de reajuste não pode ser negativo");
        }
        this.percentual = percentual;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    @Override
    public String descricao() {
        return "aumento de " + percentual.stripTrailingZeros().toPlainString() + "%";
    }

    @Override
    public BigDecimal aplicar(BigDecimal salarioAtual) {
        Objects.requireNonNull(salarioAtual, "O salário atual é obrigatório");
        BigDecimal fator = BigDecimal.ONE.add(percentual.movePointLeft(CASAS_PERCENTUAIS));
        return salarioAtual.multiply(fator).setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);
    }
}
