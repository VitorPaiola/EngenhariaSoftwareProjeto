package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Dono;

// A classe DonoDAO provê as operações CRUD no banco de dados para tabela Dono * 
public class DonoDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/oficinamecanica";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_DONO_SQL = "insert INTO dono " + "  (CNH, nome, endereco, telefone) VALUES "
            + " (?, ?, ?, ?);";
    private static final String SELECT_DONO_BY_ID = "select idDono, CNH, nome, endereco, telefone from dono where idDono =?";
    private static final String SELECT_ALL_DONO = "select * from dono";
    private static final String DELETE_DONO_SQL = "delete from dono where idDono = ?;";
    private static final String UPDATE_DONO_SQL = "update dono set CNH=?, nome=?, endereco=?, telefone=? where idDono = ?;";

    public DonoDAO() {
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

    public void insertDono(Dono d) throws SQLException {
        System.out.println(INSERT_DONO_SQL);

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DONO_SQL)) {
            preparedStatement.setInt(1, d.getCNH());
            preparedStatement.setString(2, d.getNome());
            preparedStatement.setString(3, d.getEndereco());
            preparedStatement.setString(4, d.getTelefone());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Dono selectDono(int id) {
        Dono d = null;
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DONO_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int CNH = rs.getInt("CNH");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                d = new Dono(id, CNH, nome, endereco, telefone);
                System.out.println("selectDono: " + id + " " + CNH + " " + nome + " " + endereco + " " + telefone);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return d;
    }

    public List< Dono> selectAllDono() {

        List< Dono> users = new ArrayList<>();
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DONO);) {
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int id = rs.getInt("idDono");
                int CNH = rs.getInt("CNH");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                users.add(new Dono(id, CNH, nome, endereco, telefone));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteDono(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DONO_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateDono(Dono d) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DONO_SQL);) {
            preparedStatement.setInt(1, d.getCNH());
            preparedStatement.setString(2, d.getNome());
            preparedStatement.setString(3, d.getEndereco());
            preparedStatement.setString(4, d.getTelefone());
            preparedStatement.setInt(5, d.getIdDono());
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
