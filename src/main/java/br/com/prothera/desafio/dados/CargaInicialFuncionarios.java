package br.com.prothera.desafio.dados;

import br.com.prothera.desafio.modelo.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

public class CargaInicialFuncionarios implements Supplier<List<Funcionario>> {

    @Override
    public List<Funcionario> get() {
        return List.of(
                funcionario("Maria", LocalDate.of(2000, 10, 18), "2009.44", "Operador"),
                funcionario("João", LocalDate.of(1990, 5, 12), "2284.38", "Operador"),
                funcionario("Caio", LocalDate.of(1961, 5, 2), "9836.14", "Coordenador"),
                funcionario("Miguel", LocalDate.of(1988, 10, 14), "19119.88", "Diretor"),
                funcionario("Alice", LocalDate.of(1995, 1, 5), "2234.68", "Recepcionista"),
                funcionario("Heitor", LocalDate.of(1999, 11, 19), "1582.72", "Operador"),
                funcionario("Arthur", LocalDate.of(1993, 3, 31), "4071.84", "Contador"),
                funcionario("Laura", LocalDate.of(1994, 7, 8), "3017.45", "Gerente"),
                funcionario("Heloísa", LocalDate.of(2003, 5, 24), "1606.85", "Eletricista"),
                funcionario("Helena", LocalDate.of(1996, 9, 2), "2799.93", "Gerente")
        );
    }

    private static Funcionario funcionario(String nome, LocalDate dataNascimento, String salario, String funcao) {
        return Funcionario.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .salario(new BigDecimal(salario))
                .funcao(funcao)
                .build();
    }
}
