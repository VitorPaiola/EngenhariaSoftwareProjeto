package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Mecanico;

// A classe MecanicoDAO provê as operações CRUD no banco de dados para tabela Mecanico * 
public class MecanicoDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/oficinamecanica";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_MECANICO_SQL = "insert INTO mecanico " + "  (CREA, nome, endereco, celular, CPF) VALUES "
            + " (?, ?, ?, ?, ?);";
    private static final String SELECT_MECANICO_BY_ID = "select idMeca, CREA, nome, endereco, celular, CPF from mecanico where idMeca =?";
    private static final String SELECT_ALL_MECANICO = "select * from mecanico";
    private static final String DELETE_MECANICO_SQL = "delete from mecanico where idMeca = ?;";
    private static final String UPDATE_MECANICO_SQL = "update mecanico set CREA=?, nome=?, endereco=?, celular=?, CPF=? where idMeca = ?;";

    public MecanicoDAO() {
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

    public void insertMecanico(Mecanico m) throws SQLException {
        System.out.println(INSERT_MECANICO_SQL);

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MECANICO_SQL)) {
            preparedStatement.setInt(1, m.getCREA());
            preparedStatement.setString(2, m.getNome());
            preparedStatement.setString(3, m.getEndereco());
            preparedStatement.setInt(4, m.getCelular());
            preparedStatement.setInt(5, m.getCPF());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Mecanico selectMecanico(int id) {
        Mecanico m = null;
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MECANICO_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int CREA = rs.getInt("CREA");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                int celular = rs.getInt("celular");
                int CPF = rs.getInt("CPF");
                m = new Mecanico(id, CREA, nome, endereco, celular, CPF);
                System.out.println("selectMecanico: " + id + " " + CREA + " " + nome + " " + endereco + " " + celular + " " + CPF);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return m;
    }

    public List< Mecanico> selectAllMecanico() {

        List< Mecanico> users = new ArrayList<>();
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MECANICO);) {
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int id = rs.getInt("idMeca");
                int CREA = rs.getInt("CREA");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                int celular = rs.getInt("celular");
                int CPF = rs.getInt("CPF");
                users.add(new Mecanico(id, CREA, nome, endereco, celular, CPF));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteMecanico(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MECANICO_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateMecanico(Mecanico m) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MECANICO_SQL);) {
            preparedStatement.setInt(1, m.getCREA());
            preparedStatement.setString(2, m.getNome());
            preparedStatement.setString(3, m.getEndereco());
            preparedStatement.setInt(4, m.getCelular());
            preparedStatement.setInt(5, m.getCPF());
            preparedStatement.setInt(6, m.getIdMeca());
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
