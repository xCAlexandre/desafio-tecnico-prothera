package br.com.prothera.desafio.servico;

import br.com.prothera.desafio.modelo.Funcionario;
import br.com.prothera.desafio.modelo.Pessoa;
import br.com.prothera.desafio.reajuste.PoliticaReajuste;
import br.com.prothera.desafio.repositorio.FuncionarioRepositorio;

import java.math.BigDecimal;
import java.text.Collator;
import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FuncionarioServico {

    private static final Comparator<Pessoa> POR_NOME =
            Comparator.comparing(Pessoa::getNome, Collator.getInstance(Locale.forLanguageTag("pt-BR")));
    private static final Comparator<Pessoa> POR_DATA_NASCIMENTO = Comparator.comparing(Pessoa::getDataNascimento);

    private final FuncionarioRepositorio repositorio;
    private final Clock relogio;

    public FuncionarioServico(FuncionarioRepositorio repositorio, Clock relogio) {
        this.repositorio = Objects.requireNonNull(repositorio, "O repositório é obrigatório");
        this.relogio = Objects.requireNonNull(relogio, "O relógio é obrigatório");
    }

    public void cadastrar(List<Funcionario> funcionarios) {
        repositorio.salvarTodos(funcionarios);
    }

    public boolean removerPorNome(String nome) {
        return repositorio.removerPorNome(nome);
    }

    public List<Funcionario> listarTodos() {
        return repositorio.listarTodos();
    }

    public void aplicarReajuste(PoliticaReajuste politica) {
        repositorio.listarTodos().forEach(funcionario -> funcionario.receberReajuste(politica));
    }

    public Map<String, List<Funcionario>> agruparPorFuncao() {
        return repositorio.listarTodos().stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, TreeMap::new, Collectors.toList()));
    }

    public List<Funcionario> aniversariantesNosMeses(Set<Month> meses) {
        Objects.requireNonNull(meses, "Os meses são obrigatórios");
        return repositorio.listarTodos().stream()
                .filter(funcionario -> meses.stream().anyMatch(funcionario::fazAniversarioEm))
                .toList();
    }

    public Optional<Funcionario> maisVelho() {
        return repositorio.listarTodos().stream().min(POR_DATA_NASCIMENTO);
    }

    public int idadeDe(Funcionario funcionario) {
        return funcionario.idadeEm(LocalDate.now(relogio));
    }

    public List<Funcionario> ordenadosPorNome() {
        return repositorio.listarTodos().stream().sorted(POR_NOME).toList();
    }

    public BigDecimal totalSalarios() {
        return repositorio.listarTodos().stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Funcionario, BigDecimal> salariosMinimosPorFuncionario(BigDecimal salarioMinimo) {
        return repositorio.listarTodos().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        funcionario -> funcionario.quantidadeSalariosMinimos(salarioMinimo),
                        (primeiro, segundo) -> primeiro,
                        LinkedHashMap::new));
    }
}
