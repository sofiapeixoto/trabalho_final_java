package Utilizador;

import Categoria.Categoria;
import Tarefa.Tarefa;

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

    private static final String ficheiroCategorias = "src/categorias.txt";
    private static final String ficheiroTarefas = "src/tarefas.txt";

    private List<Categoria> categorias;
    private List<Tarefa> tarefas;

    public Utilizador() {
        carregarCategorias();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void adicionarCategoria(int idCategoria, String nome) {

        boolean nomeExiste = nomeExistenteString(nome);

        if (nomeExiste) {
            System.err.println("O nome " + nome + " já existe no sistema.");
            return;
        }

        int id = obterIdProximaCategoria();

        Categoria novaCategoria= new Categoria(id, nome);

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

    public void apagarCategoria()  throws IOException {
        listarCategorias();
        System.out.println("Selecione uma categoria para apagar (através do id): ");

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

    public Boolean nomeExistenteString(String novoNome) {

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

                    Tarefa tarefa = new Tarefa(id, nome, prazo, categoria);
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

