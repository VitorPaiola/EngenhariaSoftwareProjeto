
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
import Modelo.Mecanico;
import DAO.MecanicoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/mecanico")
public class MecanicoServlet extends HttpServlet {

    private MecanicoDAO mecanicoDAO;

    @Override
    public void init() {
        mecanicoDAO = new MecanicoDAO();
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
                    System.out.println("************* srvlMecanico *** vou new");
                    showNewFormMecanico(request, response);
                    break;
                case "insert":
                    System.out.println("************* srvlMecanico *** vou insert");
                    insertMecanico(request, response);
                    break;
                case "delete":
                    System.out.println("************* srvlMecanico *** vou delete");

                    // 21/03 proximo metodo a arrumar
                    deleteMecanico(request, response);
                    break;
                case "edit":
                    System.out.println("************* srvlMecanico *** vou edit");
                    showEditFormMecanico(request, response);
                    break;
                case "update":
                    System.out.println("************* srvlMecanico *** vou update");
                    updateMecanico(request, response);
                    break;
                case "list":
                    System.out.println("************* srvlMecanico *** vou list");
                    listMecanico(request, response);
                    break;
                default:
                    System.out.println("************* em branco");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMecanico(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("vou consultar todos mecanicos e colocar na List listMecanico ");
        List< Mecanico> listMecanico = mecanicoDAO.selectAllMecanico();

        System.out.println("passando obj listMecanico como atributo da request");
        request.setAttribute("listMecanico", listMecanico);

        System.out.println("vou fazer request para MecanicoFrmListar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("MecanicoFrmListar.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormMecanico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("MecanicoFrmInserir.jsp");

        System.out.println("vou fazer request para MecanicoFrmInserir.jsp ");
        dispatcher.forward(request, response);
    }

    private void showEditFormMecanico(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("edit"));

        System.out.println("vou fazer request para MecanicoFrmEditar.jsp ");

        System.out.println("Vou selecionar o Mecanico idMeca =  " + id);
        Mecanico existingMecanico = mecanicoDAO.selectMecanico(id);

        System.out.println("vou fazer request para MecanicoFrmEditar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("MecanicoFrmEditar.jsp");

        System.out.println("passando obj mecanico como atributo da request");
        request.setAttribute("mecanico", existingMecanico);
        dispatcher.forward(request, response);

    }

    private void insertMecanico(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int CREA = Integer.parseInt(request.getParameter("CREA"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        int celular = Integer.parseInt(request.getParameter("celular"));
        int CPF = Integer.parseInt(request.getParameter("CPF"));

        System.out.println("Vou inserir no BD: ");

        System.out.println(CREA);
        System.out.println(nome);
        System.out.println(endereco);
        System.out.println(celular);
        System.out.println(CPF);
        Mecanico m = new Mecanico(CREA, nome, endereco, celular, CPF);
        mecanicoDAO.insertMecanico(m);

        System.out.println("a responde faz uma request para" + request.getContextPath() + "/mecanico?list");
        response.sendRedirect(request.getContextPath() + "/mecanico?list");
    }

    private void updateMecanico(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int idMeca = Integer.parseInt(request.getParameter("idMeca"));
        int CREA = Integer.parseInt(request.getParameter("CREA"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        int celular = Integer.parseInt(request.getParameter("celular"));
        int CPF = Integer.parseInt(request.getParameter("CPF"));
        
        System.out.println("Vou atualizar no BD: ");
        System.out.println(idMeca);
        System.out.println(CREA);
        System.out.println(nome);
        System.out.println(endereco);
        System.out.println(celular);
        System.out.println(CPF);
        Mecanico m = new Mecanico(idMeca,CREA, nome, endereco, celular, CPF);
        mecanicoDAO.updateMecanico(m);
        // response.sendRedirect("list");
        // request.getContextPath()+"/mecanico?list"
        System.out.println("a response faz uma request para" + request.getContextPath() + "/mecanico?list");
        response.sendRedirect(request.getContextPath() + "/mecanico?list");
    }

    private void deleteMecanico(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("delete"));
        mecanicoDAO.deleteMecanico(id);

        // vou fazer send redirect no delete
        System.out.println("vou fazer send redirect no delete no response");
        System.out.println("a response faz uma  request para" + request.getContextPath() + "/mecanico?list");

        response.sendRedirect(request.getContextPath() + "/mecanico?list");
    }

}
