
package Modelo;
import java.util.Date;

public class Revisao {
    private int idRevisao;
    private int idMeca;
    private String datahora;
    private String relatorio;
    
    public Revisao(int idMeca, String datahora, String relatorio) {
        this.idMeca = idMeca;
        this.datahora = datahora;
        this.relatorio = relatorio;
    }
    
    public Revisao(int idRevisao, int idMeca, String datahora, String relatorio) {
        this.idRevisao = idRevisao;
        this.idMeca = idMeca;
        this.datahora = datahora;
        this.relatorio = relatorio;
    }    
    
    public Revisao() {
        
    }

    public int getIdRevisao() {
        return idRevisao;
    }

    public int getIdMeca() {
        return idMeca;
    }

    public String getDatahora() {
        return datahora;
    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setIdRevisao(int idRevisao) {
        this.idRevisao = idRevisao;
    }

    public void setIdMeca(int idMeca) {
        this.idMeca = idMeca;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }
    
}