package br.recife.edu.ifpe.controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.recife.edu.ifpe.model.classes.ItemEntrada;
import br.recife.edu.ifpe.model.classes.LoteEntrada;
import br.recife.edu.ifpe.model.classes.LoteSaida;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteEntrada;
import br.recife.edu.ifpe.model.repositorios.RepositorioLoteSaida;
import jdk.nashorn.internal.ir.ForNode;

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
        List<LoteSaida> loteSaida = RepositorioLoteSaida.getCurrentInstance().readAll();
        
        
        String responseJSON = "{" + 
        		"   \"lotesEntrada\":[";
        
        for(LoteEntrada i : loteEntrada) {
        	responseJSON += "{" +
        			"        \"codigo\":" + i.getCodigo()+", " + 
            		"        \"data\":\"" + formatDate(i.getData()) + "\", " + 
        			"        \"quantidadeTotal\":" +i.getQuantidadeTotal() +
            		"      }";
            if(loteEntrada.indexOf(i) != loteEntrada.size()-1){
                responseJSON += ",";
            }
        }
        responseJSON += "], \"lotesSaida\":[";
        
        for(LoteSaida i : loteSaida) {
        	responseJSON += "{" +
        			"        \"codigo\":" + i.getCodigo()+", " + 
            		"        \"data\":\"" + formatDate(i.getData()) + "\", " + 
        			"        \"quantidadeTotal\":" +i.getQuantidadeTotal() +
            		"      }";
            if(loteSaida.indexOf(i) != loteSaida.size()-1){
                responseJSON += ",";
            }
        }
        responseJSON += "]}";
        
        response.setContentType("text/plain");
        
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
	
	public String formatDate(Date date) {
		//yyyy-mm-ddThh:mm:ss
		String formatedDate = "";
		formatedDate = String.valueOf(date.getYear() + 1900) + "-";
		formatedDate += String.format("%02d", date.getMonth()) + "-";
		formatedDate += String.format("%02d", date.getDate()) + "T";
		formatedDate += String.format("%02d", date.getHours()) + ":";
		formatedDate += String.format("%02d", date.getMinutes()) + ":";
		formatedDate += String.format("%02d", date.getSeconds());

		return formatedDate;
	}

}
