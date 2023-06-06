package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.VeiculoPesado;

// A classe VeiculoPesadoDAO provê as operações CRUD no banco de dados para tabela VeiculoPesado * 
public class VeiculoPesadoDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/oficinamecanica";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_VEICULOPESADO_SQL = "insert INTO veiculopesado " + "  (idVeiculo, comprimentoMetros, numEixos, pesoToneladas) VALUES "
            + " (?, ?, ?, ?);";
    private static final String SELECT_VEICULOPESADO_BY_ID = "select idVeiculoPesado, idVeiculo, comprimentoMetros, numEixos, pesoToneladas from veiculopesado where idVeiculoPesado =?";
    private static final String SELECT_ALL_VEICULOPESADO = "select * from veiculopesado";
    private static final String DELETE_VEICULOPESADO_SQL = "delete from veiculopesado where idVeiculoPesado = ?;";
    private static final String UPDATE_VEICULOPESADO_SQL = "update veiculopesado set idVeiculo=?, comprimentoMetros=?, numEixos=?, pesoToneladas=? where idVeiculoPesado = ?;";

    public VeiculoPesadoDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        return connection;
    }

    public void insertVeiculoPesado(VeiculoPesado vp) throws SQLException {
        System.out.println(INSERT_VEICULOPESADO_SQL);

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VEICULOPESADO_SQL)) {
            preparedStatement.setInt(1, vp.getIdVeiculo());
            preparedStatement.setInt(2, vp.getComprimentoMetros());
            preparedStatement.setInt(3, vp.getNumEixos());
            preparedStatement.setInt(4, vp.getPesoToneladas());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public VeiculoPesado selectVeiculoPesado(int id) {
        VeiculoPesado vp = null;
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VEICULOPESADO_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int idVeiculo = rs.getInt("idVeiculo");
                int comprimentoMetros = rs.getInt("comprimentoMetros");
                int numEixos = rs.getInt("numEixos");
                int pesoToneladas = rs.getInt("pesoToneladas");          
                vp = new VeiculoPesado(id, idVeiculo, comprimentoMetros, numEixos, pesoToneladas);
                System.out.println("selectVeiculoPesado: " + id + " " + idVeiculo + " " + comprimentoMetros + " " + numEixos + " " + pesoToneladas);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return vp;
    }

    public List< VeiculoPesado> selectAllVeiculoPesado() {

        List< VeiculoPesado> users = new ArrayList<>();
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VEICULOPESADO);) {
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int id = rs.getInt("idVeiculoPesado");
                int idVeiculo = rs.getInt("idVeiculo");
                int comprimentoMetros = rs.getInt("comprimentoMetros");
                int numEixos = rs.getInt("numEixos");
                int pesoToneladas = rs.getInt("pesoToneladas");
                users.add(new VeiculoPesado(id, idVeiculo, comprimentoMetros, numEixos, pesoToneladas));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteVeiculoPesado(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEICULOPESADO_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateVeiculoPesado(VeiculoPesado vp) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VEICULOPESADO_SQL);) {
            preparedStatement.setInt(1, vp.getIdVeiculo());
            preparedStatement.setInt(2, vp.getComprimentoMetros());
            preparedStatement.setInt(3, vp.getNumEixos());
            preparedStatement.setInt(4, vp.getPesoToneladas());
            preparedStatement.setInt(5, vp.getIdVeiculoPesado());
            System.out.println(preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
