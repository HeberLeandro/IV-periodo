<%@page import="br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario"%>
<%@page import="br.recife.edu.ifpe.model.classes.Funcionario"%>
<%@page import="java.util.*"%>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<!-- Boostrap CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
				
			<div class="col-7 mt-5">
			<%
				int codigo = 0;
				Funcionario f = null;
				String cod = request.getParameter("codigo");
				
				if (cod != null){
					try {
						
						codigo = Integer.parseInt(cod);
						f = RepositorioFuncionario.getCurrentInstance().read(codigo);
						
					}catch (Exception e) {
						response.sendRedirect("http://localhost:8080/AlmoxarifadoWeb/cadastrofuncionario.jsp");
					}
				}

				
				if (f == null){
					
			%>
			<h1 class="h2 mb-5">Cadastrar Funcionario</h1>
				<form method="post" action="FuncionarioServlet">
					<div class="form-group">
						<label>Nome</label>
						<input type="text" required="required" class="form-control" name="nome">
					</div>
					<div class="form-group">
						<label>Departamento</label>
						<input type="text" required="required" class="form-control" name="departamento">		
					</div>
					<button type="submit" class="btn btn-primary">Cadastrar</button>
					<button type="button" onclick="window.location.href='index.html'" class="btn btn-secondary">Voltar a Home</button>
				</form>
			<%
				} else {
						
			%>
			<h1 class="h2 mb-5">Atualizar Funcionario</h1>
				<form method="post" action="FuncionarioServlet">
					<div class="form-group">
						<label>Nome</label>
						<input type="text" value="<%=f.getNome() %>" required="required" class="form-control" name="nome">
					</div>
					<div class="form-group">
						<label>Departamento</label>
						<input type="text" value="<%=f.getDepartamento() %>" required="required" class="form-control" name="departamento">	
						<input type='hidden' value="true" name="atualizar">	
						<input type='hidden' value="<%=f.getCodigo() %>" name="codigo">	
					</div>
					<button type="submit" class="btn btn-primary">Atualizar</button>
					<button type="button" onclick="window.location.href='index.html'" class="btn btn-secondary">Voltar a Home</button>
				</form>
			<%
				}
			%>
			</div>
			
		</div>
	</div>
	
</body>
</html>