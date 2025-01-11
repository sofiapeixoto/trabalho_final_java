package Categoria;

public class Categoria {
    private int idCategoria;
    private String nome;

    public Categoria(int idCategoria, String nome) {
        this.idCategoria = idCategoria;
        this.nome = nome;
    }



    public int getId() {
        return idCategoria;
    }

    public void setId(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    //region Getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
