package br.com.prothera.desafio.repositorio;

import br.com.prothera.desafio.modelo.Funcionario;

import java.util.Collection;
import java.util.List;

public interface FuncionarioRepositorio {

    void salvarTodos(Collection<Funcionario> funcionarios);

    boolean removerPorNome(String nome);

    List<Funcionario> listarTodos();
}
