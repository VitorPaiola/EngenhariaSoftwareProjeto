
package Modelo;

public class Dono {
    private int idDono;
    private int CNH;
    private String nome;
    private String endereco;
    private String telefone;

    // construtor usando para criar objeto usuário antes de cadastrar no BD. 
    // Após o cadastramento o banco criar o idDono e 
    // insere com os demais dados do novo usuário
    public Dono(int CNH, String nome, String endereco, String telefone) {
        this.CNH = CNH;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Dono(int idDono, int CNH, String nome, String endereco, String telefone) {
        this.idDono = idDono;
        this.CNH = CNH;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Dono() {
    }

    public int getIdDono() {
        return idDono;
    }
    
    public int getCNH() {
        return CNH;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setIdDono(int idDono) {
        this.idDono = idDono;
    }
    
    public void setCNH(int CNH) {
        this.CNH = CNH;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
