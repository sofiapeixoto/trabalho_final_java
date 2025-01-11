import Categoria.Categoria;
import Categoria.MenuCategoria;
import Tarefa.MenuTarefa;
import Utilizador.MenuUtilizador;
import Utilizador.Utilizador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

    public class Menu {

        // objeto que permite ler valores introduzidos pelo utilizador
        private static final Scanner scanner = new Scanner(System.in);
        private static final String ficheiroLogin = "src/loginUtilizador.txt.txt";


        public void mostrarMenuEscolherUtilizador() throws IOException {
            while (true) {
                // Limpa o arquivo de login para garantir que só um utilizador esteja nele
                limparFicheiroLogin(ficheiroLogin);

                System.out.println("*******************");
                System.out.println("*** Login ***");
                System.out.println("Escreva o username:");

                String nomeUtilizador = scanner.nextLine();

                // Cria um objeto Utilizador com base no nome fornecido (pode ajustar conforme necessário)
                Utilizador utilizador = new Utilizador();
                utilizador.setNomeUtilizador(nomeUtilizador);

                // Adiciona o utilizador ao arquivo de login
                escreverLogin(List.of(utilizador));

                // Após o login, você pode redirecionar o utilizador para outro menu
                System.out.println("Login efetuado com sucesso!");
                // Exemplo: mostrar o menu de tarefas e categorias
                mostrarMenuTarefaCategoria();
            }
        }

        public void escreverLogin(List<Utilizador> utilizadores) throws IOException {
            limparFicheiroLogin(ficheiroLogin);
            guardarLoginFicheiro(utilizadores);
        }
        public void limparFicheiroLogin(String fileName) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                // Apenas abrir o arquivo em modo de escrita limpa o conteúdo.
            } catch (IOException e) {
                System.err.println("Erro ao apagar conteúdo do ficheiro " + e.getMessage());
            }
        }
        private void guardarLoginFicheiro(List<Utilizador> utilizadores) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiroLogin))) {
                int index = 0;
                for (Utilizador utilizador : utilizadores) {
                    if(index > 0) {
                        bw.newLine();
                    }
                    bw.write(utilizador.getNomeUtilizador());
                    index++;
                }
            } catch (IOException e) {
                System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
            }
        }

        private void mostrarMenuCategoria() throws IOException {
            MenuCategoria menuCategoria = new MenuCategoria();

            while (true) {
                System.out.println("*******************");
                System.out.println("*** Menu Categoria ***");
                System.out.println("1 - Listar Categorias");
                System.out.println("2 - Adicionar Categoria");
                System.out.println("3 - Alterar dados Categoria");
                System.out.println("4 - Apagar Categorias");
                System.out.println("5 - Voltar");

                int opcao = solicitarOpcao();

                switch (opcao) {
                    case 1 -> menuCategoria.listarCategorias();
                    case 2 -> menuCategoria.adicionarCategoria();
                    case 3 -> menuCategoria.atualizarCategoria();
                    case 4 -> menuCategoria.apagarCategoria();
                    case 5 -> mostrarMenuUtilizador();
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
        private void mostrarMenuTarefa() throws IOException {
            MenuTarefa menuTarefa = new MenuTarefa();

            while (true) {
                System.out.println("*******************");
                System.out.println("*** Menu Tarefa ***");
                System.out.println("1 - Listar Tarefas");
                System.out.println("2 - Adicionar Tarefa");
                System.out.println("3 - Alterar dados Tarefa");
                System.out.println("4 - Apagar Tarefa");
                System.out.println("5 - Concluir Tarefa");
                System.out.println("6 - Voltar");

                int opcao = solicitarOpcao();

                switch (opcao) {
                    case 1 -> menuTarefa.listarTarefas();
                    case 2 -> menuTarefa.adicionarTarefas();
                    case 3 -> menuTarefa.alterarTarefa();
                    case 4 -> menuTarefa.apagarTarefa();
                    case 5 -> menuTarefa.concluirTarefa();
                    case 6 -> mostrarMenuUtilizador();
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }


        public void mostrarMenuUtilizador() throws IOException {
            MenuUtilizador menuUtilizador = new MenuUtilizador();

            while (true) {
                System.out.println("*******************");
                System.out.println("*** Menu Utilizador ***");
                System.out.println("1 - Selecionar Utilizador (tem que adicionar o utilizador caso ainda nao tenha adicionado!!!)");
                System.out.println("2 - Adicionar Utilizadores");
                System.out.println("3 - Alterar dados do Utilizador");
                System.out.println("4 - Apagar Utilizador");

                int opcao = solicitarOpcao();

                switch (opcao) {
                    case 1 -> mostrarMenuEscolherUtilizador();
                    case 2 -> menuUtilizador.adicionarUtilizador();
                    case 3 -> menuUtilizador.alterarUtilizador();
                    case 4 -> menuUtilizador.apagarUtilizador();
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }

        private void mostrarMenuTarefaCategoria() throws IOException {

            while (true) {
                System.out.println("*******************");
                System.out.println("*** Menu Gerir Tarefas e Categorias do Utilizador ***");
                System.out.println("1 - Gerir Tarefas");
                System.out.println("2 - Gerir Categorias");
                System.out.println("3 - Voltar");

                int opcao = solicitarOpcao();

                switch (opcao) {
                    case 1 -> mostrarMenuTarefa();
                    case 2 -> mostrarMenuCategoria();
                    case 3 -> mostrarMenuUtilizador();
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }

            private static int solicitarOpcao() {

                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Digite um número.");
                    scanner.next();
                }

                return scanner.nextInt();
            }
        }

