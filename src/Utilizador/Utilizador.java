package Utilizador;

import Categoria.Categoria;
import Tarefa.Tarefa;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Objects;

public class Utilizador {
private String nomeUtilizador;

    public String getNomeUtilizador() {
        return nomeUtilizador;
    }
    public void setNomeUtilizador(String nomeUtilizador) {
        this.nomeUtilizador = nomeUtilizador;
    }

    public Utilizador() {
        this.nomeUtilizador = "";
        this.categorias = new ArrayList<>();
        this.tarefas = new ArrayList<>();
        this.utilizadores = new ArrayList<>();
    }

    private static final String ficheiroCategorias = "src/categorias.txt";
    private static final String ficheiroTarefas = "src/tarefas.txt";
    private static final String ficheiroUtilizadores = "src/utilizadores.txt";
    private List<Categoria> categorias;
    private List<Tarefa> tarefas;
    private List<Utilizador> utilizadores;

    public String toString() {
        return nomeUtilizador;  // Retorna o nome do utilizador como representação do objeto
    }


    public List<Categoria> getCategorias() {
        return categorias;
    }



    public void adicionarCategoria(int idCategoria, String nome) {

        boolean nomeExiste = nomeExistenteString(nome);

        if (nomeExiste) {
            System.err.println("A categoria " + nome + " já existe no sistema.");
            return;
        }

        int id = obterIdProximaCategoria();

        Categoria novaCategoria= new Categoria(idCategoria, nome);

        guardarCategoriaFicheiro(novaCategoria);

    }

    public void atualizarCategoria(Categoria categoriaAtualizada) throws IOException {
        carregarCategorias();

        for (Categoria categoria : categorias) {

            if (categoria.getId() == categoriaAtualizada.getId()) {
                categoria.setNome(categoriaAtualizada.getNome());
            }
        }

        escreverCategorias(categorias);
    }

    public void apagarCategoria(int idCategoriaParaApagar)  throws IOException {
        carregarCategorias();
        Categoria categoriaParaApagar = null;
        // Procura pela categoria com o ID informado
        for (Categoria categoria : categorias) {
            if (categoria.getId() == idCategoriaParaApagar) {
                categoriaParaApagar = categoria;
                break;
            }
        }

        // Se encontrar a categoria, a remove
        if (categoriaParaApagar != null) {
            categorias.remove(categoriaParaApagar);
            System.out.println("Categoria removida com sucesso.");
            // Atualiza o arquivo com as categorias.txt restantes
            escreverCategorias(categorias);
        } else {
            System.out.println("Categoria não encontrada com o ID: " + idCategoriaParaApagar);
        }

    }



    public boolean idCategoriaExistente(int idIntroduzido) {
        carregarCategorias();

        for (Categoria categoria : categorias) {
            if (categoria.getId() == idIntroduzido) {
                return true;
            }
        }
        return false;
    }

    private int obterIdProximaCategoria() {

        int id = 0;
        for (Categoria categoria : categorias) {
            if (categoria.getId() >= id) {
                id = categoria.getId() + 1;
            }
        }

        return id;
    }

    public void listarCategorias() {

        carregarCategorias();

        for (Categoria categoria : categorias) {
            System.out.println(categoria.toString());
        }
    }


    public Boolean nomeCategoriaExistenteString(String novoNome) {

        for (Categoria categoria : categorias) {

            String nomeCategoriaExistente = categoria.getNome();

            if (Objects.equals(novoNome, nomeCategoriaExistente)) {
                return true;
            }
        }

        return false;
    }

    public Boolean nomeExistenteCategoria(Categoria categoriaAtualizada) {
        carregarCategorias();

        for (Categoria categoriaExistente : categorias) {
            if (categoriaAtualizada.getId() != categoriaExistente.getId()) {

                if (Objects.equals(categoriaAtualizada.getNome(), categoriaExistente.getNome())) {
                    return true;
                }
            }
        }

        return false;
    }

    private void carregarCategorias() {

        categorias = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiroCategorias))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length == 4) {
                    int id = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();

                    Categoria categoria = new Categoria(id, nome);
                    categorias.add(categoria);
                } else {
                    System.out.println("Linha inválida no ficheiro: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }



    public static void guardarCategoriaFicheiro(Categoria categoria) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiroCategorias, true))) {

            String linha = categoria.getId() + "," + categoria.getNome();
            bw.write(linha);
            bw.newLine();

            System.out.println("Categoria adicionada com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }

    public static void guardarCategoriaFicheiro(List<Categoria> categorias) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiroCategorias, true))) {

            for (Categoria categoria : categorias) {
                String linha = categoria.getId() + "," + categoria.getNome();

                bw.write(linha);
                bw.newLine();

            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }


    public void escreverCategorias(List<Categoria> categorias) throws IOException {
        limparFicheiroCategoria(ficheiroCategorias);
        guardarCategoriaFicheiro(categorias);
    }

    public void limparFicheiroCategoria(String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // Apenas abrir o arquivo em modo de escrita limpa o conteúdo.
        } catch (IOException e) {
            System.err.println("Erro ao apagar conteúdo do ficheiro " + e.getMessage());
        }
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }


    public void listarTarefas() {

        carregarTarefas();

        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa.toString());
        }
    }

    private void carregarTarefas() {

        tarefas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiroTarefas))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length == 5) {
                    int id = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime prazo = LocalDateTime.parse(dados[2].trim());
                    String categoria= dados[3].trim();
                    String nomeUtilizador= dados[4].trim();
                    Boolean estaConcluida = Boolean.parseBoolean(dados[5].trim());

                    Tarefa tarefa = new Tarefa(id, nome, prazo, categoria, nomeUtilizador,estaConcluida);
                    tarefas.add(tarefa);
                } else {
                    System.out.println("Linha inválida no ficheiro: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    public void adicionarTarefa(int idTarefa, String nome, LocalDateTime prazo, String categoria, String nomeUtilizador, boolean estaConcluida) {

        boolean nomeExiste = nomeExistenteString(nome);

        if (nomeExiste) {
            System.err.println("A tarefa " + nome + " já existe no sistema.");
            return;
        }

        int id = obterIdProximaTarefa();

        Tarefa novaTarefa= new Tarefa(id, nome, prazo, categoria, nomeUtilizador, estaConcluida);

        guardarTarefaFicheiro(novaTarefa);

    }

    public void atualizarTarefa(Tarefa tarefaAtualizada) throws IOException {
        carregarTarefas();

        for (Tarefa tarefa : tarefas) {

            if (tarefa.getId() == tarefaAtualizada.getId()) {
                tarefa.setNome(tarefaAtualizada.getNome());
                tarefa.setPrazo(tarefaAtualizada.getPrazo());
                tarefa.setCategoria(tarefaAtualizada.getCategoria());
            }
        }

        escreverTarefas(tarefas);
    }

    public void apagarTarefa(int idTarefaParaApagar)  throws IOException {
        carregarTarefas();
        Tarefa tarefaParaApagar = null;
        // Procura pela categoria com o ID informado
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == idTarefaParaApagar) {
                tarefaParaApagar = tarefa;
                break;
            }
        }

        // Se encontrar a categoria, a remove
        if (tarefaParaApagar != null) {
            tarefas.remove(tarefaParaApagar);
            System.out.println("Tarefa removida com sucesso.");
            // Atualiza o arquivo com as categorias.txt restantes
            escreverTarefas(tarefas);
        } else {
            System.out.println("Tarefa não encontrada com o ID: " + idTarefaParaApagar);
        }

    }



    public boolean idTarefaExistente(int idIntroduzido) {
        carregarTarefas();

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == idIntroduzido) {
                return true;
            }
        }
        return false;
    }

    private int obterIdProximaTarefa() {

        int id = 0;
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() >= id) {
                id = tarefa.getId() + 1;
            }
        }

        return id;
    }

    public Boolean nomeExistenteString(String novoNome) {

        for (Tarefa tarefa : tarefas) {

            String nomeTarefaExistente = tarefa.getNome();

            if (Objects.equals(novoNome, nomeTarefaExistente)) {
                return true;
            }
        }

        return false;
    }

    public Boolean nomeExistenteTarefa(Tarefa tarefaAtualizada) {
        carregarTarefas();

        for (Tarefa tarefaExistente : tarefas) {
            if (tarefaAtualizada.getId() != tarefaExistente.getId()) {

                if (Objects.equals(tarefaAtualizada.getNome(), tarefaExistente.getNome())) {
                    return true;
                }
            }
        }

        return false;
    }

    public void concluirTarefa(int idTarefa) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getId() == idTarefa) {
                tarefa.setEstaConcluida(true);
                break;
            }
        }
    }



    public static void guardarTarefaFicheiro(Tarefa tarefa) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiroTarefas, true))) {

            String linha = tarefa.getId() + "," + tarefa.getNome() + "," + tarefa.getPrazo() + "," + tarefa.getCategoria();
            bw.write(linha);
            bw.newLine();

            System.out.println("Tarefa adicionada com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }

    public static void guardarTarefaFicheiro(List<Tarefa> tarefas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiroTarefas, true))) {

            for (Tarefa tarefa : tarefas) {
                String linha = tarefa.getId() + "," + tarefa.getNome() + "," + tarefa.getPrazo() + "," + tarefa.getCategoria();

                bw.write(linha);
                bw.newLine();

            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }


    public void escreverTarefas(List<Tarefa> tarefas) throws IOException {
        limparFicheiroTarefas(ficheiroTarefas);
        guardarTarefaFicheiro(tarefas);
    }

    public void limparFicheiroTarefas(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // Apenas abrir o arquivo em modo de escrita limpa o conteúdo.
        } catch (IOException e) {
            System.err.println("Erro ao apagar conteúdo do ficheiro "+ e.getMessage());
        }
    }




    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }


    public void listarSelecionarUtilizador() {

        carregarUtilizadores();

        for (Utilizador utilizador : utilizadores) {
            System.out.println(utilizador.toString());
        }
    }

    private void carregarUtilizadores() {
        utilizadores = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiroUtilizadores))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length == 1) {  // Supõe-se que cada linha tem apenas o nome do utilizador
                    String nomeUtilizador = dados[0].trim();

                    Utilizador novoUtilizador = new Utilizador();
                    novoUtilizador.setNomeUtilizador(nomeUtilizador);  // Defina o nome do utilizador
                    utilizadores.add(novoUtilizador);
                } else {
                    System.out.println("Linha inválida no ficheiro: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    public void adicionarUtilizador(String nomeUtilizador) {
        boolean nomeUtilizadorExiste = nomeUtilizadorExistenteString(nomeUtilizador);

        if (nomeUtilizadorExiste) {
            System.err.println("O usernaem " + nomeUtilizador + " já existe no sistema.");
            return;
        }

        Utilizador novoUtilizador = new Utilizador();
        novoUtilizador.setNomeUtilizador(nomeUtilizador); // Defina o nome do utilizador
        guardarUtilizadorFicheiro(novoUtilizador);
    }


    public void alterarUtilizador(Utilizador utilizadorAlterado) throws IOException {
        carregarUtilizadores();

        for (Utilizador utilizador : utilizadores) {

            if (utilizador.getNomeUtilizador().equals(utilizadorAlterado.getNomeUtilizador())) {
               utilizador.setNomeUtilizador(utilizadorAlterado.getNomeUtilizador());
            }
        }

        escreverUtilizador(utilizadores);
    }

    public void apagarUtilizador(String nomeUtilizadorApagar)  throws IOException {
        carregarUtilizadores();
        Utilizador utilizadorApagar = null;
        // Procura pelo nome
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getNomeUtilizador().equals(nomeUtilizadorApagar)) {
                utilizadorApagar = utilizador;
                break;
            }
        }

        // Se encontrar o nome, remove
        if (utilizadorApagar != null) {
            utilizadores.remove(utilizadorApagar);
            System.out.println("Utilizador removido com sucesso.");
            // Atualiza o arquivo com os nomes restantes
            escreverUtilizador(utilizadores);
        } else {
            System.out.println("Utilizador não encontrado com o username: " + nomeUtilizadorApagar);
        }

    }



    public boolean nomeUtilizadorExistente(String nomeUtilizador) {
        carregarUtilizadores();

        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getNomeUtilizador().equals(nomeUtilizador)){
                return true;
            }
        }
        return false;
    }



    public Boolean nomeUtilizadorExistenteString(String novoNomeUtilizador) {

        for (Utilizador utilizador : utilizadores) {

            String nomeUtilizadorExistente = utilizador.getNomeUtilizador();

            if (Objects.equals(novoNomeUtilizador,nomeUtilizadorExistente)) {
                return true;
            }
        }

        return false;
    }

    public Boolean nomeUtilizadorExistente(Utilizador utilizadorAlterado) {
        carregarUtilizadores();

        for (Utilizador utilizadorExistente : utilizadores) {
            if (utilizadorAlterado.getNomeUtilizador() != utilizadorExistente.getNomeUtilizador()) {

                if (Objects.equals(utilizadorAlterado.getNomeUtilizador(), utilizadorExistente.getNomeUtilizador())) {
                    return true;
                }
            }
        }

        return false;
    }


    public static void guardarUtilizadorFicheiro(Utilizador utilizador) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiroUtilizadores, true))) {
            // Verifique se o nome já existe antes de adicionar
            BufferedReader br = new BufferedReader(new FileReader(ficheiroUtilizadores));
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().equals(utilizador.getNomeUtilizador())) {
                    System.err.println("O utilizador já existe no sistema.");
                    return;
                }
            }
            bw.newLine();
            bw.write(utilizador.getNomeUtilizador());
            System.out.println("Utilizador adicionado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }


    private void guardarUtilizadoresFicheiro(List<Utilizador> utilizadores) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiroUtilizadores))) {
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




    public void escreverUtilizador(List<Utilizador> utilizadores) throws IOException {
        limparFicheiroUtilizadores(ficheiroUtilizadores);
        guardarUtilizadoresFicheiro(utilizadores);
    }

    public void limparFicheiroUtilizadores(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            // Apenas abrir o arquivo em modo de escrita limpa o conteúdo.
        } catch (IOException e) {
            System.err.println("Erro ao apagar conteúdo do ficheiro " + e.getMessage());
        }
    }

}


