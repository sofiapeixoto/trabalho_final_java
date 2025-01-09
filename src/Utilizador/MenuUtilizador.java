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

            System.out.println("Introduza o username do utilizador: ");
            String nomeUtilizador = lerString();

            utilizador.adicionarUtilizador(nomeUtilizador);

        }

    public void alterarUtilizador() throws IOException {
        utilizador.listarSelecionarUtilizador();
        System.out.println("Seleciona um utilizador (através do username): ");
        String nomeUtilizadorAntigo = lerString();

        boolean nomeUtilizadorExiste = utilizador.nomeUtilizadorExistente(nomeUtilizadorAntigo);

        if (!nomeUtilizadorExiste) {
            System.err.println("Não foi encontrado nenhum utilizador com o username: " + nomeUtilizadorAntigo);
            return;
        }

        System.out.println("Digite o novo username: ");
        String novoNomeUtilizador = lerString();

        boolean novoNomeExiste = utilizador.nomeUtilizadorExistente(novoNomeUtilizador);

        if (novoNomeExiste) {
            System.err.println("Já existe um utilizador com o username: " + novoNomeUtilizador);
            return;
        }

        // A variável u é declarada como do tipo Utilizador. A cada iteração do loop,
        // ela recebe um dos objetos da lista retornada por utilizador.getUtilizadores().

        for (Utilizador u : utilizador.getUtilizadores()) {
            if (u.getNomeUtilizador().equals(nomeUtilizadorAntigo)) {
                u.setNomeUtilizador(novoNomeUtilizador);
                break;
            }
        }

        utilizador.escreverUtilizador(utilizador.getUtilizadores());
        System.out.println("Username alterado com sucesso!");
    }


    //public void listarSelecionarUtilizador()  {

            //System.out.println("************************");
            //System.out.println("*** Utilizador: ***");
            //utilizador.listarSelecionarUtilizador();
            //System.out.println("Selecione um utilizador (através do nome): ");
            //String nomeUtilizador = lerString();

            //boolean nomeUtilizadorExiste= utilizador.nomeUtilizadorExistente(nomeUtilizador);

        //}

        public void apagarUtilizador()throws IOException{
            utilizador.listarSelecionarUtilizador();
            System.out.println("Selecione um utilizador para apagar (através do username): ");
            String nomeUtilizador = lerString();

            boolean nomeUtilizadorExiste = utilizador.nomeUtilizadorExistente(nomeUtilizador);

            if (nomeUtilizadorExiste) {
                utilizador.apagarUtilizador(nomeUtilizador);
                System.out.println("O utilizador com o username " + nomeUtilizador + " foi removido com sucesso.");
            } else {
                System.err.println("Utilizador não encontrado com o username: " + nomeUtilizador);
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

