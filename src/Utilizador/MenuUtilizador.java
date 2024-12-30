package Utilizador;

import Categoria.Categoria;
import Tarefa.Tarefa;
import Categoria.Categoria;
import Utilizador.Utilizador;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class MenuUtilizador {

        private final Utilizador utilizador = new Utilizador();
        private static final Scanner scanner = new Scanner(System.in);

        public MenuUtilizador() throws IOException {
        }

        public void adicionarUtilizador() {

            System.out.println("************************");
            System.out.println("*** Adicionar Utilizador ***");

            System.out.println("Introduza o nome do utilizador: ");
            String nomeUtilizador = lerString();

            utilizador.adicionarUtilizador(nomeUtilizador);

        }

        public void alterarUtilizador() throws IOException {

            listarSelecionarUtilizador();
            System.out.println("Selecione um Utilizador (através do nome): ");

            String nomeUtilizador= lerString();

            boolean nomeUtilizadorExiste = utilizador.nomeUtilizadorExistente(nomeUtilizador);

            if (!nomeUtilizadorExiste) {
                System.err.println("Não foi encontrado nenhum Utilizador com o nome: " +nomeUtilizador);
            }

            System.out.println("Digite o nome: ");
            String nome = lerString();


            Utilizador utilizadorAlterado = new Utilizador();

            boolean utilizadorExiste= utilizador.nomeUtilizadorExistente(utilizadorAlterado);

            if (nomeUtilizadorExiste) {
                System.err.println("Já existe um utilizador com o nome: " + nomeUtilizador);
            }
            utilizador.alterarUtilizador(utilizadorAlterado);
        }

        public void listarSelecionarUtilizador() throws IOException {

            System.out.println("************************");
            System.out.println("*** Utilizador: ***");
            listarSelecionarUtilizador();
            System.out.println("Selecione um utilizador (através do nome): ");
            String nomeUtilizador = lerString();

            boolean nomeUtilizadorExiste= utilizador.nomeUtilizadorExistente(nomeUtilizador);

        }

        public void apagarUtilizador()throws IOException{
            listarSelecionarUtilizador();
            System.out.println("Selecione um utilizador para apagar (através do nome): ");
            String nomeUtilizador = lerString();

            boolean nomeUtilizadorExiste = utilizador.nomeUtilizadorExistente(nomeUtilizador);

            if (nomeUtilizadorExiste) {
                // Chama o método de remoção da classe Utilizador
                utilizador.apagarUtilizador(nomeUtilizador);
                System.out.println("O utilizador com o nome " + nomeUtilizador + " foi removido com sucesso.");
            } else {
                System.err.println("Utilizador não encontrado com o nome: " + nomeUtilizador);
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

