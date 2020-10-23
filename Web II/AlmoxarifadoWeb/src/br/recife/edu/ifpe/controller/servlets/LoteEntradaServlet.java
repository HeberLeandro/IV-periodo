package br.recife.edu.ifpe.controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.recife.edu.ifpe.model.classes.ItemEntrada;
import br.recife.edu.ifpe.model.classes.LoteEntrada;
import br.recife.edu.ifpe.model.classes.Produto;
import br.recife.edu.ifpe.model.repositorios.RepositorioProdutos;

/**
 * Servlet implementation class LoteEntradaServlet
 */
@WebServlet("/LoteEntradaServlet")
public class LoteEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoteEntradaServlet() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = req.getSession();
		
		try {
	        int codigo = Integer.parseInt(req.getParameter("codigo"));
	        int qtd = Integer.parseInt(req.getParameter("qtd"));
	       
	        if(qtd > 100) qtd = 100;
	        else if (qtd < 0) qtd = 0;

	        LoteEntrada lE = (LoteEntrada) session.getAttribute("loteEntrada");
	        
	        if (lE == null) {
	            lE = new LoteEntrada();

	            session.setAttribute("loteEntrada", lE);
	        }

	        boolean controle = false;
	        for (ItemEntrada i : lE.getItens()) {
	            if (i.getProduto().getCodigo() == codigo) {
	                i.setQuantidade(qtd);
	                controle = true;
	            	if (qtd == 0) {
						lE.getItens().remove(i);
					}
	                break;
	            }
	        }

	        if (!controle) {
            	if (qtd != 0) {
    	            ItemEntrada item = new ItemEntrada();

    	            Produto p = RepositorioProdutos.getCurrentInstance().read(codigo);

    	            item.setProduto(p);
    	            item.setCodigo(p.getCodigo());
    	            item.setQuantidade(qtd);

    	            lE.addItem(item);
    	        }
			}

	        
	        if (lE.getItens().isEmpty()) {
				session.removeAttribute("loteEntrada");
			}
	        
		} catch (Exception e) {
			session.removeAttribute("loteEntrada");
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}

       
	        
	}

}
