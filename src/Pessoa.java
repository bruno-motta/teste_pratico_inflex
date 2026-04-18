import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pessoa {

    String nome;
    LocalDateTime localDate;

    public Pessoa(String nome, LocalDateTime localDate) {
        this.nome = nome;
        this.localDate = localDate;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocalDate(LocalDateTime localDate) {
        this.localDate = localDate;
    }
}
