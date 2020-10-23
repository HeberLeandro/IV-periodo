<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page import="java.util.List" %>
 <%@ taglib uri="br.recife.edu.ifpe.customtags" prefix="my"%>

 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Lote de Entrada</title>
	<!-- Boostrap CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
		
			<div class="col">
				<h1 class="h1">Cadastro do Lote de Entrada</h1>
				<button type="button" class="btn btn-sm mb-1 btn-secondary" onclick="window.location.href='index.html'">Home</button>
				<button type="button" class="btn btn-sm mb-1 btn-primary" onclick="window.location.href='#'">Estoque</button>
				
			</div>
			<div class="col-12 mx-1">	
				<table class="table table-hover">
					<thead>
						<tr> 
							<th scope="col">Código</th>
					     	<th scope="col">Nome</th> 
					     	<th scope="col">Marca</th>
					     	<th scope="col">Categoria</th>
					     	<th scope="col">Qtd. para o Lote de Entrada</th>
						</tr> 
					</thead>
					<tbody>
					<my:carregaprodutos/>
					
					<c:forEach var="p" items="${produtos}">
						<tr>
							<th scope="row"><c:out value="${p.codigo}"></c:out></th>
							<td><c:out value="${p.nome}"></c:out></td>
							<td><c:out value="${p.marca}"></c:out></td>
							<td><c:out value="${p.categoria}"></c:out></td>
							<td>
							  <div class="form-group form-inline">
							    <input type="number" id="${p.codigo}" class="form-control"/>
							    <button class="btn btn-primary ml-1" onclick="set(${p.codigo})">Definir Qtd.</button>
							  </div>
							</td>
						</tr>
					</c:forEach>
					
					</tbody>
				</table>
			</div>
			
			<c:if test="${loteEntrada != null}">
				<div class="col-12 mt-2">
				<h2 class="h2">Quantidades Selecionadas</h2>
					<table class="table table-hover table-bordered">
						<thead>
							<tr> 
								<th scope="col">Código</th>
						     	<th scope="col">Nome</th> 
						     	<th scope="col">Marca</th>
						     	<th scope="col">Categoria</th>
						     	<th scope="col">Qtd. Definida</th>
							</tr> 
						</thead>
						<tbody>
						
						<c:forEach var="item" items="${loteEntrada.itens}">
							<tr>
								<th scope="row"><c:out value="${item.codigo}"></c:out></th>
								<td><c:out value="${item.produto.nome}"></c:out></td>
								<td><c:out value="${item.produto.marca}"></c:out></td>
								<td><c:out value="${item.produto.categoria}"></c:out></td>
								<td><c:out value="${item.quantidade}"></c:out></td>
							</tr>
						</c:forEach>
						
						</tbody>
					</table>
				</div>
			</c:if>
			
	
		</div>
	</div><!-- Container -->
		
     <script>
		
		function set(codigo) {
			var qtd = document.getElementById(codigo);
			var aux;
			if(qtd.value == ''){
				console.log(qtd.value+', igual a null');
				aux = 0;
			}else{
				console.log(qtd.value+', else');
				aux = qtd.value;
			}
			
			console.log(aux);
		    fetch("LoteEntradaServlet?qtd="+aux+"&codigo=" + codigo, {method: "put"})
		            .then(function () {
		                location.reload();
		            });
		}
    </script>
</body>
</html>