import java.time.LocalDate;

public class Adestramento extends Servico {
    public Adestramento(LocalDate data) {
        super(data);
    }

    @Override
    public String getDescricao() {
        return "Adestramento a partir de " + data;
    }
}
