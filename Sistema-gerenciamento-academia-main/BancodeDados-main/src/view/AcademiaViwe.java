package view;

import control.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;

import javax.xml.transform.Source;

public class AcademiaViwe {
    private static final DateTimeFormatter FORMATADOR_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss") ;
    private static PlanoController planoController = new PlanoController();
    private static ClienteController clienteController = new ClienteController();
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static MatriculaController matriculaController = new MatriculaController();
    private static EquipamentoController equipamentoController = new EquipamentoController();
    private static EnderecoController enderecoController = new EnderecoController();
    private static RegistroController registroController = new RegistroController();
    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        exibirMenuPrincipal();
    }

    public static void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1 - Gerenciar Planos");
            System.out.println("2 - Gerenciar Clientes");
            System.out.println("3 - Gerenciar Funcionários");
            System.out.println("4 - Gerenciar Matrículas");
            System.out.println("5 - Gerenciar Equipamentos");
            System.out.println("6 - Gerenciar Enderecos");
            System.out.println("7 - Gerenciar Registros");
            System.out.println("0 - Sair do Sistema");
            System.out.print("Escolha uma área: ");

            int opc = -1;
            try {
                opc = entrada.nextInt();
                entrada.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                continue;
            }

            switch (opc) {
                case 1:
                    menuPlanos();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 3:
                    menuFuncionarios();
                    break;
                case 4:
                    menuMatriculas();
                    break;
                case 5:
                    menuEquipamentos();
                    break;
                case 6:
                    menuEnderecos();
                    break;
                case 7:
                    menuRegistros();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuPlanos() {
        int opc = -1;
        while (opc != 0) {
            System.out.println("\n--- Gerenciar Planos ---");
            System.out.println("1 - Cadastrar Plano");
            System.out.println("2 - Atualizar Plano");
            System.out.println("3 - Listar Planos");
            System.out.println("4 - Remover Plano");
            System.out.println("5 - Buscar Plano");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opc = entrada.nextInt();
                entrada.nextLine();

                switch (opc) {
                    case 1:
                        inserirPlano();
                        break;
                    case 2:
                        alterarPlano();
                        break;
                    case 3:
                        listarPlanos();
                        break;
                    case 4:
                        removerPlano();
                        break;
                    case 5:
                        buscarPlano();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                entrada.nextLine();
            }
        }
    }

    private static void menuClientes() {
        int opc = -1;
        while (opc != 0) {
            System.out.println("\n--- Gerenciar Clientes ---");
            System.out.println("1 - Adicionar Cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar cliente");
            System.out.println("4 - Atualizar conta");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opc = entrada.nextInt();
                entrada.nextLine();

                switch (opc) {
                    case 1:
                        adicionarCliente();
                        break;
                    case 2:
                        listarClientes();
                        break;
                    case 3:
                        buscarCliente();
                        break;
                    case 4:
                        atualizarConta();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                entrada.nextLine();
            }
        }
    }

    private static void menuFuncionarios() {
        int opc = -1;
        while (opc != 0) {
            System.out.println("\n--- Gerenciar Funcionários ---");
            System.out.println("1 - Adicionar funcionário");
            System.out.println("2 - Listar Funcionarios");
            System.out.println("3 - Buscar funcionário");
            System.out.println("4 - Atualizar funcionário");
            System.out.println("5 - Deletar funcionário");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opc = entrada.nextInt();
                entrada.nextLine();

                switch (opc) {
                    case 1:
                        adcionarFuncionario();
                        break;
                    case 2:
                        listartFuncionarios();
                        break;
                    case 3:
                        buscarFuncionario();
                        break;
                    case 4:
                        atualizarFuncionario();
                        break;
                    case 5:
                        deletarFuncionario();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                entrada.nextLine();
            }
        }
    }

    private static void menuMatriculas() {
        int opc = -1;
        while (opc != 0) {
            System.out.println("\n--- Gerenciar Matrículas ---");
            System.out.println("1 - Matricular cliente");
            System.out.println("2 - Desativar matrícula");
            System.out.println("3 - Pagamento matrícula");
            System.out.println("4 - Listar Matrículas Ativas");
            System.out.println("5 - Listar Matrículas de um Cliente");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opc = entrada.nextInt();
                entrada.nextLine();

                switch (opc) {
                    case 1:
                        matricularCliente();
                        break;
                    case 2:
                        desativarMatricula();
                        break;
                    case 3:
                        pagamentoMatricula();
                        break;
                    case 4:
                        listarMatriculasAtivas();
                        break;
                    case 5:
                        listarMatriculasCliente();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                entrada.nextLine();
            }
        }
    }

    private static void menuEquipamentos() {
        int opc = -1;
        while (opc != 0) {
            System.out.println("\n--- Gerenciar Equipamentos ---");
            System.out.println("1 - Adicionar equipamento");
            System.out.println("2 - Listar Equipamentos");
            System.out.println("3 - Buscar Equipamento");
            System.out.println("4 - Atualizar Equipamento");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opc = entrada.nextInt();
                entrada.nextLine();

                switch (opc) {
                    case 1:
                        inserirEquipamento();
                        break;
                    case 2:
                        listarEquipamentos();
                        break;
                    case 3:
                        buscarEquipamento();
                        break;
                    case 4:
                        atualizarEquipamento();
                        break;
                    case 5:
                        deletarEquipamento();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                entrada.nextLine();
            }
        }
    }

    private static void menuEnderecos() {
        int opc = -1;
        while (opc != 0) {
            System.out.println("\n--- Gerenciar Endereços ---");
            System.out.println("1 - Adicionar endereço");
            System.out.println("2 - Deletar enderço");
            System.out.println("3 - Listar endereços");
            System.out.println("4 - Alterar endereço");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opc = entrada.nextInt();
                entrada.nextLine();

                switch (opc) {
                    case 1:
                        inserirEndereco();
                        break;
                    case 2:
                        deletarEndereco();
                        break;
                    case 3:
                        listarEnderecos();
                        break;
                    case 4:
                        atualizarEndereco();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                entrada.nextLine();
            }
        }
    }

    private static void menuRegistros() {
        int opc = -1;
        while (opc != 0) {
            System.out.println("\n--- Gerenciar Registros ---");
            System.out.println("1 - Inserir registro");
            System.out.println("2 - Excluir registros");
            System.out.println("3 - Listar registros");
            System.out.println("4 - Listar registros de um Cliente");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opc = entrada.nextInt();
                entrada.nextLine();

                switch (opc) {
                    case 1:
                        inserirEntrada();
                        break;
                    case 2:
                        excluirRegistro();
                        break;
                    case 3:
                        listarRegistros();
                        break;
                    case 4:
                        buscarPorCliente();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                entrada.nextLine();
            }
        }
    }

    public static void inserirPlano() {
        System.out.println("Descrição:");
        String descricao = entrada.nextLine();
        System.out.println("Preço:");
        double preco = entrada.nextDouble();
        entrada.nextLine();
        planoController.inserirPlano(descricao, preco);
    }

    public static void alterarPlano() {
        System.out.println("ID do plano:");
        int id_plano = entrada.nextInt();
        System.out.println("Novo preço:");
        double preco = entrada.nextDouble();
        entrada.nextLine();
        planoController.atualizarPlano(id_plano, preco);
    }

    public static void listarPlanos() {
        ArrayList<Plano> planos = planoController.listarPlanos();
        for (Plano plano : planos) {
            System.out.println("ID: " + plano.getIdPlano() + " | Descrição: " + plano.getDescricaoPlano() + " | Preço: R$" + plano.getPrecoPlano());
        }
    }

    public static void removerPlano() {
        System.out.println("ID:");
        int id_plano = entrada.nextInt();
        entrada.nextLine();
        System.out.println(planoController.deletarPlano(id_plano));
    }

    public static void buscarPlano() {
        System.out.println("ID: ");
        int idPlano = entrada.nextInt();
        entrada.nextLine();
        Plano plano = planoController.buscarPlano(idPlano);
        System.out.println("ID: " + plano.getIdPlano() + " | Descrição: " + plano.getDescricaoPlano() + " | Preço: R$" + plano.getPrecoPlano());
    }

    public static void adicionarCliente() {
        String cpf, obs;
        System.out.println("O cliente já possui conta cadastrada?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        int opc = entrada.nextInt();
        entrada.nextLine();
        switch (opc) {
            case 1:
                System.out.println("Digite o cpf da conta:");
                cpf = entrada.nextLine();
                System.out.println("Observações: ");
                obs = entrada.nextLine();
                System.out.println(clienteController.cadastrarCliente(cpf, obs));
                break;
            case 2:
                System.out.println("Digite o nome completo:");
                String nome = entrada.nextLine();
                System.out.println("Digite o cpf:");
                cpf = entrada.nextLine();
                System.out.println("Digite o e-mail:");
                String email = entrada.nextLine();
                System.out.println("Digite o telefone:");
                String telefone = entrada.nextLine();
                System.out.println("Observações: ");
                obs = entrada.nextLine();
                System.out.println(clienteController.cadastrarContaCliente(nome, cpf, email, telefone, obs));
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static void listarClientes() {
        ArrayList<Cliente> clientes = clienteController.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId_cliente() + " | Nome: " + cliente.getNomeConta() + " | CPF: " + cliente.getCpfConta() + " | E-mail: " + cliente.getEmailConta() + " | Telefone: " + cliente.getTelefoneConta() + " | Observação: " + cliente.getObsCliente() + " | Data de cadastro: " + cliente.getDataCadastro());
        }
    }

    public static void buscarCliente() {
        System.out.println("Digite o cpf do cliente");
        String cpf = entrada.nextLine();
        Cliente cliente = clienteController.buscarCliente(cpf);
        System.out.println("ID: " + cliente.getId_cliente() + " | Nome: " + cliente.getNomeConta() + " | CPF: " + cliente.getCpfConta() + " | E-mail: " + cliente.getEmailConta() + " | Telefone: " + cliente.getTelefoneConta() + " | Observação: " + cliente.getObsCliente() + " | Data de cadastro: " + cliente.getDataCadastro());
    }

    public static void atualizarConta() {
        System.out.println("Digite o cpf da conta:");
        String cpf = entrada.nextLine();
        System.out.println("Digite o novo nome:");
        String novoNome = entrada.nextLine();
        System.out.println("Digite o novo e-mail:");
        String novoEmail = entrada.nextLine();
        System.out.println("Digite o novo telefone:");
        String novoTelefone = entrada.nextLine();
        System.out.println(clienteController.atualiarConta(cpf, novoNome, novoEmail, novoTelefone));
    }

    public static void adcionarFuncionario() {
        String cpf, funcao;
        double salario;
        System.out.println("O funcionario já possui conta cadastrada?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        int opc = entrada.nextInt();
        entrada.nextLine();
        switch (opc) {
            case 1:
                System.out.println("Digite o cpf da conta:");
                cpf = entrada.nextLine();
                System.out.println("Cargo: ");
                funcao = entrada.nextLine();
                System.out.println("Salário:");
                salario = entrada.nextDouble();
                entrada.nextLine();
                System.out.println(funcionarioController.cadastrarFuncionario(cpf, funcao, salario));
                break;
            case 2:
                System.out.println("Digite o nome completo:");
                String nome = entrada.nextLine();
                System.out.println("Digite o cpf:");
                cpf = entrada.nextLine();
                System.out.println("Digite o e-mail:");
                String email = entrada.nextLine();
                System.out.println("Digite o telefone:");
                String telefone = entrada.nextLine();
                System.out.println("Cargo: ");
                funcao = entrada.nextLine();
                System.out.println("Salário:");
                salario = entrada.nextDouble();
                entrada.nextLine();
                System.out.println(funcionarioController.cadastrarContaFuncionario(nome, cpf, telefone, email, funcao, salario));
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static void listartFuncionarios() {
        ArrayList<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        for (Funcionario funcionario : funcionarios) {
            System.out.println("ID: " + funcionario.getId_funcionario() + " | Nome: " + funcionario.getNomeConta() + " | CPF: " + funcionario.getCpfConta() + " | E-mail: " + funcionario.getEmailConta() + " | Telefone: " + funcionario.getTelefoneConta() + " | Cargo: " + funcionario.getFuncao() + " | Salário: " + funcionario.getSalario());
        }
    }

    public static void buscarFuncionario() {
        System.out.println("Digite o cpf do funcionario");
        String cpf = entrada.nextLine();
        Funcionario funcionario = funcionarioController.buscarFuncionario(cpf);
        System.out.println("ID: " + funcionario.getId_funcionario() + " | Nome: " + funcionario.getNomeConta() + " | CPF: " + funcionario.getCpfConta() + " | E-mail: " + funcionario.getEmailConta() + " | Telefone: " + funcionario.getTelefoneConta() + " | Cargo: " + funcionario.getFuncao() + " | Salário: " + funcionario.getSalario());
    }

    public static void deletarFuncionario() {
        System.out.println("Digite o cpf do funcionario que deseja excluir:");
        String cpf = entrada.nextLine();
        System.out.println(funcionarioController.deletarFuncionario(cpf));
    }

    public static void atualizarFuncionario() {
        System.out.println("Digite o cpf da conta:");
        String cpf = entrada.nextLine();
        System.out.println("Digite a nova função:");
        String funcao = entrada.nextLine();
        System.out.println("Digite o novo salário:");
        double salario = entrada.nextDouble();
        entrada.nextLine();
        System.out.println(funcionarioController.atualizarFuncionario(cpf, funcao, salario));
    }

    public static void matricularCliente() {
        System.out.println("Digite o CPF do cliente:");
        String cpf = entrada.nextLine();
        System.out.println("Digite o ID do plano:");
        int idPlano = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite a duração em meses:");
        int meses = entrada.nextInt();
        System.out.println(matriculaController.matricularCliente(cpf, idPlano, meses));
    }

    public static void desativarMatricula() {
        System.out.println("Digite o CPF do cliente:");
        String cpf = entrada.nextLine();
        System.out.println(matriculaController.desativarMatricula(cpf));
    }

    public static void pagamentoMatricula() {
        System.out.println("Digite o CPF do cliente:");
        String cpf = entrada.nextLine();
        System.out.println(matriculaController.pagamentoMatricula(cpf));
    }

    public static ArrayList<Matricula> listarMatriculasAtivas() {
        return matriculaController.listarMatriculasAtivas();
    }

    public static ArrayList<Matricula> listarMatriculasCliente() {
        System.out.println("Digite o CPF do cliente:");
        String cpf = entrada.nextLine();
        return matriculaController.listarMatriculasCliente(cpf);
    }

    public static void inserirEquipamento() {
        System.out.println("Digite a descrição (nome) do equipamento:");
        String descricao = entrada.nextLine();
        System.out.println("Digite a marca do equipamento:");
        String marca = entrada.nextLine();
        System.out.println("Digite o músculo alvo do equipamento:");
        String musculoAlvo = entrada.nextLine();
        System.out.println(equipamentoController.cadastrarEquipamento(descricao, marca, musculoAlvo));
    }

    public static void listarEquipamentos() {
        ArrayList<Equipamento> equipamentos = equipamentoController.listarEquipamentos();
        for (Equipamento equipamento : equipamentos) {
            System.out.println();
            System.out.println("ID: " + equipamento.getIdEquipamento());
            System.out.println("Descricao: " + equipamento.getDescricao());
            System.out.println("Marca: " + equipamento.getMarca());
            System.out.println("Musculo Alvo: " + equipamento.getMusculoAlvo());
        }
    }

    public static void buscarEquipamento() {
        System.out.println("Digite o ID do equipamento:");
        int idEquipamento = entrada.nextInt();

        System.out.println(equipamentoController.buscarEquipamento(idEquipamento));
    }

    public static void atualizarEquipamento() {
        System.out.println("Digite o ID do equipamento que deseja atualizar:");
        int idEquipamento = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite a descrição do equipamento:");
        String descricao = entrada.nextLine();
        System.out.println("Digite a marca da equipamento:");
        String marca = entrada.nextLine();
        System.out.println("Digite a musculo alvo do equipamento:");
        String musculoAlvo = entrada.nextLine();

        System.out.println(equipamentoController.atualizarEquipamento(idEquipamento, descricao, marca, musculoAlvo));
    }

    public static void deletarEquipamento() {
        System.out.println("Digite o ID do equipamento que deseja deletar:");
        int idEquipamento = entrada.nextInt();
        System.out.println(equipamentoController.deletarEquipamento(idEquipamento));
    }

    public static void inserirEndereco() {
        System.out.println("Digite o ID da conta vinculada ao endereco:");
        int idConta = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite o CEP do endereco:");
        String cep = entrada.nextLine();
        System.out.println("Digite o logradouro do endereco:");
        String logradouro = entrada.nextLine();
        System.out.println("Digite a bairro do endereco:");
        String bairro = entrada.nextLine();
        System.out.println("Digite o numero do endereco:");
        int numero = entrada.nextInt();
        entrada.nextLine();
        System.out.println(enderecoController.cadastraEndereco(idConta, cep, logradouro, bairro, numero));
    }

    public static void deletarEndereco() {
        System.out.println("Digite o ID do endereco que deseja deletar:");
        int idEndereco = entrada.nextInt();
        System.out.println(enderecoController.deletarEndereco(idEndereco));
    }

    public static void atualizarEndereco() {
        System.out.println("Digite o id do endereco que deseja atualizar:");
        int idEndereco = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite o CEP do endereco:");
        String cep = entrada.nextLine();
        System.out.println("Digite o logradouro do endereco:");
        String logradouro = entrada.nextLine();
        System.out.println("Digite a bairro do endereco:");
        String bairro = entrada.nextLine();
        System.out.println("Digite o numero do endereco:");
        int numero = entrada.nextInt();
        entrada.nextLine();
        System.out.println(enderecoController.alterarEndereco(idEndereco, cep, logradouro, bairro, numero));
    }

    public static void listarEnderecos() {
        ArrayList<Endereco> enderecos = enderecoController.listarEnderecos();
        for (Endereco endereco : enderecos) {
            System.out.println("ID: " + endereco.getIdEndereco());
            System.out.println("|| CEP: " + endereco.getCep() +"|| Logradouro: " + endereco.getLogradouro());
            System.out.println("Bairro: " + endereco.getBairro() + "|| Numero: " + endereco.getNumero());
        }
    }

    private static void inserirEntrada() {
        try {
            System.out.print("Digite o ID do cliente: ");
            int idCliente = entrada.nextInt();
            entrada.nextLine();

            System.out.print("Digite a Data e Hora de Entrada (dd/MM/yyyy HH:mm:ss): ");
            String dataEntradaStr = entrada.nextLine();
            LocalDateTime dataEntrada = null;
            try {
                dataEntrada = LocalDateTime.parse(dataEntradaStr, FORMATADOR_DATA_HORA);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de Data e Hora de Entrada inválido. Use dd/MM/yyyy HH:mm:ss.");
                return;
            }

            System.out.print("Digite a Data e Hora de Saída (dd/MM/yyyy HH:mm:ss): ");
            String dataSaidaStr = entrada.nextLine();
            LocalDateTime dataSaida = null;
            try {
                dataSaida = LocalDateTime.parse(dataSaidaStr, FORMATADOR_DATA_HORA);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de Data e Hora de Saída inválido. Use dd/MM/yyyy HH:mm:ss.");
                return;
            }

            System.out.println(registroController.inserirRegistro(idCliente, dataEntrada, dataSaida));

        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Verifique o ID do cliente.");
            entrada.nextLine();
        }
    }

    private static void excluirRegistro() {
        try {
            System.out.println("Digite o ID do cliente vinculado ao registro que deseja excluir:");
            int idCliente = entrada.nextInt();
            entrada.nextLine();

            System.out.println("Digite a data de entrada (dd/MM/yyyy HH:mm:ss):");
            String dataEntradaStr = entrada.nextLine();

            LocalDateTime dataEntrada = LocalDateTime.parse(dataEntradaStr, FORMATADOR_DATA_HORA);

            String resultado = registroController.excluirRegistro(idCliente, dataEntrada);
            System.out.println(resultado);

        } catch (InputMismatchException e) {
            System.out.println("Erro: O ID do cliente deve ser um número.");
            entrada.nextLine();
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data e hora inválido. Use dd/MM/yyyy HH:mm:ss.");
        }
    }

    private static void listarRegistros() {
        ArrayList<Registro> registros = registroController.listarRegistros();


        if (registros.isEmpty()) {
            System.out.println("Nenhum registro de acesso encontrado.");
        } else {
            System.out.println();
            for (Registro registro : registros) {
                String dataEntradaStr = registro.getDataEntrada().format(FORMATADOR_DATA_HORA);
                String dataSaidaStr = registro.getDataSaida().format(FORMATADOR_DATA_HORA);
                System.out.println("ID: " + registro.getIdCliente() + " | Data entrada: " + dataEntradaStr + " | Data saída: " + dataSaidaStr);
            }
        }
    }

    private static void buscarPorCliente() {
        try {
            System.out.print("Digite o ID do cliente para buscar registros: ");
            int idCliente = entrada.nextInt();
            entrada.nextLine();

            ArrayList<Registro> registrosBuscados = registroController.buscarPorCliente(idCliente);
            if (registrosBuscados.isEmpty()) {
                System.out.println("Nenhum registro de acesso encontrado para o cliente ID " + idCliente + ".");
            } else {
                System.out.println("Registros para o Cliente ID: " + idCliente);
                for (Registro registro : registrosBuscados) {
                    String dataEntradaStr = registro.getDataEntrada().format(FORMATADOR_DATA_HORA);
                    String dataSaidaStr = registro.getDataSaida().format(FORMATADOR_DATA_HORA);
                    System.out.println("ID: " + registro.getIdCliente() + " | Data entrada: " + dataEntradaStr + " | Data saída: " + dataSaidaStr);                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Por favor, digite um número inteiro.");
            entrada.nextLine();
        }
    }
}

