package Tarefa;

import Categoria.Categoria;
import Utilizador.Utilizador;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TarefaCategoria {
    private int idTarefa;
    private int idCategoria;



    private static final String ficheiroTarefas = "src/tarefas.txt.txt";
    private static final String ficheiroCategorias = "src/categorias.txt.txt";
    private static final String ficheiroUtilizadores = "src/utilizadores.txt.txt";

    private List<Categoria> categorias;
    private List<Tarefa> tarefas;
    private List<Utilizador> utilizadores;

    public TarefaCategoria() {

        carregarCategorias();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void adicionarCategoria(String nome, int idCategoria ) {

        boolean idCategoriaExiste = idCategoriaExistente(idCategoria);

        if (idCategoriaExiste) {
            System.err.println("A categoria: " + nome + " já existe no sistema, não pode ser adiconada.");
            return;
        }

        int id = obterIdProximaCategoria();

        Categoria novaCategoria = new Categoria(idCategoria, nome);

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

    public void listarTarefas() {

        carregarTarefas();

        for (Tarefa tarefa : tarefas) {
            System.out.println(tarefa.toString());
        }
    }

    public Boolean idCategoriaExistenteInt(int novoIdCategoria) {

        for (Categoria categoria : categorias) {

            int idCategoriaExistente = categoria.getId();

            if (Objects.equals(novoIdCategoria, idCategoriaExistente)) {
                return true;
            }
        }

        return false;
    }

    public Boolean idCategoriaExistenteCategoria(Categoria categoriaAtualizada) {
        carregarCategorias();

        for (Categoria idCategoriaExistente : categorias) {
            if (categoriaAtualizada.getId() != idCategoriaExistente.getId()) {

                if (Objects.equals(categoriaAtualizada.getId(), idCategoriaExistente.getId())) {
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
                    int idCategoria = Integer.parseInt(dados[0].trim());
                    String nome = dados[1].trim();

                    Categoria categoria = new Categoria(idCategoria, nome);
                    categorias.add(categoria);
                } else {
                    System.out.println("Linha inválida no ficheiro: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    private void carregarTarefas() {

        tarefas = new ArrayList<>();
        utilizadores= new ArrayList<>();

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

                    Tarefa tarefa = new Tarefa(idTarefa, nome, prazo, categoria, nomeUtilizador);
                    tarefas.add(tarefa);
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

            bw.newLine();
            bw.write(linha);

            System.out.println("Categoria.Categoria adicionada com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }

    public static void guardarCategoriaFicheiro(List<Categoria> categorias) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheiroCategorias, true))) {

            for (Categoria categoria : categorias) {
                String linha = categoria.getId() + "," + categoria.getNome();

                bw.newLine();
                bw.write(linha);

            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }


    public void escreverCategorias(List<Categoria> categorias) throws IOException {
        limparFicheiro(ficheiroCategorias);
        guardarCategoriaFicheiro(categorias);
    }

    public void limparFicheiro(String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

        } catch (Exception e) {
            System.err.println("Erro ao apagar ficheiro");
        }
    }
}
