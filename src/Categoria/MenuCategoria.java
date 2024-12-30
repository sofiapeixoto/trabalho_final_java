package Categoria;
import java.io.IOException;
import java.util.Scanner;
import Tarefa.TarefaCategoria;
import Utilizador.Utilizador;


public final class MenuCategoria {
        private final Utilizador utilizador = new Utilizador();
        private static final Scanner scanner = new Scanner(System.in);

        public MenuCategoria() {
        }

        public void adicionarCategoria() {

            System.out.println("************************");
            System.out.println("*** Adicionar Categoria ***");

            System.out.println("Introduza o nome da Categoria: ");
            String nome = lerString();

            System.out.println("Introduza o id da Categoria: ");
            int idCategoria = scanner.nextInt();
            scanner.nextLine();

            utilizador.adicionarCategoria(idCategoria,nome);

        }

        public void atualizarCategoria() throws IOException {

            listarCategorias();
            System.out.println("Selecione uma Categoria (através do id): ");

            int idCategoria= solicitarOpcao();
            boolean idCategoriaExiste = utilizador.idCategoriaExistente(idCategoria);

            if (!idCategoriaExiste) {
                System.err.println("Não foi encontrado nenhuma categoria com o id: " +idCategoria);
            }

            System.out.println("Digite o nome: ");
            String nome = lerString();

            Categoria categoriaAtualizada = new Categoria(idCategoria, nome);

            boolean nomeExiste = utilizador.nomeExistenteCategoria(categoriaAtualizada);
            if (nomeExiste) {
                System.err.println("Já existe uma categoria com o nome: " + nome);
            }
                utilizador.atualizarCategoria(categoriaAtualizada);
        }

        public void listarCategorias() {

            System.out.println("************************");
            System.out.println("*** Categorias: ***");

            utilizador.listarCategorias();
        }

        public void apagarCategoria()throws IOException{
            listarCategorias();
            System.out.println("Selecione uma categoria para apagar (através do id): ");
            int idCategoria = solicitarOpcao();

            boolean idCategoriaExiste = utilizador.idCategoriaExistente(idCategoria);

            if (idCategoriaExiste) {
                // Chama o método de remoção da classe Utilizador
                utilizador.apagarCategoria(idCategoria);
                System.out.println("Categoria com id " + idCategoria + " removida com sucesso.");
            } else {
                System.err.println("Categoria não encontrada com o ID: " + idCategoria);
            }
        }

        private static int solicitarOpcao() {

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.next();
            }

            return scanner.nextInt();
        }

        private static String lerString() {
            String valor;

            while (true) {
                valor = scanner.nextLine();

                // sai do loop se a string for válida
                if (!valor.isEmpty() && !valor.isBlank()) {
                    break;
                }

                System.out.println("A string introduzida não é válida. Tente novamente.");
            }

            return valor;
        }
}

