import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Funcionario extends Pessoa{

    BigDecimal salario;
    String funcao;

    public Funcionario(String nome, LocalDate localDate, BigDecimal salario, String funcao) {
        super(nome, localDate);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
