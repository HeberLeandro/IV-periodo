package br.recife.edu.ifpe.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.recife.edu.ifpe.model.classes.ItemEntrada;
import br.recife.edu.ifpe.model.classes.LoteEntrada;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteEntrada;

/**
 * Servlet implementation class ExibirLotesServlet
 */
@WebServlet("/ExibirLotesServlet")
public class ExibirLotesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExibirLotesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        
        List<LoteEntrada> loteEntrada = RepositorioLoteEntrada.getCurrentInstance().readAll();
        
        
        String responseJSON = "{" + 
        		"   \"lotesEntrada\":[";
        
        for(LoteEntrada i : loteEntrada) {
        	responseJSON += "{" +
        			"        \"codigo\":" + i.getCodigo()+", " + 
            		"        \"data\":\"" +i.getData().toString() + "\", " + 
        			"        \"quantidadeTotal\":" +i.getQuantidadeTotal() +
            		"      }";
            if(loteEntrada.indexOf(i) != loteEntrada.size()-1){
                responseJSON += ",";
            }
        }
        responseJSON += "]}";//, lotesEntrada:[";		 

        
        response.setContentType("text/plain");
        System.out.println(responseJSON);
        
        try(PrintWriter out = response.getWriter()){
            out.println(responseJSON);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
