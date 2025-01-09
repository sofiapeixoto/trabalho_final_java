import Categoria.MenuCategoria;
import Tarefa.MenuTarefa;
import Utilizador.MenuUtilizador;

import java.io.IOException;
import java.util.Scanner;

    public class Menu {

        // objeto que permite ler valores introduzidos pelo utilizador
        private static final Scanner scanner = new Scanner(System.in);

        public void mostrarMenuInicial() throws IOException {
            while (true) {

                System.out.println("*******************");
                System.out.println("*** Menu do Gerenciador de Tarefas  ***");
                System.out.println("1-Utilizadores");

                int opcao = solicitarOpcao();

                switch (opcao) {
                    case 1 -> mostrarMenuUtilizador();
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
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
                    case 5 -> mostrarMenuInicial();
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
                    case 6 -> mostrarMenuInicial();
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }


        private void mostrarMenuUtilizador() throws IOException {
            MenuUtilizador menuUtilizador = new MenuUtilizador();

            while (true) {
                System.out.println("*******************");
                System.out.println("*** Menu Utilizador ***");
                System.out.println("1 - Gerir as tarefas do Utilizador (tem que adicionar o utilizador caso ainda nao tenha adicionado!!!)");
                System.out.println("2 - Adicionar Utilizadores");
                System.out.println("3 - Alterar dados do Utilizador");
                System.out.println("4 - Apagar Utilizador");
                System.out.println("5 - Voltar");

                int opcao = solicitarOpcao();

                switch (opcao) {
                    case 1 -> mostrarMenuTarefaCategoria();
                    case 2 -> menuUtilizador.adicionarUtilizador();
                    case 3 -> menuUtilizador.alterarUtilizador();
                    case 4 -> menuUtilizador.apagarUtilizador();
                    case 5 -> mostrarMenuInicial();
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
                    case 3 -> mostrarMenuInicial();
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

