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
		Funcionario f = new Funcionario();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			int delete = Integer.parseInt(request.getParameter("delete"));
			
			f = RepositorioFuncionario.getCurrentInstance().read(delete);
			
			if (f == null) throw new Exception("Funcionario não encontrado.");
			
			RepositorioFuncionario.getCurrentInstance().delete(f);			
			
			out.println("<!DOCTYPE 	html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Resultado da Deleção</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Funcionário <i>"+ f.getNome() +"</i>, Deletado com sucesso!");
			out.println("<a href=\"Funcionarios.jsp\">Voltar à Lista de Funcionarios</a> <h1>");
			out.println("</body>");
			out.println("</html>");
			
		} catch (Exception e) {
			out.println("<!DOCTYPE 	html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Resultado do Deleção</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Erro ao Deletar.");
			out.println("<a href=\"Funcionarios.jsp\">Voltar à Lista de Funcionarios</a> <h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Funcionario f = new Funcionario();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			
			String nome = request.getParameter("nome");
			String departamento = request.getParameter("departamento");
			String atualizar = request.getParameter("atualizar");
			
			if (nome == "" || departamento == "") throw new Exception("Dados Vazios."); 
			
			f.setCodigo(RepositorioFuncionario.getCurrentInstance().gerarCodigo());
			f.setNome(nome);
			f.setDepartamento(departamento);
				
			if (atualizar != null) {
				int codigo = Integer.parseInt(request.getParameter("codigo"));
				f.setCodigo(codigo);
				RepositorioFuncionario.getCurrentInstance().update(f);
			} else {
				RepositorioFuncionario.getCurrentInstance().create(f);
			}
			
			out.println("<!DOCTYPE 	html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Resultado do Cadastro</title>");
			out.println("</head>");
			out.println("<body>");
			if (atualizar == null) {
				out.println("<h1>Cadastro do funcionário <i>"+ f.getNome() +"</i>, realizado com sucesso!");
				out.println("<a href=\"Funcionarios.jsp\">Ir para Lista</a> <h1>");
			} else {
				out.println("<h1>Atualização do funcionário <i>"+ f.getNome() +"</i>, realizada com sucesso!");
				out.println("<a href=\"Funcionarios.jsp\">Voltar a Lista</a> <h1>");
			}
			
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
			out.println("<a href=\"cadastrofuncionario.jsp\">Voltar ao Cadastro</a> <h1>");
			out.println("</body>");
			out.println("</html>");
		}
		
	}

}

















