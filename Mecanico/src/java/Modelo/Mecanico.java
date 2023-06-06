
package Modelo;

public class Mecanico {
    private int idMeca;
    private int CREA;
    private String nome;
    private String endereco;
    private int celular;
    private int CPF;
    
    // construtor usando para criar objeto usuário antes de cadastrar no BD. 
    // Após o cadastramento o banco criar o idCli e 
    // insere com os demais dados do novo usuário
    public Mecanico(int CREA, String nome, String endereco, int celular, int CPF) {
        this.CREA = CREA;
        this.nome = nome;
        this.endereco = endereco;
        this.celular = celular;
        this.CPF = CPF;
    }
    
    public Mecanico(int idMeca, int CREA, String nome, String endereco, int celular, int CPF) {
        this.idMeca = idMeca;
        this.CREA = CREA;
        this.nome = nome;
        this.endereco = endereco;
        this.celular = celular;
        this.CPF = CPF;
    }
    
    public Mecanico() {
        
    }

    public int getIdMeca() {
        return idMeca;
    }
    
     public int getCREA() {
        return CREA;
    }
     
     public String getNome() {
        return nome;
    }
     
    public String getEndereco() {
        return endereco;
    }
    
     public int getCelular() {
        return celular;
    }
     
    public int getCPF() {
        return CPF;
    }
    
    public void setIdMeca(int idMeca) {
        this.idMeca = idMeca;
    }
 
    public void setCREA(int CREA) {
        this.CREA = CREA;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void setCelular(int celular) {
        this.celular = celular;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }
    
}
