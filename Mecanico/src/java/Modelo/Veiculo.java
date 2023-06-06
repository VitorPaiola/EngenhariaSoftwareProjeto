
package Modelo;

public class Veiculo {
    private int idVeiculo;
    private int idRevisao;
    private int idDono;
    private String placa;
    private int ano;
    private String RENAVEM;
    private String modelo;
    private String marca;

    public Veiculo( int idRevisao, int idDono, String placa, int ano, String RENAVEM, String modelo, String marca){
        this.idRevisao = idRevisao;
        this.idDono = idDono;
        this.placa = placa;
        this.ano = ano;
        this.RENAVEM = RENAVEM;
        this.modelo = modelo;
        this.marca = marca;
    }
    
    public Veiculo(int idVeiculo, int idRevisao, int idDono, String placa, int ano, String RENAVEM, String modelo, String marca){
        this.idVeiculo = idVeiculo;
        this.idRevisao = idRevisao;
        this.idDono = idDono;
        this.placa = placa;
        this.ano = ano;
        this.RENAVEM = RENAVEM;
        this.modelo = modelo;
        this.marca = marca;
    }
    
    public Veiculo(){
        
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public int getIdRevisao() {
        return idRevisao;
    }

    public int getIdDono() {
        return idDono;
    }

    public String getPlaca() {
        return placa;
    }

    public int getAno() {
        return ano;
    }

    public String getRENAVEM() {
        return RENAVEM;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public void setIdRevisao(int idRevisao) {
        this.idRevisao = idRevisao;
    }

    public void setIdDono(int idDono) {
        this.idDono = idDono;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setRENAVEM(String RENAVEM) {
        this.RENAVEM = RENAVEM;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
   
}