package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ConexaoBD;
import dao.MovimentacoesDAO;
import entity.Movimentacoes;
import jakarta.activation.DataSource;
import jakarta.annotation.Resource;
//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListarMovimentacoes.jsp")
public class ListarMovimentacoes extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/projetobd") // For Tomcat, define as <Resource> in context.xml and declare as <resource-ref> in web.xml.
    private DataSource dataSource;
    private MovimentacoesDAO movimentacoesDAO;

    @Override
    public void init() {
    	movimentacoesDAO = new MovimentacoesDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	Connection conn = ConexaoBD.getConexao();
            List<Movimentacoes> movimentacoes = movimentacoesDAO.listarMovimentacoes(conn);
            request.setAttribute("movimentacoes", movimentacoes); // Will be available as ${products} in JSP
            request.getRequestDispatcher("/WEB-INF/ListarMovimentacoes.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain products from DB", e);
        }
    }

}