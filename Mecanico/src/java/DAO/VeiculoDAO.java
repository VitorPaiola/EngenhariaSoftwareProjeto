package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Veiculo;

// A classe VeiculoDAO provê as operações CRUD no banco de dados para tabela Veiculo * 
public class VeiculoDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/oficinamecanica";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_VEICULO_SQL = "insert INTO veiculo " + "  (idRevisao, idDono, placa, ano, RENAVEM, modelo, marca) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_VEICULO_BY_ID = "select idVeiculo, idRevisao, idDono, placa, ano, RENAVEM, modelo, marca from veiculo where idVeiculo =?";
    private static final String SELECT_ALL_VEICULO = "select * from veiculo";
    private static final String DELETE_VEICULO_SQL = "delete from veiculo where idVeiculo = ?;";
    private static final String UPDATE_VEICULO_SQL = "update veiculo set idRevisao=?, idDono=?, placa=?, ano=?, RENAVEM=?, modelo=?, marca=? where idVeiculo = ?;";

    public VeiculoDAO() {
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

    public void insertVeiculo(Veiculo v) throws SQLException {
        System.out.println(INSERT_VEICULO_SQL);

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VEICULO_SQL)) {
            preparedStatement.setInt(1, v.getIdRevisao());
            preparedStatement.setInt(2, v.getIdDono());
            preparedStatement.setString(3, v.getPlaca());
            preparedStatement.setInt(4, v.getAno());
            preparedStatement.setString(5, v.getRENAVEM());
            preparedStatement.setString(6, v.getModelo());
            preparedStatement.setString(7, v.getMarca());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Veiculo selectVeiculo(int id) {
        Veiculo v = null;
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VEICULO_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int idRevisao = rs.getInt("idRevisao");
                int idDono = rs.getInt("idDono");
                String placa = rs.getString("placa");
                int ano = rs.getInt("ano");
                String RENAVEM = rs.getString("RENAVEM");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                v = new Veiculo(id, idRevisao, idDono, placa, ano, RENAVEM, modelo, marca);
                System.out.println("selectVeiculo: " + id + " " + idRevisao + " " + idDono + " " + placa + " " + ano + " " + RENAVEM + " " + modelo + " " + marca);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return v;
    }

    public List< Veiculo> selectAllVeiculo() {

        List< Veiculo> users = new ArrayList<>();
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VEICULO);) {
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int id = rs.getInt("idVeiculo");
                int idRevisao = rs.getInt("idRevisao");
                int idDono = rs.getInt("idDono");
                String placa = rs.getString("placa");
                int ano = rs.getInt("ano");
                String RENAVEM = rs.getString("RENAVEM");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                users.add(new Veiculo(id, idRevisao, idDono, placa, ano, RENAVEM, modelo, marca));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteVeiculo(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEICULO_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateVeiculo(Veiculo v) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VEICULO_SQL);) {
            preparedStatement.setInt(1, v.getIdRevisao());
            preparedStatement.setInt(2, v.getIdDono());
            preparedStatement.setString(3, v.getPlaca());
            preparedStatement.setInt(4, v.getAno());
            preparedStatement.setString(5, v.getRENAVEM());
            preparedStatement.setString(6, v.getModelo());
            preparedStatement.setString(7, v.getMarca());
            preparedStatement.setInt(8, v.getIdVeiculo());
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
