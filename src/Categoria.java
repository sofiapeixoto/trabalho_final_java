public class Categoria {
    private int idcategoria;
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(int id, String nome) {
        this.idcategoria = id;
        this.nome = nome;
    }

    public int getId() {
        return idcategoria;
    }

    public void setId(int id) {
        this.idcategoria = id;
    }

    //region Getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
