package br.com.prothera.desafio.repositorio;

import br.com.prothera.desafio.modelo.Funcionario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class FuncionarioRepositorioEmMemoria implements FuncionarioRepositorio {

    private final List<Funcionario> funcionarios = new ArrayList<>();

    @Override
    public void salvarTodos(Collection<Funcionario> novosFuncionarios) {
        Objects.requireNonNull(novosFuncionarios, "A lista de funcionários é obrigatória");
        novosFuncionarios.forEach(funcionario ->
                funcionarios.add(Objects.requireNonNull(funcionario, "O funcionário é obrigatório")));
    }

    @Override
    public boolean removerPorNome(String nome) {
        return funcionarios.removeIf(funcionario -> funcionario.possuiNome(nome));
    }

    @Override
    public List<Funcionario> listarTodos() {
        return List.copyOf(funcionarios);
    }
}
