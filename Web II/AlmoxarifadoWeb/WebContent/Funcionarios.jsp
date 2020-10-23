<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario"%>
<%@page import="br.recife.edu.ifpe.model.classes.Funcionario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Funcionarios</title>
	<!-- Boostrap CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
		
			<div class="col">
				<h1 class="h1">Lista de Funcionarios</h1>
				<button type="button" class="btn btn-sm mb-1 btn-secondary" onclick="window.location.href='index.html'">Voltar à Home</button>
				<button type="button" class="btn btn-sm mb-1 btn-primary" onclick="window.location.href='cadastrofuncionario.jsp'">Cadastrar Funcionario</button>
				
			</div>
			<div class="col-12 mt-1">	
				<table class="table table-hover">
					<thead>
						<tr> 
							<th scope="col">Código</th>
					     	<th scope="col">Nome</th> 
					     	<th scope="col">Departamento</th>
					     	<th scope="col">Operações</th>
						</tr> 
					</thead>
					<tbody>
					<%
						List<Funcionario> funcionarios = RepositorioFuncionario.getCurrentInstance().readAll();	
						for (Funcionario f : funcionarios) {
					%>
						<tr>
							<th scope="row"><%= f.getCodigo() %></th>
							<td><%= f.getNome() %></td>
							<td><%= f.getDepartamento() %></td>
							<td>
								<button type="button" class="btn btn-primary" onclick="window.location.href='cadastrofuncionario.jsp?codigo=<%=f.getCodigo()%>'">Atualizar</button>
								<button type="button" class="btn btn-danger" onclick="window.location.href='FuncionarioServlet?delete=<%=f.getCodigo()%>'">Deletar</button>
							</td>
						</tr>
						
					<%
						}
						
					%>
					
					</tbody>
				</table>
				
			</div>
	
		</div>
	</div><!-- Container -->	
    
</body>
</html>