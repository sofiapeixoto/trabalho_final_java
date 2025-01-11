package Tarefa;

import Categoria.Categoria;
import Utilizador.Utilizador;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


    public final class MenuTarefa {
        private final Utilizador utilizador = new Utilizador();
        private static final Scanner scanner = new Scanner(System.in);

        private static final String ficheiroLogin = "src/loginUtilizador.txt";

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
            String nomeUtilizador = lerNomeUtilizadorDoFicheiro();
            if (nomeUtilizador == null) {
                System.err.println("Erro: Nenhum utilizador encontrado no arquivo de login.");
                return;
            }

            utilizador.adicionarTarefa(idTarefa, nome, prazo, nomeCategoria, nomeUtilizador, false);
            System.out.println("Tarefa adicionada com sucesso!");
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

            int idTarefa = solicitarOpcao();
            boolean idTarefaExiste = utilizador.idTarefaExistente(idTarefa);

            if (!idTarefaExiste) {
                System.err.println("Não foi encontrada nenhuma Tarefa com o id: " + idTarefa);
                return;
            }

            // Obtém a tarefa atual para manter o utilizador que já estava associado
            Tarefa tarefaExistente = tarefa.getId(idTarefa);

            if (tarefaExistente == null) {
                System.err.println("Tarefa não encontrada.");
                return;
            }

            // Obtém o utilizador atual (mantendo o mesmo utilizador da tarefa)
            String nomeUtilizador = tarefaExistente.getNomeUtilizador().getNome();  // Mantém o nome do utilizador da tarefa original

            // Digita o novo nome da tarefa
            System.out.println("Digite o novo nome da tarefa (deixe em branco para não alterar): ");
            String nome = lerString();
            if (nome.isEmpty()) {
                nome = tarefaExistente.getNome(); // Se o nome não for alterado, mantém o nome atual
            }

            // Digita o novo prazo da tarefa
            System.out.println("Digite o novo prazo da tarefa (yyyy-MM-dd HH:mm): ");
            String prazoStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime prazo = LocalDateTime.parse(prazoStr, formatter);

            // Digita a nova categoria da tarefa
            System.out.println("Digite a nova categoria da tarefa: ");
            String nomeCategoria = lerString();

            Categoria categoriaSelecionada = null;
            for (Categoria categoria : utilizador.getCategorias()) {
                if (categoria.getNome().equalsIgnoreCase(nomeCategoria)) {  // Compara os nomes ignorando o case
                    categoriaSelecionada = categoria;
                    break;
                }
            }

            // Atualizando a tarefa com os novos dados (mantendo o utilizador original)
            Tarefa tarefaAtualizada = new Tarefa(idTarefa, nome, categoriaSelecionada, prazo, tarefaExistente.getNomeUtilizador(), tarefaExistente.getEstaConcluida());

            // Verifica se o nome da tarefa já existe
            boolean nomeExiste = utilizador.nomeExistenteTarefa(tarefaAtualizada);
            if (nomeExiste) {
                System.err.println("Já existe uma tarefa com o nome: " + nome);
                return;
            }

            // Atualiza a tarefa
            utilizador.atualizarTarefa(tarefaAtualizada);
            System.out.println("Tarefa atualizada com sucesso!");
        }


        public void listarTarefas() throws IOException {
            System.out.println("************************");
            System.out.println("*** Tarefas: ***");

            for (Tarefa tarefa : utilizador.getTarefas()) {
                String estado = tarefa.getEstaConcluida() ? "Concluída" : "Pendente";
                System.out.println("ID: " + tarefa.getId() + " | Nome: " + tarefa.getNome() +
                        " | Prazo: " + tarefa.formatarPrazo() +
                        " | Categoria: " + tarefa.getCategoria().getNome() +
                        " | Estado: " + estado);
            }
        }
        private String lerNomeUtilizadorDoFicheiro() throws IOException {
            List<String> linhas = Files.readAllLines(Paths.get(ficheiroLogin)); // Lê todas as linhas do arquivo
            return linhas.isEmpty() ? null : linhas.get(0); // Retorna a primeira linha, ou null se o arquivo estiver vazio
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

        public void concluirTarefa() throws IOException {
            listarTarefas();
            System.out.println("Selecione uma tarefa para concluir (através do id): ");
            int idTarefa = solicitarOpcao();

            boolean idTarefaExiste = utilizador.idTarefaExistente(idTarefa);

            if (idTarefaExiste) {
                utilizador.concluirTarefa(idTarefa);
                System.out.println("Tarefa com id " + idTarefa + " marcada como concluída.");
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
                if (!valor.isEmpty() && !valor.isBlank()) {
                    break;
                }
                System.out.println("A string introduzida não é válida. Tente novamente.");
            }
            return valor;
        }
    }
