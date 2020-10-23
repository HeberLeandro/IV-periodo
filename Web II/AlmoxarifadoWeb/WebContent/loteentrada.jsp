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
		<c:if test="${msgError != null}">	
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
		  		<strong>Erro!</strong> <c:out value="${msgError}"></c:out>
		  		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		   			<span aria-hidden="true">&times;</span>
		  		</button>
			</div>
		</c:if>
		<c:if test="${msgSuccess != null}">	
			<div class="alert alert-success alert-dismissible fade show" role="alert">
		  		<strong>Sucesso!</strong> <c:out value="${msgSuccess}"></c:out>
		  		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		   			<span aria-hidden="true">&times;</span>
		  		</button>
			</div>
		</c:if>
		<c:remove var="msgError"/>
		<c:remove var="msgSuccess"/>
	
		<div class="row justify-content-center">
		
			<div class="col">
				<h1 class="h1">Cadastro do Lote de Entrada</h1>
				<button type="button" class="btn btn-sm mb-1 btn-secondary" onclick="window.location.href='index.html'">Home</button>
				<button type="button" class="btn btn-sm mb-1 btn-primary" onclick="window.location.href='estoque.jsp'">Estoque</button>
				<button type="button" class="btn btn-sm mb-1 btn-primary" onclick="window.location.href='cadastroproduto.html'">Cadastrar Produto</button>
				
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
							    <button class="btn btn-primary ml-1" onclick="definir(${p.codigo})">Definir Qtd.</button>
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
								<td>
									<c:out value="${item.quantidade}"></c:out>
									<button type="button" class="close" aria-label="Close"  onclick="remover(${item.codigo})">
									  <span aria-hidden="true">&times;</span>
									</button>
								</td>
							</tr>
						</c:forEach>

						</tbody>
						
					</table>
					<button class="btn btn-success  my-2" onclick="cadastrar()">Cadastrar</button>
				</div>
			</c:if>
			
	
		</div>
	</div><!-- Container -->
		
     <script>
		
		function definir(codigo) {
			var qtd = document.getElementById(codigo);
			var aux;
			if(qtd.value == ''){
				aux = null;
				return;
			}else{
				aux = qtd.value;
			}
			
		    fetch("LoteEntradaServlet?qtd="+aux+"&codigo=" + codigo, {method: "put"})
		            .then(function () {
		                location.reload();
		            });
		}
		
		function remover(codigo){
			 fetch("LoteEntradaServlet?qtd=0&codigo=" + codigo, {method: "put"})
	            .then(function () {
	                location.reload();
	            });
		}
		
		function cadastrar(codigo){
			 fetch("LoteEntradaServlet", {method: "post"})
	            .then(function () {
	                location.reload();
	            });
		}
    </script>
    
    <!-- Scripts do Boostrap -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>