
    
<%@page import="com.ipartek.formacion.controller.backoffice.UserController"%>

<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>

	<h1>Detalle Usuario</h1>
	
	<div class="row">
		<div class="col">
		
			<%@include file="../../includes/mensaje.jsp"%>
			
			<form action="backoffice/users" method="post" class="mb-2">
			
				<input type="hidden" name="op" value="<%=UserController.OP_GUARDAR%>">
			
				<div class="form-group">	
					<label for="id">Id:</label>
					<input type="text" name="id" value="${usuarioEditar.id}" readonly class="form-control">
				</div>
				
				<div class="form-group">
					<label for="nombre">Nombre:</label>
					<input type="text" name="nombre" value="${usuarioEditar.nombre}"
					       placeholder="M�nimio 3 m�ximo 150"
					       class="form-control">
				</div>
				
				<div class="form-group">
					<label for="codigo">Password:</label>
					<input type="password" name="contrasena" value="${usuarioEditar.contrasena}"					      
						   class="form-control">
				</div>
				
				<div class="form-group">
					<label for="rol">Rol:</label>
					<select class="form-control" name="idRol">
						
						<c:forEach items="${roles }" var="rol">
							<option value="${rol.id }" ${(rol.id == usuarioEditar.rol.id)?"selected":"" }>${rol.nombre }</option>
						</c:forEach>
					
					</select>
				</div>	
				
				<div class="form-group">
					<label for="fechaCreacion">Fecha Creaci�n:</label>
					  
					<fmt:formatDate type = "both"  var="fechaC" value = "${usuarioEditar.fechaCreacion}" />
					<input type="text" name="fechaCreacion" value="${fechaC }"					      
						   class="form-control" disabled>  
				</div>
				
				<div class="form-group">
					<label for="fechaEliminacion">Fecha Eliminaci�n:</label>
					<fmt:formatDate type = "both" var="fechaE" value = "${usuarioEditar.fechaEliminacion}" />
					<input type="text" name="fechaEliminacion" value="${fechaE }"					      
						   class="form-control" disabled>
				</div>
				
				<input type="submit" value="${(usuarioEditar.id != -1)?'Modificar':'Crear'}" class="btn btn-outline-primary  btn-block">
			
			</form>
			
			<c:if test="${usuarioEditar.id != -1}">
			
				
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-outline-danger btn-block" data-toggle="modal" data-target="#exampleModal">
				  Eliminar
				</button>
		
				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">�Estas Seguro de ELIMINAR el registro?</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <p>Cuidado porque operaci�n no es reversible</p>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        	<form action="backoffice/users" method="post">	
							<input type="hidden" name="op" value="<%=UserController.OP_ELIMINAR%>">
							<input type="hidden" name="id" value="${usuarioEditar.id}" readonly>			
							<input type="submit" value="Eliminar" class="btn btn-danger btn-block">	
						</form>
				      </div>
				    </div>
				  </div>
				</div>
				
				
			</c:if>	
			
			
			
		</div>
		
	</div>
	
<%@include file="../../includes/footer.jsp"%>
