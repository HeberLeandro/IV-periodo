/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.repositorios;

import br.recife.edu.ifpe.model.classes.LoteEntrada;
import java.util.ArrayList;
import java.util.List;

public class RepositorioLoteEntrada {
    
    private static RepositorioLoteEntrada myself = null;
    
    private List<LoteEntrada> lotes = null;

	private int contador = 0;
    
    private RepositorioLoteEntrada(){
        this.lotes = new ArrayList<>();
    }
    
    public static RepositorioLoteEntrada getCurrentInstance(){
        if(myself == null)
            myself = new RepositorioLoteEntrada();
        
        return myself;
    }
    
    public void create(LoteEntrada le){
        this.lotes.add(le);
    }
    
    public void update(LoteEntrada le){
        
        for(LoteEntrada aux: this.lotes){
            if(aux.getCodigo() == le.getCodigo()){
                
                aux.setDescricao(le.getDescricao());
                return;
                
            }
        }
        
    }
    
    public LoteEntrada read(int codigo){
        for(LoteEntrada aux: this.lotes){
            if(aux.getCodigo() == codigo){
                return aux;
            }
        }
        return null;
    }
    
    public void delete(LoteEntrada le){
        this.lotes.remove(le);
    }
    
    public List<LoteEntrada> readAll(){
        return this.lotes;
    }
    
    public int gerarCodigo() {
    	return this.contador += 1;
    }
}
