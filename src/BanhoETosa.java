import java.time.LocalDate;

public class BanhoETosa extends Servico {
    public BanhoETosa(LocalDate data) {
        super(data);
    }

    @Override
    public String getDescricao() {
        return "Banho e Tosa em " + data;
    }
}
