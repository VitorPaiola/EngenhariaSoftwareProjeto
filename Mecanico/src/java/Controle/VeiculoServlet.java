
package Controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Modelo.Veiculo;
import DAO.VeiculoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/veiculo")
public class VeiculoServlet extends HttpServlet {

    private VeiculoDAO veiculoDAO;

    @Override
    public void init() {
        veiculoDAO = new VeiculoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acaocomparamentro = request.getQueryString();
        System.out.println("\n\n++++++++ Chegou Request: acao com paramentro " + acaocomparamentro);
        String action = acaocomparamentro.split("\\=")[0];
        System.out.println("action " + action);

        try {
            switch (action) {
                case "new":
                    System.out.println("************* srvlVeiculo *** vou new");
                    showNewFormVeiculo(request, response);
                    break;
                case "insert":
                    System.out.println("************* srvlVeiculo *** vou insert");
                    insertVeiculo(request, response);
                    break;
                case "delete":
                    System.out.println("************* srvlVeiculo *** vou delete");

                    // 21/03 proximo metodo a arrumar
                    deleteVeiculo(request, response);
                    break;
                case "edit":
                    System.out.println("************* srvlVeiculo *** vou edit");
                    showEditFormVeiculo(request, response);
                    break;
                case "update":
                    System.out.println("************* srvlVeiculo *** vou update");
                    updateVeiculo(request, response);
                    break;
                case "list":
                    System.out.println("************* srvlVeiculo *** vou list");
                    listVeiculo(request, response);
                    break;
                default:
                    System.out.println("************* em branco");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listVeiculo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("vou consultar todos veiculos e colocar na List listVeiculo ");
        List< Veiculo> listVeiculo = veiculoDAO.selectAllVeiculo();

        System.out.println("passando obj listVeiculo como atributo da request");
        request.setAttribute("listVeiculo", listVeiculo);

        System.out.println("vou fazer request para VeiculoFrmListar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("VeiculoFrmListar.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormVeiculo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("VeiculoFrmInserir.jsp");

        System.out.println("vou fazer request para VeiculoFrmInserir.jsp ");
        dispatcher.forward(request, response);
    }

    private void showEditFormVeiculo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("edit"));

        System.out.println("vou fazer request para VeiculoFrmEditar.jsp ");

        System.out.println("Vou selecionar o Veiculo idVeiculo =  " + id);
        Veiculo existingVeiculo = veiculoDAO.selectVeiculo(id);

        System.out.println("vou fazer request para VeiculoFrmEditar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("VeiculoFrmEditar.jsp");

        System.out.println("passando obj veiculo como atributo da request");
        request.setAttribute("veiculo", existingVeiculo);
        dispatcher.forward(request, response);

    }

    private void insertVeiculo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int idRevisao = Integer.parseInt(request.getParameter("idRevisao"));
        int idDono = Integer.parseInt(request.getParameter("idDono"));
        String placa = request.getParameter("placa");
        int ano = Integer.parseInt(request.getParameter("ano"));
        String RENAVEM = request.getParameter("RENAVEM");
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");

        System.out.println("Vou inserir no BD: ");

        System.out.println(idRevisao);
        System.out.println(idDono);
        System.out.println(placa);
        System.out.println(ano);
        System.out.println(RENAVEM);
        System.out.println(modelo);
        System.out.println(marca);
        Veiculo v = new Veiculo(idRevisao, idDono, placa, ano, RENAVEM, modelo, marca);
        veiculoDAO.insertVeiculo(v);

        System.out.println("a responde faz uma request para" + request.getContextPath() + "/veiculo?list");
        response.sendRedirect(request.getContextPath() + "/veiculo?list");
    }

    private void updateVeiculo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int idVeiculo = Integer.parseInt(request.getParameter("idVeiculo"));
        int idRevisao = Integer.parseInt(request.getParameter("idRevisao"));
        int idDono = Integer.parseInt(request.getParameter("idDono"));
        String placa = request.getParameter("placa");
        int ano = Integer.parseInt(request.getParameter("ano"));
        String RENAVEM = request.getParameter("RENAVEM");
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        
        System.out.println("Vou atualizar no BD: ");
        System.out.println(idVeiculo);
        System.out.println(idRevisao);
        System.out.println(idDono);
        System.out.println(placa);
        System.out.println(ano);
        System.out.println(RENAVEM);
        System.out.println(modelo);
        System.out.println(marca);
        Veiculo v = new Veiculo(idVeiculo,idRevisao, idDono, placa, ano, RENAVEM, modelo, marca);
        veiculoDAO.updateVeiculo(v);
        // response.sendRedirect("list");
        // request.getContextPath()+"/veiculo?list"
        System.out.println("a response faz uma request para" + request.getContextPath() + "/veiculo?list");
        response.sendRedirect(request.getContextPath() + "/veiculo?list");
    }

    private void deleteVeiculo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("delete"));
        veiculoDAO.deleteVeiculo(id);

        // vou fazer send redirect no delete
        System.out.println("vou fazer send redirect no delete no response");
        System.out.println("a response faz uma  request para" + request.getContextPath() + "/veiculo?list");

        response.sendRedirect(request.getContextPath() + "/veiculo?list");
    }

}
