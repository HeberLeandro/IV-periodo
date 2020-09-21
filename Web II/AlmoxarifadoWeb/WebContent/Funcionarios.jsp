<%@page import="br.recife.edu.ifpe.model.repositorios.RepositorioFuncionario"%>
<%@page import="br.recife.edu.ifpe.model.classes.Funcionario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<!-- Boostrap CSS only -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
    <script type="text/javascript">
	function funcionarioSelecionado(codigo){
		sessionStorage.setItem("funcionario", codigo);
	}
    
    </script>
<body>
	<div class="container">
		<div class="row justify-content-center">
		
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
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ModalAtualizar" onclick="funcionarioSelecionado('<%=f.getCodigo()%>')">Atualizar</button>
								
								<a href="#" class="badge badge-danger">Deletar</a>
							</td>
						</tr>
						
					<%
						}
						
					%>
					
					</tbody>
				</table>
				
				
								<!-- Modal -->
				<div class="modal fade" id="ModalAtualizar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Atualizar Funcionario></h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        ...
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				        <button type="button" class="btn btn-primary">Save changes</button>
				      </div>
				    </div>
				  </div>
				</div>
				
			
			</div><!-- Container -->	
	
	
	
	
	
	
		</div>
	</div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    

</body>
</html>