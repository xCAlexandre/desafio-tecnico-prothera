# Desafio Técnico — Funcionários da Indústria

Projeto Java que cadastra, manipula e exibe os funcionários de uma indústria, conforme o teste prático da Prothera.

## Requisitos

- Java 17 ou superior
- Maven 3.8 ou superior

## Como executar

```bash
mvn compile exec:java
```

Ou abra o projeto na IDE (IntelliJ, Eclipse, NetBeans) e execute a classe `br.com.prothera.desafio.Principal`.

Também é possível gerar o jar executável:

```bash
mvn package
java -jar target/desafio-funcionarios-1.0.0.jar
```

## Como testar

```bash
mvn test
```

## Itens atendidos

| Item | Onde |
|---|---|
| 1 – Classe `Pessoa` (nome, data de nascimento) | `modelo/Pessoa` |
| 2 – Classe `Funcionario` estendendo `Pessoa` (salário, função) | `modelo/Funcionario` |
| 3.1 – Inserir todos os funcionários | `etapa/InserirFuncionariosEtapa` + `dados/CargaInicialFuncionarios` |
| 3.2 – Remover o funcionário "João" | `etapa/RemoverFuncionarioEtapa` |
| 3.3 – Imprimir todos os funcionários (data `dd/mm/aaaa`, número `1.234,56`) | `etapa/ImprimirFuncionariosEtapa` + `formatacao/Formatador` |
| 3.4 – Aumento de 10% nos salários | `etapa/AplicarReajusteEtapa` + `reajuste/ReajustePercentual` |
| 3.5 – Agrupar por função em um `Map` | `etapa/AgruparPorFuncaoEtapa` |
| 3.6 – Imprimir agrupados por função | `etapa/ImprimirAgrupadosPorFuncaoEtapa` |
| 3.8 – Aniversariantes dos meses 10 e 12 | `etapa/ImprimirAniversariantesEtapa` |
| 3.9 – Funcionário com a maior idade (nome e idade) | `etapa/ImprimirFuncionarioMaisVelhoEtapa` |
| 3.10 – Lista em ordem alfabética | `etapa/ImprimirOrdemAlfabeticaEtapa` |
| 3.11 – Total dos salários | `etapa/ImprimirTotalSalariosEtapa` |
| 3.12 – Quantidade de salários mínimos (R$ 1.212,00) | `etapa/ImprimirSalariosMinimosEtapa` |

O enunciado não possui o item 3.7, por isso a numeração original foi mantida.

## Arquitetura

```
br.com.prothera.desafio
├── Principal                 monta as dependências e executa as etapas em ordem
├── modelo                    Pessoa (abstrata) e Funcionario
├── reajuste                  PoliticaReajuste e ReajustePercentual
├── repositorio               FuncionarioRepositorio e implementação em memória
├── servico                   FuncionarioServico com as regras de negócio
├── dados                     CargaInicialFuncionarios com a tabela do enunciado
├── formatacao                Formatador de datas e números
├── apresentacao              ImpressoraFuncionarios para a saída no console
└── etapa                     uma classe por item do enunciado
```

### Design patterns

- **Builder** — `Funcionario.builder()` cria funcionários de forma legível e validada.
- **Repository** — `FuncionarioRepositorio` isola o armazenamento da regra de negócio.
- **Strategy** — `PoliticaReajuste` permite trocar a regra de aumento sem alterar `Funcionario`.
- **Command** — cada item do enunciado é uma `Etapa` executável e independente.
- **Template Method** — `EtapaBase.executar()` imprime o título e delega o conteúdo para cada etapa.
- **Injeção de dependências** — `Principal` compõe os objetos; o `Clock` injetado torna o cálculo de idade testável.

### Decisões

- Valores monetários em `BigDecimal`, criados a partir de `String`, com arredondamento `HALF_UP` em duas casas.
- O salário só é alterado pelo comportamento `receberReajuste`, sem setters públicos.
- A ordenação por nome usa `Collator` pt-BR para tratar acentos corretamente.
- O agrupamento por função usa `TreeMap`, exibindo as funções em ordem alfabética.
