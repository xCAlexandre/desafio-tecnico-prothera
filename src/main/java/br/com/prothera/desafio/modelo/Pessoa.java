package br.com.prothera.desafio.modelo;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Objects;

public abstract class Pessoa {

    private final String nome;
    private final LocalDate dataNascimento;

    protected Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = validarNome(nome);
        this.dataNascimento = Objects.requireNonNull(dataNascimento, "A data de nascimento é obrigatória");
    }

    private static String validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório");
        }
        return nome.trim();
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int idadeEm(LocalDate referencia) {
        Objects.requireNonNull(referencia, "A data de referência é obrigatória");
        if (referencia.isBefore(dataNascimento)) {
            throw new IllegalArgumentException("A data de referência não pode ser anterior ao nascimento");
        }
        return Period.between(dataNascimento, referencia).getYears();
    }

    public boolean fazAniversarioEm(Month mes) {
        return dataNascimento.getMonth() == mes;
    }

    public boolean possuiNome(String nome) {
        return this.nome.equalsIgnoreCase(nome);
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) {
            return true;
        }
        if (outro == null || getClass() != outro.getClass()) {
            return false;
        }
        Pessoa pessoa = (Pessoa) outro;
        return nome.equals(pessoa.nome) && dataNascimento.equals(pessoa.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataNascimento);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{nome='" + nome + "', dataNascimento=" + dataNascimento + "}";
    }
}
