
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
import Modelo.Dono;
import DAO.DonoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/dono")
public class DonoServlet extends HttpServlet {

    private DonoDAO donoDAO;

    @Override
    public void init() {
        donoDAO = new DonoDAO();
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
                    System.out.println("************* srvlDono *** vou new");
                    showNewFormDono(request, response);
                    break;
                case "insert":
                    System.out.println("************* srvlDono *** vou insert");
                    insertDono(request, response);
                    break;
                case "delete":
                    System.out.println("************* srvlDono *** vou delete");

                    // 21/03 proximo metodo a arrumar
                    deleteDono(request, response);
                    break;
                case "edit":
                    System.out.println("************* srvlDono *** vou edit");
                    showEditFormDono(request, response);
                    break;
                case "update":
                    System.out.println("************* srvlDono *** vou update");
                    updateDono(request, response);
                    break;
                case "list":
                    System.out.println("************* srvlDono *** vou list");
                    listDono(request, response);
                    break;
                default:
                    System.out.println("************* em branco");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listDono(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        System.out.println("vou consultar todos donos e colocar na List listDono ");
        List< Dono> listDono = donoDAO.selectAllDono();

        System.out.println("passando obj listDono como atributo da request");
        request.setAttribute("listDono", listDono);

        System.out.println("vou fazer request para DonoFrmListar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("DonoFrmListar.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewFormDono(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("DonoFrmInserir.jsp");

        System.out.println("vou fazer request para DonoFrmInserir.jsp ");
        dispatcher.forward(request, response);
    }

    private void showEditFormDono(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("edit"));

        System.out.println("vou fazer request para DonoFrmEditar.jsp ");

        System.out.println("Vou selecionar o Dono idDono =  " + id);
        Dono existingDono = donoDAO.selectDono(id);

        System.out.println("vou fazer request para DonoFrmEditar.jsp ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("DonoFrmEditar.jsp");

        System.out.println("passando obj cliente como atributo da request");
        request.setAttribute("dono", existingDono);
        dispatcher.forward(request, response);

    }

    private void insertDono(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        int CNH = Integer.parseInt(request.getParameter("CNH"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");

        System.out.println("Vou inserir no BD: ");
        System.out.println(CNH);
        System.out.println(nome);
        System.out.println(endereco);
        System.out.println(telefone);
        Dono d = new Dono(CNH, nome, endereco, telefone);
        donoDAO.insertDono(d);

        System.out.println("a responde faz uma request para" + request.getContextPath() + "/dono?list");
        response.sendRedirect(request.getContextPath() + "/dono?list");
    }

    private void updateDono(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int idDono = Integer.parseInt(request.getParameter("idDono"));
        int CNH = Integer.parseInt(request.getParameter("CNH"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        
        System.out.println("Vou atualizar no BD: ");
        System.out.println(idDono);
        System.out.println(CNH);
        System.out.println(nome);
        System.out.println(endereco);
        System.out.println(telefone);
        Dono d = new Dono(idDono,CNH, nome, endereco, telefone);
        donoDAO.updateDono(d);
        // response.sendRedirect("list");
        // request.getContextPath()+"/dono?list"
        System.out.println("a response faz uma request para" + request.getContextPath() + "/dono?list");
        response.sendRedirect(request.getContextPath() + "/dono?list");
    }

    private void deleteDono(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("delete"));
        donoDAO.deleteDono(id);

        // vou fazer send redirect no delete
        System.out.println("vou fazer send redirect no delete no response");
        System.out.println("a response faz uma  request para" + request.getContextPath() + "/dono?list");

        response.sendRedirect(request.getContextPath() + "/dono?list");
    }

}
