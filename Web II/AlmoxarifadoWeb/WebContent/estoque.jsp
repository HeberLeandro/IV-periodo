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
	<title>Estoque</title>
	<!-- Boostrap CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
		
			<div class="col">
				<h1 class="h1">Produtos no Estoque</h1>
				<button type="button" class="btn btn-sm mb-1 btn-secondary" onclick="window.location.href='index.html'">Home</button>
				<button type="button" class="btn btn-sm mb-1 btn-primary" onclick="window.location.href='loteentrada.jsp'">Cadastrar Lote de Entrada</button>
				<button type="button" class="btn btn-sm mb-1 btn-primary" onclick="window.location.href='lotesaida.jsp'">Cadastrar Lote de Saida</button>
				
			</div>
			<div class="col-12 mt-1">	
				<table class="table table-hover">
					<thead>
						<tr> 
							<th scope="col">Código</th>
					     	<th scope="col">Produto</th> 
					     	<th scope="col">Marca</th> 
					     	<th scope="col">Categoria</th> 
					     	<th scope="col">Quantidade</th>
						</tr> 
					</thead>
					<tbody>
					<my:carregaestoque/>
					
					<c:forEach var="item" items="${estoque.getItens()}">
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
	
		</div>
	</div><!-- Container -->	
    
</body>
</html>