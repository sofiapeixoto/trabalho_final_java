package Tarefa;

import Categoria.Categoria;
import Utilizador.Utilizador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Tarefa {
    private int idTarefa;
    private String nome;
    private LocalDateTime prazo;
    private Categoria categoria;
    private List<Categoria> categorias;
    private List<Utilizador> nomeUtilizador;



    public Tarefa(int idTarefa,String nome, Categoria categoria, LocalDateTime prazo, Utilizador utilizador) {
        this.idTarefa = idTarefa;
        this.nome = nome;
        this.categoria = categoria;
        this.prazo = prazo;
        this.categorias = new ArrayList<>();
        this.categorias.add(categoria);
        this.nomeUtilizador= new ArrayList<>();
        this.nomeUtilizador.add(utilizador);
    }


    public Tarefa(int idTarefa, String nome, LocalDateTime prazo, String categoria, String nomeUtilizador) {
    }

    public int getId() {
        return idTarefa;
    }

    public void setId(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    //region Getters e setters

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public LocalDateTime getPrazo() {

        return prazo;
    }

    public void setPrazo(LocalDateTime prazo) {

        this.prazo = prazo;
    }

    public Categoria getCategoria() {

        return categoria;
    }

    public void setCategoria(Categoria categoria) {

        this.categoria = categoria;
    }

    public void adicionarCategoria(Categoria categoria) {

        categorias.add(categoria);
    }

    public String formatarPrazo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return prazo.format(formatter);
    }

    public void setNomeUtilizador(Utilizador utilizador) {
        this.nomeUtilizador = nomeUtilizador;
    }
    public Utilizador getNomeUtilizador() {
        return nomeUtilizador.get(0);
    }
}