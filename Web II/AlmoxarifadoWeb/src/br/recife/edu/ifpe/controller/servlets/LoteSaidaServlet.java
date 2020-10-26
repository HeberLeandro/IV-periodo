package br.recife.edu.ifpe.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.recife.edu.ifpe.model.classes.Estoque;
import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.classes.ItemSaida;
import br.recife.edu.ifpe.model.classes.ItemEstoque;
import br.recife.edu.ifpe.model.classes.ItemSaida;
import br.recife.edu.ifpe.model.classes.LoteSaida;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioEstoque;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteSaida;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;

/**
 * Servlet implementation class LoteEntradaServlet
 */
@WebServlet("/LoteSaidaServlet")
public class LoteSaidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoteSaidaServlet() {
        super();
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
		
		HttpSession session = request.getSession();
		try {
			int funcCod = Integer.parseInt(request.getParameter("funcionario"));
			LoteSaida lS = (LoteSaida)session.getAttribute("loteSaida");
			if (lS == null) throw new Exception("LoteSaida Vazio.");
			
			Estoque estoque = RepositorioEstoque.getCurrentInstance().read();
			
			Funcionario funcionario = RepositorioFuncionario.getCurrentInstance().read(funcCod);
			
			for (ItemSaida itemSaida : lS.getItens()) {
				for(ItemEstoque itemEstoque : estoque.getItens()) {
					if (itemSaida.getCodigo() == itemEstoque.getCodigo()) {
						if (itemEstoque.getQuantidade() == 0) {
							session.removeAttribute("loteSaida");
							throw new Exception("Não há itens para serem retirados do estoque.");
						}
						itemEstoque.diminui(itemSaida.getQuantidade());

						break;
					}
				}
			}
			
			
			lS.setResponsavel(funcionario);
			lS.setCodigo(RepositorioLoteSaida.getCurrentInstance().gerarCodigo());
			
			RepositorioLoteSaida.getCurrentInstance().create(lS);
			
			session.removeAttribute("loteSaida");
			session.setAttribute("msgSuccess", "O Lote de Saida foi Retirado.");
			
		} catch (Exception e) {
			session.setAttribute("msgError", "Falha ao Retirar Lote de Saida.");
		}
		
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		try {
	        int codigo = Integer.parseInt(req.getParameter("codigo"));
	        int qtd = Integer.parseInt(req.getParameter("qtd"));
	       
	        if(qtd > 100) qtd = 100;
	        else if (qtd < 0) qtd = 0;

	        LoteSaida lS = (LoteSaida) session.getAttribute("loteSaida");
	        
	        if (lS == null) {
	            lS = new LoteSaida();

	            session.setAttribute("loteSaida", lS);
	        }

	        boolean controle = false;
	        for (ItemSaida i : lS.getItens()) {
	            if (i.getProduto().getCodigo() == codigo) {
	                i.setQuantidade(qtd);
	                controle = true;
	            	if (qtd == 0) {
						lS.getItens().remove(i);
					}
	                break;
	            }
	        }

	        if (!controle) {
            	if (qtd != 0) {
    	            ItemSaida item = new ItemSaida();

    	            Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);

    	            item.setProduto(p);
    	            item.setCodigo(p.getCodigo());
    	            item.setQuantidade(qtd);

    	            lS.addItem(item);
    	        }
			}

	        
	        if (lS.getItens().isEmpty()) {
				session.removeAttribute("loteSaida");
			}
	        
		} catch (Exception e) {
			session.setAttribute("msgError", "Falha ao Definir quantidade do Lote.");
		}

       
	        
	}

}
