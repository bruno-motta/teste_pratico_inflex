import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pessoa {

    String nome;
    LocalDate localDate;

    public Pessoa(String nome, LocalDate localDate) {
        this.nome = nome;
        this.localDate = localDate;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
