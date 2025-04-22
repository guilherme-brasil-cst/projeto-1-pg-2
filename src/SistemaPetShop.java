import java.time.LocalDate;
import java.util.*;

public class SistemaPetShop {
    private Map<String, Cliente> clientes = new HashMap<>();
    private Map<Pet, List<Servico>> servicosContratados = new HashMap<>();
    private Scanner sc;

    public SistemaPetShop(Scanner scanner) {
        this.sc = scanner;
    }

    public void cadastrarCliente() {
        System.out.println("\n=== CADASTRO DE CLIENTE ===");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(nome, telefone, email);
        clientes.put(nome, cliente);
        System.out.println("Cliente cadastrado com sucesso.");
    }

    public void cadastrarPet() {
        System.out.println("\n=== CADASTRO DE PET ===");
        System.out.print("Nome do dono: ");
        String nomeDono = sc.nextLine();
        Cliente cliente = clientes.get(nomeDono);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Nome do pet: ");
        String nome = sc.nextLine();
        System.out.print("Tipo (cachorro, gato...): ");
        String tipo = sc.nextLine();
        System.out.print("Idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        Pet pet = new Pet(nome, tipo, idade);
        cliente.adicionarPet(pet);
        System.out.println("Pet cadastrado com sucesso.");
    }

    public void listarClientes() {
        System.out.println("\n=== LISTA DE CLIENTES ===");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente c : clientes.values()) {
            System.out.println(c.getNome() + " | " + c.getTelefone() + " | " + c.getEmail());
            for (Pet p : c.getPets()) {
                System.out.println("   " + p);
            }
        }
    }

    public void contratarServico() {
        System.out.println("\n=== CONTRATAR SERVIÇO ===");
        System.out.print("Nome do dono: ");
        String nomeDono = sc.nextLine();
        Cliente cliente = clientes.get(nomeDono);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        List<Pet> pets = cliente.getPets();
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet cadastrado para esse cliente.");
            return;
        }

        System.out.println("Pets disponíveis:");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).getNome());
        }

        System.out.print("Escolha o pet (número): ");
        int idx = Integer.parseInt(sc.nextLine()) - 1;

        if (idx < 0 || idx >= pets.size()) {
            System.out.println("Pet inválido.");
            return;
        }

        Pet pet = pets.get(idx);

        System.out.println("Serviços disponíveis:");
        System.out.println("1. Banho e Tosa");
        System.out.println("2. Consulta Veterinária");
        System.out.println("3. Hospedagem");
        System.out.println("4. Adestramento");

        System.out.print("Escolha o serviço (número): ");
        int opcao = Integer.parseInt(sc.nextLine());

        System.out.print("Informe a data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(sc.nextLine());

        Servico servico = switch (opcao) {
            case 1 -> new BanhoETosa(data);
            case 2 -> new ConsultaVeterinaria(data);
            case 3 -> new Hospedagem(data);
            case 4 -> new Adestramento(data);
            default -> null;
        };

        if (servico != null) {
            servicosContratados.computeIfAbsent(pet, k -> new ArrayList<>()).add(servico);
            System.out.println("Serviço contratado com sucesso.");
        } else {
            System.out.println("Opção de serviço inválida.");
        }
    }

    public void listarServicos() {
        System.out.println("\n=== SERVIÇOS CONTRATADOS ===");
        if (servicosContratados.isEmpty()) {
            System.out.println("Nenhum serviço contratado.");
            return;
        }

        for (Map.Entry<Pet, List<Servico>> entry : servicosContratados.entrySet()) {
            System.out.println("Pet: " + entry.getKey().getNome());
            for (Servico s : entry.getValue()) {
                System.out.println("   " + s.getDescricao());
            }
        }
    }
}