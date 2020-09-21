package br.recife.edu.ifpe.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

	public ProdutoServlet() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String cod = request.getParameter("codigo");
		String deletar = request.getParameter("deletar");
		String atualizar = request.getParameter("atualizar");

		if (deletar != null) {
			try {
				int codigo = Integer.parseInt(cod);
				
				Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
				
				if (p == null) throw new Exception("codigo inválido.");
				
				RepositorioProdutos.getCurrentInstance().delete(p);
				
				out.println("<!DOCTYPE 	html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"ISO-8859-1\">");
				out.println("<title>Produto Deletado</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Produto, <i>" + p.getNome() + "</i> Deletado com sucesso!");
				out.println("<a href=\"ProdutoServlet\">Voltar a lista de Produtos</a> <h1>");
				out.println("</body>");
				out.println("</html>");
				
			} catch (Exception e) {
				out.println("<!DOCTYPE 	html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"ISO-8859-1\">");
				out.println("<title>Erro ao Deletar Produto</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Código inválido, Erro ao Deletar o Produto.<br>");
				out.println("<a href=\"ProdutoServlet\">Voltar a listagem de Produtos</a> <h1>");
				out.println("</body>");
				out.println("</html>");
			}
		}
		
		if (atualizar != null) {

			try {
				int codigo = Integer.parseInt(cod);
				
				Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);
				
				if (p == null) throw new Exception("codigo inválido.");

				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"ISO-8859-1\">");
				out.println("<title>Atualiar Produto</title>");
				out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/"
						+ "bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">");
				out.println("</head>");
				out.println("<body>");
				out.println("<div class=\"container\">\r\n" + "	<div class=\"row justify-content-center\">\r\n"
						+ "		\r\n" + "	\r\n" + "		<div class=\"col-7 mt-5\">\r\n"
						+ "		<h1 class=\"h2 mb-5\">Atualizar Produto</h1>\r\n"
						+ "			<form method=\"post\" action=\"ProdutoServlet\">\r\n"
						+ "				<div class=\"form-group d-none\">\r\n"
						+ "					<label>Codigo</label>\r\n"
						+ "					<input type=\"hidden\" required=\"required\" class=\"form-control\" name=\"codigo\" value=\""+ p.getCodigo() + "\"/>\r\n"
						+ "				</div>\r\n"
						+ "				<div class=\"form-group\">\r\n" + "					<label>Nome</label>\r\n"
						+ "					<input type=\"text\" required=\"required\" class=\"form-control\" name=\"nome\" value=\""+p.getNome()+"\"/>\r\n"
						+ "				</div>\r\n" + "				<div class=\"form-group\">\r\n"
						+ "					<label>Marca</label>\r\n"
						+ "					<input type=\"text\" required=\"required\" class=\"form-control\" name=\"marca\" value=\""+p.getMarca()+"\"/> \r\n"
						+ "				</div>\r\n" + "				<div class=\"form-group\">\r\n"
						+ "					<label>Categoria</label>\r\n"
						+ "					<input type=\"text\" required=\"required\" class=\"form-control\" name=\"categoria\" value=\""+p.getCategoria()+"\"/>\r\n"
						+ "				</div>\r\n" + "				<div class=\"form-group\">\r\n"
						+ "					<label>Descrição</label>\r\n"
						+ "					<textarea type=\"text\" class=\"form-control\" name=\"descricao\" value=\""+p.getDescricao()+"\">"+p.getDescricao()+"</textarea>\r\n"
						+ "				</div>\r\n"
						+ "				<input type='hidden' name='atualizar' value='1' />\r\n"
						+ "				<button type=\"submit\"  class=\"btn btn-primary\">Atualizar</button>\r\n"
						+ "				<button type=\"button\" onclick=\"window.location.href='ProdutoServlet'\" class=\"btn btn-secondary\">Voltar a lista</button>\r\n"
						+ "			</form>\r\n" + "		</div>\r\n" + "	</div>\r\n" + "	\r\n" + "	\r\n"
						+ "	\r\n" + "</div>");
				out.println("</body>");
				out.println("</html>");
				
			} catch (Exception e) {
				
				out.println("<!DOCTYPE 	html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"ISO-8859-1\">");
				out.println("<title>Erro ao Buscar Produto</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Código inválido, Erro ao Resgatar o Produto.<br>");
				out.println("<a href=\"index.html\">Voltar a Home</a> <h1>");
				out.println("</body>");
				out.println("</html>");
			}

		}

		if (cod == null || cod == "") {

			List<Produto> produtos = RepositorioProdutos.getCurrentInstance().readAll();

			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Lista de Produtos</title>");
			out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/"
					+ "bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=\"container\"> <div class=\"row\"> <div class=\"col\">");
			out.println("<h1>Produto Selecionado</h1>");
			out.println("<a href=\"index.html\">Voltar a Home.</a> </dir>");
			out.println("<div class=\"col-12 mt-1\">");
			out.println("<table class=\"table table-hover\">");
			out.println("<thead>\r\n" + "    <tr>\r\n" + "      <th scope=\"col\">Código</th>\r\n"
					+ "      <th scope=\"col\">Nome</th>\r\n" + "      <th scope=\"col\">Marca</th>\r\n"
					+ "      <th scope=\"col\">Categoria</th>\r\n" + "      <th scope=\"col\">Operações</th>\r\n"
					+ "    </tr>\r\n" + "  </thead>");
			out.println("<tbody>");
			for (Produto p : produtos) {
				out.println(" <tr>\r\n" + "      <th scope=\"row\">" + p.getCodigo() + "</th>\r\n" + "      <td>"
						+ p.getNome() + "</td>\r\n" + "      <td>" + p.getMarca() + "</td>\r\n" + "      <td>"
						+ p.getCategoria() + "</td>\r\n" + "      <td><a href=\"ProdutoServlet?codigo=" + p.getCodigo()
						+ "\" class=\"badge badge-primary\">Visualizar</a>\r\n"
						+ "    <a href=\"ProdutoServlet?codigo=" + p.getCodigo() + "&atualizar=1\" class=\"badge badge-secondary\">Atualizar</a>"
						+ "   <a href='ProdutoServlet?codigo="+p.getCodigo()+"&deletar=1'class=\"badge badge-danger\">Deletar</td>\r\n" + "  </tr>");
			}

			out.println("</tbody>");
			out.println("</table>");
			out.println("</div> </div> </div>");
			out.println("</body>");
			out.println("</html>");

		} else if (cod != null && atualizar == null && deletar == null) {
			try {
				int codigo = Integer.parseInt(cod);

				Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);

				if (p == null)
					throw new Exception("Codigo inexistente.");

				out.println("<!DOCTYPE 	html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"ISO-8859-1\">");
				out.println("<title>Detalhes do Produto</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Produto Selecionado</h1>");
				out.println("<a href=\"ProdutoServlet\">Voltar à listagem de produtos.</a> <br/><br/>");
				out.println("<strong>Código: </strong>" + p.getCodigo());
				out.println("<br/><strong>Nome: </strong>" + p.getNome());
				out.println("<br/><strong>Marca: </strong>" + p.getMarca());
				out.println("<br/><strong>Categoria: </strong>" + p.getCategoria());
				out.println("<br/><strong>Descrição: </strong>" + p.getDescricao());
				out.println("</body>");
				out.println("</html>");

			} catch (Exception e) {
				out.println("<!DOCTYPE 	html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset=\"ISO-8859-1\">");
				out.println("<title>Erro ao Buscar Produto</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Código inválido, Erro ao Resgatar o Produto.<br>");
				out.println("<a href=\"ProdutoServlet\">Voltar a listagem de Produtos</a> <h1>");
				out.println("</body>");
				out.println("</html>");
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Produto p = new Produto();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String atualizar = request.getParameter("atualizar");
		
		try {

			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			String marca = request.getParameter("marca");
			String categoria = request.getParameter("categoria");
			String descricao = request.getParameter("descricao");

			if (nome == "" || marca == "" || categoria == "")
				throw new Exception("Dados Vazios.");

			p.setCodigo(codigo);
			p.setNome(nome);
			p.setMarca(marca);
			p.setCategoria(categoria);
			p.setDescricao(descricao);
			
			if (atualizar != null) {
				
				RepositorioProdutos.getCurrentInstance().update(p);
				
			} else {
				
				RepositorioProdutos.getCurrentInstance().create(p);

				ItemEstoque item = new ItemEstoque();
				item.setProduto(p);
				item.setQuantidade(0);
				item.setCodigo(p.getCodigo());

				RepositorioEstoque.getCurrentInstance().read().addItem(item);
			}

			out.println("<!DOCTYPE 	html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"ISO-8859-1\">");
			out.println("<title>Resultado do Cadastro</title>");
			out.println("</head>");
			out.println("<body>");
			
			if (atualizar == null) {
				out.println("<h1>Cadastro do Produto, <i>" + p.getNome() + "</i> realizado com sucesso!");
			} else {
				out.println("<h1>Atualização do Produto, <i>" + p.getNome() + "</i> realizada com sucesso!");
			}
			
			out.println("<a href=\"index.html\">Voltar a HOME.</a> <h1>");
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
			out.println("<a href=\"cadastroproduto.html\">Voltar ao Cadastro.</a> <h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
