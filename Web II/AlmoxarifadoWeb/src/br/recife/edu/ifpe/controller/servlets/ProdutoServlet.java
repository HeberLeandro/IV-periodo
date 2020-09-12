package br.recife.edu.ifpe.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdutoServlet() {
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
		
		Produto p = new Produto();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			String marca = request.getParameter("marca");
			String categoria = request.getParameter("categoria");
			String descricao = request.getParameter("descricao");
			
			RepositorioProdutos.getCurrentInstance().create(p);
			
			ItemEstoque item = new ItemEstoque();
			item.setProduto(p);
			item.setQuantidade(0);
			item.setCodigo(p.getCodigo());
			
			RepositorioEstoque.getCurrentInstance().read().addItem(item);
			
			if (nome == "" || marca == "" || categoria == "") throw new Exception("Dados Vazios."); 
		
			p.setCodigo(codigo);
			p.setNome(nome);
			p.setMarca(marca);
			p.setCategoria(categoria);
			p.setDescricao(descricao);
			
			
			out.println("<!DOCTYPE 	html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Resultado do Cadastro</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Cadastro do Produto, <i>"+ p.getNome() +"</i> realizado com sucesso!");
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
			out.println("<h1>Erro ao Realizar o Cadastro, Insira os Dados Corretamente. <br>");
			out.println("<a href=\"index.html\">Voltar a HOME</a> <h1>");
			out.println();
			out.println();
			out.println();
			out.println("</body>");
			out.println("</html>");
		}
	}

}
