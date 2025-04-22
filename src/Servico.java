import java.time.LocalDate;

public abstract class Servico {
    protected LocalDate data;

    public Servico(LocalDate data) {
        this.data = data;
    }

    public abstract String getDescricao();
}