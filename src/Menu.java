import Categoria.MenuCategoria;
import Tarefa.MenuTarefa;
import Utilizador.MenuUtilizador;

import java.io.IOException;
import java.util.Scanner;

    public final class Menu {

        // objeto que permite ler valores introduzidos pelo utilizador
        private static final Scanner scanner = new Scanner(System.in);

        public void mostrarMenuInicial() throws IOException {
            while (true) {

                System.out.println("*******************");
                System.out.println("*** Menu ***");
                System.out.println("1 - Gerir Categorias");
                System.out.println("2 - Gerir Tarefas");
                System.out.println("3 - Gerir Utilizadores");

                int opcao = solicitarOpcao();

                switch (opcao) {
                    case 1 -> mostrarMenuCategoria();
                    case 2 -> mostrarMenuTarefa();
                    case 3 -> mostrarMenuUtilizador();
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
                System.out.println("5 - Voltar");

                int opcao = solicitarOpcao();

                switch (opcao) {
                    case 1 -> menuTarefa.listarTarefas();
                    case 2 -> menuTarefa.adicionarTarefas();
                    case 3 -> menuTarefa.alterarTarefa();
                    case 4 -> menuTarefa.apagarTarefa();
                    case 5 -> mostrarMenuInicial();
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }




        private static int solicitarOpcao() {
            System.out.print("Selecione uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.next();
            }

            return scanner.nextInt();
        }
    }
