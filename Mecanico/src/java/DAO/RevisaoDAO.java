package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Revisao;
import java.sql.Date;

// A classe RevisaoDAO provê as operações CRUD no banco de dados para tabela Revisao * 
public class RevisaoDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/oficinamecanica";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_REVISAO_SQL = "insert INTO revisao " + "  (idMeca, datahora, relatorio) VALUES "
            + " (?, ?, ?);";
    private static final String SELECT_REVISAO_BY_ID = "select idRevisao, idMeca, datahora, relatorio from revisao where idRevisao =?";
    private static final String SELECT_ALL_REVISAO = "select * from revisao";
    private static final String DELETE_REVISAO_SQL = "delete from revisao where idRevisao = ?;";
    private static final String UPDATE_REVISAO_SQL = "update revisao set idMeca=?, datahora=?, relatorio=? where idRevisao = ?;";

    public RevisaoDAO() {
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

    public void insertRevisao(Revisao r) throws SQLException {
        System.out.println(INSERT_REVISAO_SQL);

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REVISAO_SQL)) {
            preparedStatement.setInt(1, r.getIdMeca());
            preparedStatement.setString(2, r.getDatahora());
            preparedStatement.setString(3, r.getRelatorio());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Revisao selectRevisao(int id) {
        Revisao r = null;
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVISAO_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int idMeca = rs.getInt("idMeca");
                String datahora = rs.getString("datahora");
                String relatorio = rs.getString("relatorio");

                r = new Revisao(id, idMeca, datahora, relatorio);
                System.out.println("selectRevisao: " + id + " " + idMeca + " " + datahora + " " + relatorio);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return r;
    }

    public List< Revisao> selectAllRevisao() {

        List< Revisao> users = new ArrayList<>();
        // passo 1 : estabelece a conexão
        try (Connection connection = getConnection(); // passo 2: cria um comando utilizando a conexão
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REVISAO);) {
            System.out.println(preparedStatement);
            // passo 3: executa o comando
            ResultSet rs = preparedStatement.executeQuery();

            // passo 4: processa o objeto de retorno
            while (rs.next()) {
                int id = rs.getInt("idRevisao");
                int idMeca = rs.getInt("idMeca");
                String datahora = rs.getString("datahora");
                String relatorio = rs.getString("relatorio");
                users.add(new Revisao(id, idMeca, datahora, relatorio));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteRevisao(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVISAO_SQL);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateRevisao(Revisao r) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REVISAO_SQL);) {
            preparedStatement.setInt(1, r.getIdMeca());
            preparedStatement.setString(2, r.getDatahora());
            preparedStatement.setString(3, r.getRelatorio());
            preparedStatement.setInt(4, r.getIdRevisao());
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
