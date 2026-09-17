package br.com.prothera.desafio.modelo;

import br.com.prothera.desafio.reajuste.PoliticaReajuste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class Funcionario extends Pessoa {

    private static final int ESCALA_MONETARIA = 2;

    private BigDecimal salario;
    private final String funcao;

    private Funcionario(Builder builder) {
        super(builder.nome, builder.dataNascimento);
        this.salario = validarSalario(builder.salario);
        this.funcao = validarFuncao(builder.funcao);
    }

    public static Builder builder() {
        return new Builder();
    }

    private static BigDecimal validarSalario(BigDecimal salario) {
        Objects.requireNonNull(salario, "O salário é obrigatório");
        if (salario.signum() < 0) {
            throw new IllegalArgumentException("O salário não pode ser negativo");
        }
        return salario.setScale(ESCALA_MONETARIA, RoundingMode.HALF_UP);
    }

    private static String validarFuncao(String funcao) {
        if (funcao == null || funcao.isBlank()) {
            throw new IllegalArgumentException("A função é obrigatória");
        }
        return funcao.trim();
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void receberReajuste(PoliticaReajuste politica) {
        Objects.requireNonNull(politica, "A política de reajuste é obrigatória");
        this.salario = validarSalario(politica.aplicar(salario));
    }

    public BigDecimal quantidadeSalariosMinimos(BigDecimal salarioMinimo) {
        Objects.requireNonNull(salarioMinimo, "O salário mínimo é obrigatório");
        if (salarioMinimo.signum() <= 0) {
            throw new IllegalArgumentException("O salário mínimo deve ser maior que zero");
        }
        return salario.divide(salarioMinimo, ESCALA_MONETARIA, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object outro) {
        if (!super.equals(outro)) {
            return false;
        }
        return funcao.equals(((Funcionario) outro).funcao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), funcao);
    }

    @Override
    public String toString() {
        return "Funcionario{nome='" + getNome() + "', dataNascimento=" + getDataNascimento()
                + ", salario=" + salario + ", funcao='" + funcao + "'}";
    }

    public static final class Builder {

        private String nome;
        private LocalDate dataNascimento;
        private BigDecimal salario;
        private String funcao;

        private Builder() {
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder dataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Builder salario(BigDecimal salario) {
            this.salario = salario;
            return this;
        }

        public Builder funcao(String funcao) {
            this.funcao = funcao;
            return this;
        }

        public Funcionario build() {
            return new Funcionario(this);
        }
    }
}
