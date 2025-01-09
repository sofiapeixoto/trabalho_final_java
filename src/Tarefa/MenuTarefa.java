package Tarefa;

import Categoria.Categoria;
import Utilizador.Utilizador;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

    public final class MenuTarefa {
        private final Utilizador utilizador = new Utilizador();
        private static final Scanner scanner = new Scanner(System.in);

        public MenuTarefa() throws IOException {
        }

        public void adicionarTarefas() {

            System.out.println("************************");
            System.out.println("*** Adicionar Tarefa ***");

            System.out.println("Introduza o nome da Tarefa: ");
            String nome = lerString();

            System.out.println("Introduza o id da Tarefa: ");
            int idTarefa = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Introduza o prazo da Tarefa (yyyy-MM-dd HH:mm): ");
            String prazoStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime prazo = LocalDateTime.parse(prazoStr, formatter);

            System.out.println("Introduza a categoria da Tarefa: ");
            String nomeCategoria = lerString();

            Categoria categoriaSelecionada = null;
            for (Categoria categoria : utilizador.getCategorias()) {
                if (categoria.getNome().equalsIgnoreCase(nomeCategoria)) {  // Compara os nomes ignorando o case
                    categoriaSelecionada = categoria;
                    break;
                }
            }
            System.out.println("Introduza o nome do Utilizador: ");
            String nomeUtilizador = lerString();
            nomeUtilizadorExistenteString(nomeUtilizador);
            utilizador.adicionarTarefa(idTarefa,nome,prazo,nomeCategoria,nomeUtilizador);

        }
        public void nomeUtilizadorExistenteString(String nomeUtilizador) {

            boolean nomeUtilizadorExiste = utilizador.nomeUtilizadorExistenteString(nomeUtilizador);

            if (!nomeUtilizadorExiste) {
                utilizador.adicionarUtilizador(nomeUtilizador);
            }
        }

        public void alterarTarefa() throws IOException {

            listarTarefas();
            System.out.println("Selecione uma Tarefa (através do id): ");

            int idTarefa= solicitarOpcao();
            boolean idTarefaExiste = utilizador.idTarefaExistente(idTarefa);

            if (!idTarefaExiste) {
                System.err.println("Não foi encontrado nenhuma Tarefa com o id: " +idTarefa);
            }

            System.out.println("Digite o nome: ");
            String nome = lerString();

            System.out.println("Digite o prazo: ");
            String prazoStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime prazo = LocalDateTime.parse(prazoStr, formatter);

            System.out.println("Digite a categoria da Tarefa: ");
            String nomeCategoria = lerString();

            System.out.println("Digite o nome de Utilizador: ");
            String nomeUtilizador = lerString();
            nomeUtilizadorExistenteString(nomeUtilizador);

            Tarefa tarefaAtualizada = new Tarefa(idTarefa, nome, prazo, nomeCategoria,nomeUtilizador);

            boolean nomeExiste = utilizador.nomeExistenteTarefa(tarefaAtualizada);
            if (nomeExiste) {
                System.err.println("Já existe uma tarefa com o nome: " + nome);
            }
            utilizador.atualizarTarefa(tarefaAtualizada);
        }

        public void listarTarefas() throws IOException {

            System.out.println("************************");
            System.out.println("*** Tarefas: ***");

            utilizador.listarTarefas();
        }

        public void apagarTarefa()throws IOException{
            listarTarefas();
            System.out.println("Selecione uma tarefa para apagar (através do id): ");
            int idTarefa = solicitarOpcao();

            boolean idTarefaExiste = utilizador.idTarefaExistente(idTarefa);

            if (idTarefaExiste) {
                utilizador.apagarTarefa(idTarefa);
                System.out.println("Tarefa com id " + idTarefa + " removida com sucesso.");
            } else {
                System.err.println("Tarefa não encontrada com o ID: " + idTarefa);
            }
        }

        public void concluirTarefa()throws IOException{
            listarTarefas();
            System.out.println("Selecione uma tarefa para concluir (através do id): ");
            int idTarefa = solicitarOpcao();
            boolean idTarefaExiste = utilizador.idTarefaExistente(idTarefa);

            if (idTarefaExiste) {
                utilizador.concluirTarefa();
                System.out.println("Tarefa com id " + idTarefa + " concluída com sucesso.");
            } else {
                System.err.println("Tarefa não encontrada com o ID: " + idTarefa);
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
