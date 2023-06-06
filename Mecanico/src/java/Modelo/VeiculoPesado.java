package Modelo;

public class VeiculoPesado {
    private int idVeiculoPesado;
    private int idVeiculo;
    private int comprimentoMetros;
    private int numEixos;
    private int pesoToneladas;
    
    public VeiculoPesado(int idVeiculo, int comprimentoMetros, int numEixos, int pesoToneladas) {
        this.idVeiculo = idVeiculo;
        this.comprimentoMetros = comprimentoMetros;
        this.numEixos = numEixos;
        this.pesoToneladas = pesoToneladas;
    }
    
    public VeiculoPesado(int idVeiculoPesado, int idVeiculo, int comprimentoMetros, int numEixos, int pesoToneladas) {
        this.idVeiculoPesado = idVeiculoPesado;
        this.idVeiculo = idVeiculo;
        this.comprimentoMetros = comprimentoMetros;
        this.numEixos = numEixos;
        this.pesoToneladas = pesoToneladas;
    }
    
    public VeiculoPesado() {
        
    }

    public int getIdVeiculoPesado() {
        return idVeiculoPesado;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public int getComprimentoMetros() {
        return comprimentoMetros;
    }

    public int getNumEixos() {
        return numEixos;
    }

    public int getPesoToneladas() {
        return pesoToneladas;
    }

    public void setIdVeiculoPesado(int idVeiculoPesado) {
        this.idVeiculoPesado = idVeiculoPesado;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public void setComprimentoMetros(int comprimentoMetros) {
        this.comprimentoMetros = comprimentoMetros;
    }

    public void setNumEixos(int numEixos) {
        this.numEixos = numEixos;
    }

    public void setPesoToneladas(int pesoToneladas) {
        this.pesoToneladas = pesoToneladas;
    }
        
}