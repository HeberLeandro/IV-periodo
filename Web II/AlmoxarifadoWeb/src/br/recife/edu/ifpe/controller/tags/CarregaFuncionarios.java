package br.recife.edu.ifpe.controller.tags;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import br.recife.edu.ifpe.model.classes.Funcionario;
import br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario;



public class CarregaFuncionarios extends SimpleTagSupport{
	
	    @Override
	    public void doTag() throws JspException, IOException {
	        super.doTag(); //To change body of generated methods, choose Tools | Templates.
	        
	        List<Funcionario> funcionarios = RepositorioFuncionario.getCurrentInstance().readAll();
	        
	        getJspContext().setAttribute("funcionarios", funcionarios, PageContext.PAGE_SCOPE);
	        
	    }
	

}
