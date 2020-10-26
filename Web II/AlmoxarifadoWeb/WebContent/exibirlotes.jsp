<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Historico dos Lotes</title>
	<!-- Boostrap CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
		
			<div class="col">
				<h1 class="h1">Historico dos Lotes</h1>
				<button type="button" class="btn btn-sm mb-1 btn-secondary" onclick="window.location.href='index.html'">Home</button>
				
				
			</div>
			<div class="col-12 mt-1">	
				<table class="table table-hover">
					<thead>
						<tr> 
							<th scope="col">Código</th>
					     	<th scope="col">Tipo</th> 
					     	<th scope="col">Qtd. Total</th>
					     	<th scope="col">Data</th>
					     	<th scope="col">Operações</th>
						</tr> 
					</thead>
					<tbody>
					</tbody>
				</table>
				<button type="button" class="btn mb-1 btn-secondary" onclick='loteSaidaNone()'>Exibir apenas Lotes de Entrada</button>
				<button type="button" class="btn mb-1 btn-secondary" onclick='loteEntradaNone()'>Exibir apenas Lotes de Saida</button>
				<button type="button" class="btn mb-1 btn-secondary" onclick='window.location.reload()'>Exibir Todos</button>
			</div>
		
		</div>
	</div><!-- Container -->



	<script>
		getLotes();
		
	    function getLotes(codigo){
	        
	        fetch("ExibirLotesServlet",{method:"get"}).then(function(response){
	            response.text().then(function(text){
	                	let lotes = JSON.parse(text);
	                	showLotes(lotes);
	                });
	   	  });
		}
	    
	    function showLotes(lotes){
	    	var lotesEntrada, lotesSaida, tbody;
	    	lotesEntrada = lotes.lotesEntrada;
	    	lotesSaida = lotes.lotesSaida;
	    	tbody = document.getElementsByTagName("tbody")[0];
	    	
	    	for(i = 0; i < lotesEntrada.length; ++i){
	    		var dataEhora = lotesEntrada[i].data.split("T");
	    		var row = tbody.insertRow();
	    		row.setAttribute("class", "lotesEntrada");
	    		var cell1, cell2, cell3, cell4, cell5;
	    		cell1  = row.insertCell(-1);
	    		cell2  = row.insertCell(-1);
	    		cell3  = row.insertCell(-1);
	    		cell4  = row.insertCell(-1);
	    		cell5  = row.insertCell(-1);
	    		
	    		cell1.innerHTML = lotesEntrada[i].codigo;
	    		cell2.innerHTML = "Lote de Entrada"
	    		cell3.innerHTML = lotesEntrada[i].quantidadeTotal;
	    		cell4.innerHTML = "Data: " +dataEhora[0] +"<br/>Hora: "+dataEhora[1];
	    		cell4.setAttribute("id",lotesEntrada[i].data);
	    		cell5.innerHTML = " <button type='button' class='btn btn-sm mb-1 btn-primary'>Detalhes</button>";
	    	}
	    	
	    	for(i = 0; i < lotesSaida.length; ++i){
	    		var dataEhora = lotesSaida[i].data.split("T");
	    		var row = tbody.insertRow();
	    		row.setAttribute("class", "lotesSaida");
	    		var cell1, cell2, cell3, cell4, cell5;
	    		cell1  = row.insertCell(-1);
	    		cell2  = row.insertCell(-1);
	    		cell3  = row.insertCell(-1);
	    		cell4  = row.insertCell(-1);
	    		cell5  = row.insertCell(-1);
	    		
	    		cell1.innerHTML = lotesSaida[i].codigo;
	    		cell2.innerHTML = "Lote de Saida"
	    		cell3.innerHTML = lotesSaida[i].quantidadeTotal;
	    		cell4.innerHTML = "Data: " +dataEhora[0] +"<br/>Hora: "+dataEhora[1];
	    		cell4.setAttribute("id",lotesSaida[i].data);
	    		cell5.innerHTML = " <button type='button' class='btn btn-sm mb-1 btn-primary'>Detalhes</button>";
	    	}
	    	
	    	sortByDate();
	    }
	    
	    function convertDate(d) {
	    	  var dataEhora = d.split("T");
	    	  var data = dataEhora[0].split("-");
	    	  var hora = dataEhora[1].split(":")
	    	  return +(data[2]+data[1]+data[0]+hora[0]+hora[1]+hora[2]);
	    	}

    	function sortByDate() {
    	  var tbody = document.getElementsByTagName("tbody")[0];
    	  // get trs as array for ease of use
    	  var rows = [].slice.call(tbody.querySelectorAll("tr"));
    	  
    	  rows.sort(function(a,b) {
    	    return convertDate(a.cells[3].getAttribute("id")) - convertDate(b.cells[3].getAttribute("id"));
    	  });
    	  
    	  rows.forEach(function(v) {
    	    tbody.appendChild(v); // note that .appendChild() *moves* elements
    	  });
    	}
		
    	function loteEntradaNone(){
    		var lE = document.getElementsByClassName("lotesEntrada");
    		var lS = document.getElementsByClassName("lotesSaida");
    		
    		for(i = 0; i < lE.length; i++){
    			lE[i].classList.add("class","d-none");
    		}
    		for(i = 0; i < lS.length; i++){
    			lS[i].classList.remove("d-none");
    		}
    	}
    	
    	function loteSaidaNone(){
    		var lE = document.getElementsByClassName("lotesEntrada");
    		var lS = document.getElementsByClassName("lotesSaida");
    		
    		for(i = 0; i < lE.length; i++){
    			lE[i].classList.remove("d-none");
    			    		}
    		for(i = 0; i < lS.length; i++){
    			lS[i].classList.add("class","d-none");    			
    		}
    	}
                
	</script>
	
    <!-- Scripts do Boostrap -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>