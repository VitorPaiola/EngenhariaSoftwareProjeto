
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
import Modelo.Revisao;
import DAO.RevisaoDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/revisao")
public class RevisaoServlet extends HttpServlet {

    private RevisaoDAO revisaoDAO;

    @Override
    public void init() {
        revisaoDAO = new RevisaoDAO();
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
                    System.out.println("************* srvlRevisao *** vou new");
                    showNewFormRevisao(request, response);
                    break;
                case "insert":
                    System.out.println("************* srvlRevisao *** vou insert");
                    insertRevisao(request, response);
                    break;
                case "delete":
                    System.out.println("************* srvlRevisao *** vou delete");

                    // 21/03 proximo metodo a arrumar
                    deleteRevisao(request, response);
                    break;
                case "edit":
                    System.out.println("************* srvlRevisao *** vou edit");
                    showEditFormRevisao(request, response);
                    break;
                case "update":
                    System.out.println("************* srvlRevisao *** vou update");
                    updateRevisao(request, response);
                    break;
                case "list":
                    System.out.println("************* srvlRevisao *** vou list");
                    listRevisao(request, response);
                    break;
                default:
                    System.out.println("************* em branco");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listRevisao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("vou consultar todos revisaos e colocar na List listRevisao ");
        List< Revisao> listRevisao = revisaoDAO.selectAllRevisao();

        System.out.println("passando obj listRevisao como atributo da request");
        request.setAttribute("listRevisao", listRevisao);

        System.out.println("vou fazer request para RevisaoFrmListar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("RevisaoFrmListar.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormRevisao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("RevisaoFrmInserir.jsp");

        System.out.println("vou fazer request para RevisaoFrmInserir.jsp ");
        dispatcher.forward(request, response);
    }

    private void showEditFormRevisao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("edit"));

        System.out.println("vou fazer request para RevisaoFrmEditar.jsp ");

        System.out.println("Vou selecionar o Revisao idMeca =  " + id);
        Revisao existingRevisao = revisaoDAO.selectRevisao(id);

        System.out.println("vou fazer request para RevisaoFrmEditar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("RevisaoFrmEditar.jsp");

        System.out.println("passando obj revisao como atributo da request");
        request.setAttribute("revisao", existingRevisao);
        dispatcher.forward(request, response);

    }

    private void insertRevisao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int idMeca = Integer.parseInt(request.getParameter("idMeca"));
        String datahora = request.getParameter("datahora");
        String relatorio = request.getParameter("relatorio");

        System.out.println("Vou inserir no BD: ");

        System.out.println(idMeca);
        System.out.println(datahora);
        System.out.println(relatorio);
        Revisao r = new Revisao(idMeca, datahora, relatorio);
        revisaoDAO.insertRevisao(r);

        System.out.println("a responde faz uma request para" + request.getContextPath() + "/revisao?list");
        response.sendRedirect(request.getContextPath() + "/revisao?list");
    }

    private void updateRevisao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int idRevisao = Integer.parseInt(request.getParameter("idRevisao"));
        int idMeca = Integer.parseInt(request.getParameter("idMeca"));
        String datahora = request.getParameter("datahora");
        String relatorio = request.getParameter("relatorio");
        
        System.out.println("Vou atualizar no BD: ");
        System.out.println(idRevisao);
        System.out.println(idMeca);
        System.out.println(datahora);
        System.out.println(relatorio);

        Revisao r = new Revisao(idRevisao, idMeca, datahora, relatorio);
        revisaoDAO.updateRevisao(r);
        // response.sendRedirect("list");
        // request.getContextPath()+"/revisao?list"
        System.out.println("a response faz uma request para" + request.getContextPath() + "/revisao?list");
        response.sendRedirect(request.getContextPath() + "/revisao?list");
    }

    private void deleteRevisao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("delete"));
        revisaoDAO.deleteRevisao(id);

        // vou fazer send redirect no delete
        System.out.println("vou fazer send redirect no delete no response");
        System.out.println("a response faz uma  request para" + request.getContextPath() + "/revisao?list");

        response.sendRedirect(request.getContextPath() + "/revisao?list");
    }

}
