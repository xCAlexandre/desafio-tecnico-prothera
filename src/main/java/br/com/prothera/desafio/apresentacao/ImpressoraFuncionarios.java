package br.com.prothera.desafio.apresentacao;

import br.com.prothera.desafio.formatacao.Formatador;
import br.com.prothera.desafio.modelo.Funcionario;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ImpressoraFuncionarios {

    private static final String FORMATO_LINHA = "%-12s | %-15s | %12s | %s%n";
    private static final String FORMATO_SALARIOS_MINIMOS = "%-12s | %12s | %16s%n";
    private static final int LARGURA_SEPARADOR = 64;

    private final PrintStream saida;

    public ImpressoraFuncionarios(PrintStream saida) {
        this.saida = Objects.requireNonNull(saida, "A saída é obrigatória");
    }

    public void imprimirTitulo(String titulo) {
        saida.println();
        saida.println("=".repeat(LARGURA_SEPARADOR));
        saida.println(titulo);
        saida.println("=".repeat(LARGURA_SEPARADOR));
    }

    public void imprimirSubtitulo(String subtitulo) {
        saida.println();
        saida.println(">> " + subtitulo);
    }

    public void imprimirMensagem(String mensagem) {
        saida.println(mensagem);
    }

    public void imprimirTabela(List<Funcionario> funcionarios) {
        if (funcionarios.isEmpty()) {
            imprimirMensagem("Nenhum funcionário encontrado.");
            return;
        }
        saida.printf(FORMATO_LINHA, "Nome", "Data Nascimento", "Salário", "Função");
        saida.println("-".repeat(LARGURA_SEPARADOR));
        funcionarios.forEach(this::imprimirLinha);
    }

    public void imprimirQuantidadeSalariosMinimos(Map<Funcionario, BigDecimal> salariosMinimosPorFuncionario) {
        if (salariosMinimosPorFuncionario.isEmpty()) {
            imprimirMensagem("Nenhum funcionário encontrado.");
            return;
        }
        saida.printf(FORMATO_SALARIOS_MINIMOS, "Nome", "Salário", "Salários mínimos");
        saida.println("-".repeat(LARGURA_SEPARADOR));
        salariosMinimosPorFuncionario.forEach((funcionario, quantidade) ->
                saida.printf(FORMATO_SALARIOS_MINIMOS,
                        funcionario.getNome(),
                        Formatador.numero(funcionario.getSalario()),
                        Formatador.numero(quantidade)));
    }

    private void imprimirLinha(Funcionario funcionario) {
        saida.printf(FORMATO_LINHA,
                funcionario.getNome(),
                Formatador.data(funcionario.getDataNascimento()),
                Formatador.numero(funcionario.getSalario()),
                funcionario.getFuncao());
    }
}
