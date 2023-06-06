
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
import Modelo.VeiculoPesado;
import DAO.VeiculoPesadoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/veiculopesado")
public class VeiculoPesadoServlet extends HttpServlet {

    private VeiculoPesadoDAO veiculopesadoDAO;

    @Override
    public void init() {
        veiculopesadoDAO = new VeiculoPesadoDAO();
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
                    System.out.println("************* srvlVeiculoPesado *** vou new");
                    showNewFormVeiculoPesado(request, response);
                    break;
                case "insert":
                    System.out.println("************* srvlVeiculoPesado *** vou insert");
                    insertVeiculoPesado(request, response);
                    break;
                case "delete":
                    System.out.println("************* srvlVeiculoPesado *** vou delete");

                    // 21/03 proximo metodo a arrumar
                    deleteVeiculoPesado(request, response);
                    break;
                case "edit":
                    System.out.println("************* srvlVeiculoPesado *** vou edit");
                    showEditFormVeiculoPesado(request, response);
                    break;
                case "update":
                    System.out.println("************* srvlVeiculoPesado *** vou update");
                    updateVeiculoPesado(request, response);
                    break;
                case "list":
                    System.out.println("************* srvlVeiculoPesado *** vou list");
                    listVeiculoPesado(request, response);
                    break;
                default:
                    System.out.println("************* em branco");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listVeiculoPesado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("vou consultar todos veiculopesados e colocar na List listVeiculoPesado ");
        List< VeiculoPesado> listVeiculoPesado = veiculopesadoDAO.selectAllVeiculoPesado();

        System.out.println("passando obj listVeiculoPesado como atributo da request");
        request.setAttribute("listVeiculoPesado", listVeiculoPesado);

        System.out.println("vou fazer request para VeiculoPesadoFrmListar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("VeiculoPesadoFrmListar.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormVeiculoPesado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("VeiculoPesadoFrmInserir.jsp");

        System.out.println("vou fazer request para VeiculoPesadoFrmInserir.jsp ");
        dispatcher.forward(request, response);
    }

    private void showEditFormVeiculoPesado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("edit"));

        System.out.println("vou fazer request para VeiculoPesadoFrmEditar.jsp ");

        System.out.println("Vou selecionar o VeiculoPesado idVeiculoPesado =  " + id);
        VeiculoPesado existingVeiculoPesado = veiculopesadoDAO.selectVeiculoPesado(id);

        System.out.println("vou fazer request para VeiculoPesadoFrmEditar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("VeiculoPesadoFrmEditar.jsp");

        System.out.println("passando obj veiculopesado como atributo da request");
        request.setAttribute("veiculopesado", existingVeiculoPesado);
        dispatcher.forward(request, response);

    }

    private void insertVeiculoPesado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int idVeiculo = Integer.parseInt(request.getParameter("idVeiculo"));
        int comprimentoMetros = Integer.parseInt(request.getParameter("comprimentoMetros"));
        int numEixos = Integer.parseInt(request.getParameter("numEixos"));
        int pesoToneladas = Integer.parseInt(request.getParameter("pesoToneladas"));

        System.out.println("Vou inserir no BD: ");

        System.out.println(idVeiculo);
        System.out.println(comprimentoMetros);
        System.out.println(numEixos);
        System.out.println(pesoToneladas);
        VeiculoPesado vp = new VeiculoPesado(idVeiculo, comprimentoMetros, numEixos, pesoToneladas);
        veiculopesadoDAO.insertVeiculoPesado(vp);

        System.out.println("a responde faz uma request para" + request.getContextPath() + "/veiculopesado?list");
        response.sendRedirect(request.getContextPath() + "/veiculopesado?list");
    }

    private void updateVeiculoPesado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int idVeiculoPesado = Integer.parseInt(request.getParameter("idVeiculoPesado"));
        int idVeiculo = Integer.parseInt(request.getParameter("idVeiculo"));
        int comprimentoMetros = Integer.parseInt(request.getParameter("comprimentoMetros"));
        int numEixos = Integer.parseInt(request.getParameter("numEixos"));
        int pesoToneladas = Integer.parseInt(request.getParameter("pesoToneladas"));
        
        System.out.println("Vou atualizar no BD: ");
        System.out.println(idVeiculoPesado);
        System.out.println(idVeiculo);
        System.out.println(comprimentoMetros);
        System.out.println(numEixos);
        System.out.println(pesoToneladas);
        VeiculoPesado vp = new VeiculoPesado(idVeiculoPesado, idVeiculo, comprimentoMetros, numEixos, pesoToneladas);
        veiculopesadoDAO.updateVeiculoPesado(vp);
        // response.sendRedirect("list");
        // request.getContextPath()+"/veiculopesado?list"
        System.out.println("a response faz uma request para" + request.getContextPath() + "/veiculopesado?list");
        response.sendRedirect(request.getContextPath() + "/veiculopesado?list");
    }

    private void deleteVeiculoPesado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("delete"));
        veiculopesadoDAO.deleteVeiculoPesado(id);

        // vou fazer send redirect no delete
        System.out.println("vou fazer send redirect no delete no response");
        System.out.println("a response faz uma  request para" + request.getContextPath() + "/veiculopesado?list");

        response.sendRedirect(request.getContextPath() + "/veiculopesado?list");
    }

}
