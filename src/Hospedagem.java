import java.time.LocalDate;

public class Hospedagem extends Servico {
    public Hospedagem(LocalDate data) {
        super(data);
    }

    @Override
    public String getDescricao() {
        return "Hospedagem a partir de " + data;
    }
}