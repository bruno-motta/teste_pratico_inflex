import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NUMBER_FORMATTER;

    static {
        NUMBER_FORMATTER = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        NUMBER_FORMATTER.setMinimumFractionDigits(2);
        NUMBER_FORMATTER.setMaximumFractionDigits(2);
    }

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria",   LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"),  "Operador"));
        funcionarios.add(new Funcionario("João",    LocalDate.of(1990,  5, 12), new BigDecimal("2284.38"),  "Operador"));
        funcionarios.add(new Funcionario("Caio",    LocalDate.of(1961,  5,  2), new BigDecimal("9836.14"),  "Coordenador"));
        funcionarios.add(new Funcionario("Miguel",  LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice",   LocalDate.of(1995,  1,  5), new BigDecimal("2234.68"),  "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor",  LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"),  "Operador"));
        funcionarios.add(new Funcionario("Arthur",  LocalDate.of(1993,  3, 31), new BigDecimal("4071.84"),  "Contador"));
        funcionarios.add(new Funcionario("Laura",   LocalDate.of(1994,  7,  8), new BigDecimal("3017.45"),  "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003,  5, 24), new BigDecimal("1606.85"),  "Eletricista"));
        funcionarios.add(new Funcionario("Helena",  LocalDate.of(1996,  9,  2), new BigDecimal("2799.93"),  "Gerente"));

        funcionarios.removeIf(f -> f.getNome().equals("João"));

        System.out.println("-".repeat(65));
        System.out.println("LISTA DE FUNCIONÁRIOS");
        imprimirFuncionarios(funcionarios);

        funcionarios.forEach(f ->
                f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP))
        );

        System.out.println("\n");
        System.out.println("FUNCIONÁRIOS APÓS AUMENTO DE 10%");
        imprimirFuncionarios(funcionarios);

        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\n");
        System.out.println("FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("\n  Função: " + funcao);
            lista.forEach(f -> System.out.println("    " + formatarFuncionario(f)));
        });


        System.out.println("\n");
        System.out.println("ANIVERSARIANTES DO MÊS DE OUTUBRO E DEZEMBRO");
        funcionarios.stream()
                .filter(f -> {
                    int mes = f.getLocalDate().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(f -> System.out.println("  " + formatarFuncionario(f)));

        System.out.println("\n");
        System.out.println("FUNCIONÁRIO COM MAIOR IDADE");
        funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getLocalDate))
                .ifPresent(f -> {
                    int idade = Period.between(f.getLocalDate(), LocalDate.now()).getYears();
                    System.out.println("  Nome: " + f.getNome() + " | Idade: " + idade + " anos");
                });

        System.out.println("\n");
        System.out.println("FUNCIONÁRIOS EM ORDEM ALFABÉTICA");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println("  " + formatarFuncionario(f)));


        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\n");
        System.out.println("TOTAL DOS SALÁRIOS");
        System.out.println("  Total: R$ " + NUMBER_FORMATTER.format(totalSalarios));
        System.out.println("\n");

        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("SALÁRIOS MÍNIMOS POR FUNCIONÁRIO (SM = R$ 1.212,00)");
        funcionarios.forEach(f -> {
            BigDecimal quantosSM = f.getSalario()
                    .divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("  %-10s → %s salários mínimos%n",
                    f.getNome(), NUMBER_FORMATTER.format(quantosSM));
        });

        System.out.println("\n" + "-".repeat(65));
    }

    private static void imprimirFuncionarios(List<Funcionario> lista) {
        lista.forEach(f -> System.out.println("  " + formatarFuncionario(f)));
    }

    private static String formatarFuncionario(Funcionario f) {
        return String.format("Nome: %-10s | Nascimento: %s | Salário: R$ %s | Função: %s",
                f.getNome(),
                f.getLocalDate().format(DATE_FORMATTER),
                NUMBER_FORMATTER.format(f.getSalario()),
                f.getFuncao());
    }
}