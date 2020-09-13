package br.recife.edu.ifpe.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;

/**
 * Servlet implementation class ProtudoServlet
 */
@WebServlet("/FuncionarioServlet")
public class FuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FuncionarioServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Funcionario f = new Funcionario();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			String departamento = request.getParameter("departamento");
			
			if (nome == "" || departamento == "") throw new Exception("Dados Vazios."); 
		
			f.setCodigo(codigo);
			f.setNome(nome);
			f.setDepartamento(departamento);
			
			RepositorioFuncionario.getCurrentInstance().create(f);
			
			out.println("<!DOCTYPE 	html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Resultado do Cadastro</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Cadastro do funcionário, <i>"+ f.getCodigo() +"</i> realizado com sucesso!");
			out.println("<a href=\"index.html\">Voltar a HOME</a> <h1>");
			out.println();
			out.println();
			out.println();
			out.println("</body>");
			out.println("</html>");
			
		} catch (Exception e) {
			out.println("<!DOCTYPE 	html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Resultado do Cadastro</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Erro ao Realizar o Cadastro, Insira os Dados Corretamente.");
			out.println("<a href=\"cadastrofuncionario.html\">Voltar ao Cadastro</a> <h1>");
			out.println();
			out.println();
			out.println();
			out.println("</body>");
			out.println("</html>");
		}
		
	}

}

















